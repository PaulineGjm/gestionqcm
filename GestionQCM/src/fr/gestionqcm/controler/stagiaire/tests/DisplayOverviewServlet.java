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
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.SelectQuestionDAO;
import fr.gestionqcm.model.enums.ModeRunningTest;
import fr.gestionqcm.model.enums.TypeEstRepondu;
import fr.gestionqcm.view.beans.QuestionGUI;
import fr.gestionqcm.view.beans.SelectQuestionGUI;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

/**
 * Servlet implementation class DisplayOverview
 */
public class DisplayOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayOverviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
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

		// TODO
		// Penser à re-setter / enregistrer le temps restant + la position de
		// question
		request.getSession().setAttribute("mode", ModeRunningTest.overview);
		Utilisateur user = (Utilisateur) request.getSession().getAttribute(
				"user");

		TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
				.getAttribute("runningTest");

		try {
			List<SelectQuestion> listSelectQuestions = SelectQuestionDAO
					.getSelectQuestionByIdInscription(runningTest
							.getInscriptionID());

			List<SelectQuestionGUI> listSelectQuestionGuis = new ArrayList<SelectQuestionGUI>();
			for (SelectQuestion selectQuestion : listSelectQuestions) {
				listSelectQuestionGuis.add(new SelectQuestionGUI(selectQuestion
						.getIdQuestion(), selectQuestion.getIsAnswered(),
						selectQuestion.getIsBranded()));
			}
			
			Integer nbFullyAnswered = SelectQuestionDAO.getNbSelectQuestion(TypeEstRepondu.fullyAnswered);
			Integer nbPartiallyAnswered = SelectQuestionDAO.getNbSelectQuestion(TypeEstRepondu.partiallyAnswered);
			Integer nbNotAnswered = SelectQuestionDAO.getNbSelectQuestion(TypeEstRepondu.notAnswered);
			
			request.setAttribute("nbFullyAnswered", nbFullyAnswered);
			request.setAttribute("nbPartiallyAnswered", nbPartiallyAnswered);
			request.setAttribute("nbNotAnswered", nbNotAnswered);
			request.setAttribute("listSelectQuestionGuis", listSelectQuestionGuis);

			dispatcher = getServletContext().getRequestDispatcher(
					"/view/trainee/overview.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception ex) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", ex);
			// Passer la main Ã  la page de présentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
