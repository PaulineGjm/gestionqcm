package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.SelectQuestionDAO;

/**
 * Servlet implementation class DisplayNextQuestion
 */
public class DisplayNextQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayNextQuestionServlet() {
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
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher;
		Integer numeroQuestion = (Integer) request.getSession().getAttribute("numeroQuestion");
		List<Integer> listIdQuestions = (List<Integer>) request.getSession().getAttribute("listIdQuestions");
		Integer idNextQuestion = listIdQuestions.get(numeroQuestion);
		try
		{
			
			
			
			return;
		}
		catch (Exception ex) {
			// Placer l'objet représentant l'exception dans le contexte de requete
			request.setAttribute("error", ex);
			// Passer la main à la page de pr�sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher("/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
