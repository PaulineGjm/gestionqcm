package fr.gestionqcm.controler.teacher.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.handler.ThemeHandler;

/**
 * Servlet implementation class NewTestServlet
 */
public class NewTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTestServlet() {
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Theme> themes = new ArrayList<Theme>();
		
		RequestDispatcher dispatcher = null;
		
		// Construire la liste des formations et la placer en session
		try {
			themes = ThemeHandler.getAll();
		}
		catch (SQLException sqle){
			// Placer l'objet représentant l'exception dans le contexte de requete
			request.setAttribute("error", sqle);
			// Passer la main à la page de présentation des erreurs
			dispatcher = request.getRequestDispatcher("/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("themes", themes);			
		dispatcher = request.getRequestDispatcher("/view/teacher/tests/newTest.jsp"); 
		dispatcher.forward(request, response);
	}

}
