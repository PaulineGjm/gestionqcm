package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.dal.SelectQuestionDAO;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

/**
 * Servlet implementation class EndTestManagementServlet
 */
public class EndTestManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndTestManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
					.getAttribute("runningTest");
			// update questions set isRepondu = 3 if it is = 0
			SelectQuestionDAO.updateEndTest(runningTest.getInscriptionID());
			
			// update position question set à max
			
		}
		catch(Exception ex)
		{
			String error = ex.getMessage();
		}
	}
}
