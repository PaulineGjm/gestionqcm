package fr.gestionqcm.controler.stagiaire.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.bo.Reponse;
import fr.gestionqcm.model.bo.ReponseCandidat;
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.ConnexionDAO;
import fr.gestionqcm.model.dal.InscriptionDAO;
import fr.gestionqcm.model.dal.QuestionDAO;
import fr.gestionqcm.model.dal.ReponseCandidatDAO;
import fr.gestionqcm.model.dal.ReponseDAO;
import fr.gestionqcm.model.dal.SelectQuestionDAO;
import fr.gestionqcm.model.enums.ModeRunningTest;
import fr.gestionqcm.view.beans.QuestionGUI;
import fr.gestionqcm.view.beans.ReponseGUI;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

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
		TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
				.getAttribute("runningTest");

		ModeRunningTest mode = (ModeRunningTest) request.getSession()
				.getAttribute("mode");

		try {
			
			String remainingTime = request.getParameter("remainingTime");
			if(null != remainingTime)
				request.getSession().setAttribute("remainingTime", Integer.parseInt(remainingTime));

			
			Integer questionNumber = runningTest.getQuestionPosition();

			// To go from view overview to a selected question
			String questionWanted = request.getParameter("questionNumber");
			if (null != questionWanted) {
				questionNumber = Integer.parseInt(questionWanted);
			}

			if (null != request.getSession().getAttribute("remainingTime") && (Integer) request.getSession().getAttribute("remainingTime") == 0) {
				dispatcher = getServletContext().getRequestDispatcher(
						"/trainee/test/overview");
				dispatcher.forward(request, response);
				return;
			}

			// Switch case to know which button has been pushed
			if (null != request.getParameter("bNext")) {
				if (questionNumber == runningTest
						.getNbQuestion()) {
					dispatcher = getServletContext().getRequestDispatcher(
							"/trainee/test/overview");
					dispatcher.forward(request, response);
					return;
				}
				// On passe à la question suivante
				if (questionNumber < runningTest.getNbQuestion()) {
					questionNumber++;
					// update of questionPosition
					if (mode.equals(ModeRunningTest.runningTest))
						InscriptionDAO.updateQuestionPositionByIdInscription(
								questionNumber, runningTest.getInscriptionID());
				}
			} else if (null != request.getParameter("bPrev")) {
				// On passe à la question suivante
				if (questionNumber > 1) {
					questionNumber--;
				}
			} else if (null != request.getParameter("bOverview")) {
				dispatcher = getServletContext().getRequestDispatcher(
						"/trainee/test/overview");
				dispatcher.forward(request, response);
				return;
			}

			runningTest.setQuestionPosition(questionNumber);

			List<Integer> listIdQuestions = (List<Integer>) request
					.getSession().getAttribute("listIdQuestions");

			// Comme c'est une liste son index commence à 0 et non à 1
			// En conséquence si le numéro de la question est 1, on va demander
			// l'index 0
			Integer idNextQuestion = listIdQuestions.get(questionNumber - 1);

			// Récupération de la question et des réponses + insertion dans les
			// objets GUI
			Question question = QuestionDAO.getQuestionById(idNextQuestion);
			List<Reponse> listResponses = ReponseDAO
					.getResponsesByIdQuestion(idNextQuestion);

			List<Integer> listIdResponseChecked = new ArrayList<Integer>();
			if (mode.equals(ModeRunningTest.overview)) {
				listIdResponseChecked = ReponseCandidatDAO.getListIdResponse(
						question.getIdQuestion(),
						runningTest.getInscriptionID());
			}

			List<ReponseGUI> listResponsesGUI = new ArrayList<ReponseGUI>();
			Boolean isChecked = false;
			for (Reponse responseFor : listResponses) {
				isChecked = false;
				if (mode.equals(ModeRunningTest.overview)) {
					if (listIdResponseChecked.contains(responseFor
							.getIdResponse())) {
						isChecked = true;
					}
				}
				ReponseGUI responseGUI = new ReponseGUI(
						responseFor.getIdResponse(), responseFor.getWording(),
						responseFor.getIsCorrect(), isChecked);
				listResponsesGUI.add(responseGUI);
			}

			// In runningTest mode isBranded = false
			// in overview mode we get back what the user entered
			Boolean isBranded = false;
			if (mode.equals(ModeRunningTest.overview)) {
				isBranded = SelectQuestionDAO.getSelectQuestion(
						question.getIdQuestion(),
						runningTest.getInscriptionID()).getIsBranded();
			}
			QuestionGUI questionGUI = new QuestionGUI(question.getIdQuestion(),
					question.getWording(), question.getUrlImage(), isBranded,
					listResponsesGUI);

			request.getSession().setAttribute("selectedQuestion", questionGUI);
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/trainee/runningTest.jsp");
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
