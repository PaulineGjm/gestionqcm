package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.dal.InscriptionDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;
		try {
			TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
					.getAttribute("runningTest");
			if (runningTest.getQuestionPosition() <= runningTest
					.getNbQuestion()) {
				// update questions set isRepondu = 3 if it is equal to 0
				SelectQuestionDAO.updateEndTest(runningTest.getInscriptionID());

				// update position question set � max
				InscriptionDAO.updateQuestionPositionByIdInscription(
						runningTest.getNbQuestion() + 1,
						runningTest.getInscriptionID());
			}
			dispatcher = getServletContext().getRequestDispatcher(
					"/trainee/test/overview");
			dispatcher.forward(request, response);
			return;

		} catch (Exception ex) {
			// Placer l'objet repr�sentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", ex);
			// Passer la main à la page de pr�sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}
