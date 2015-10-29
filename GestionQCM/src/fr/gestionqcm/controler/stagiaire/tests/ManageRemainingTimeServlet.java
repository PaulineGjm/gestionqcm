package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.dal.InscriptionDAO;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

/**
 * Servlet implementation class RefreshRemainingTime
 */
public class ManageRemainingTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageRemainingTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String mode = request.getParameter("mode");
		if(null != mode)
		{
			Integer remainingTime = Integer.parseInt(request.getParameter("timeRemaining"));
			if(remainingTime < 0)
				remainingTime = 0;
			// Refresh session attribute
			if(mode.equals("refresh"))
			{
				// value in seconds
				request.getSession().setAttribute("remainingTime", remainingTime);
			}
			// Save in database remaining time in minutes each minute
			else if(mode.equals("save"))
			{
				String idInscription = request.getParameter("idInscription");
				// valeur en minutes
				
				InscriptionDAO.updateRemainingTimeByInscriptionId(remainingTime, Integer.parseInt(idInscription));
			}
			// Refresh and save for end of the test => Remaining time = 0
			else
			{
				String idInscription = request.getParameter("idInscription");
				request.getSession().setAttribute("remainingTime", 0);
				InscriptionDAO.updateRemainingTimeByInscriptionId(0, Integer.parseInt(idInscription));
			}
		}
		
//		   
//	       response.setContentType("text/plain");
//	       response.setCharacterEncoding("UTF-8");
//	       response.getWriter().write(test.toString());
		}
		catch(Exception ex)
		{
			String error = ex.getMessage();
		}
	}

}
