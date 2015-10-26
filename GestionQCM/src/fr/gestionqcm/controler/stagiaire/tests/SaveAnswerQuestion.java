package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.ReponseCandidat;
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.ReponseCandidatDAO;
import fr.gestionqcm.model.dal.SelectQuestionDAO;
import fr.gestionqcm.view.beans.QuestionGUI;
import fr.gestionqcm.view.beans.ReponseGUI;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

/**
 * Servlet implementation class SaveAnswerQuestion
 */
public class SaveAnswerQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveAnswerQuestion() {
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

		Utilisateur user = (Utilisateur) request.getSession().getAttribute(
				"user");

		QuestionGUI selectedQuestion = (QuestionGUI) request.getSession()
				.getAttribute("selectedQuestion");

		TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
				.getAttribute("runningTest");

		try {
			if (null != user && null != selectedQuestion && null != runningTest) {
				// Save selected answers
				Integer countNbGoodAnswers = 0;
				Integer countNbChecked = 0;
				for (ReponseGUI responseGUI : selectedQuestion
						.getListResponses()) {
					String valRecup = request.getParameter("response"
							+ responseGUI.getIdResponse());
					// if value = checked
					if ("on".equals(valRecup)) {
						// Base insertion
						ReponseCandidatDAO.ajouter(new ReponseCandidat(
								responseGUI.getIdResponse(), user.getId(),
								selectedQuestion.getIdQuestion(), runningTest
										.getInscriptionID()));
						countNbChecked++;
					}
					if (responseGUI.getIsCorrect() == true) {
						countNbGoodAnswers++;
					}
				}

				// Saving question informations => branded and fully answered ?
				String valRecup = request.getParameter("questionMarked");
				Integer questionFullyAnswered = 0;
				// Response fully answered
				if (countNbChecked >= countNbGoodAnswers)
					questionFullyAnswered = 5;
				// Response not answered
				else if (countNbChecked.equals(0))
					questionFullyAnswered = 3;
				// Response partially answered
				else
					questionFullyAnswered = 4;

				Boolean isBranded = false;
				if ("on".equals(valRecup))
					isBranded = true;

				// update Selected Question
				SelectQuestion selectQuestion = new SelectQuestion(null,
						selectedQuestion.getIdQuestion(), user.getId(),
						runningTest.getInscriptionID(), questionFullyAnswered,
						isBranded);
				SelectQuestionDAO.updateByRunningTest(selectQuestion);

				// On redirige vers la servlet qui gère l'affichage des
				// questions
				dispatcher = getServletContext().getRequestDispatcher(
						"/test/DisplayNextQuestion");
				dispatcher.forward(request, response);

			}
			else
				throw new Exception("Informations manquantes");
		} catch (Exception ex) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", ex);
			// Passer la main Ã  la page de présentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher(
					"/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
