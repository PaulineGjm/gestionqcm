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
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.QuestionDAO;
import fr.gestionqcm.model.dal.ReponseDAO;
import fr.gestionqcm.model.dal.SelectQuestionDAO;
import fr.gestionqcm.view.beans.QuestionGUI;
import fr.gestionqcm.view.beans.ReponseGUI;

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

		Integer numeroQuestion = (Integer) request.getSession().getAttribute(
				"numeroQuestion");
		
		numeroQuestion++;
		request.getSession().setAttribute("numeroQuestion", numeroQuestion);

		List<Integer> listIdQuestions = (List<Integer>) request.getSession()
				.getAttribute("listIdQuestions");

		// Comme c'est une liste son index commence � 0 et non � 1
		// En cons�quence si le num�ro de la question est 1, on va demander l'index 0
		Integer idNextQuestion = listIdQuestions.get(numeroQuestion-1);
		try {
			// R�cup�ration de la question et des r�ponses + insertion dans les
			// objets GUI
			Question question = QuestionDAO.getQuestionById(idNextQuestion);
			List<Reponse> listResponses = ReponseDAO
					.getResponsesByIdQuestion(idNextQuestion);

			List<ReponseGUI> listResponsesGUI = new ArrayList<ReponseGUI>();
			for (Reponse responseFor : listResponses) {

				ReponseGUI responseGUI = new ReponseGUI(
						responseFor.getIdResponse(), responseFor.getWording(),
						responseFor.getIsCorrect());
				listResponsesGUI.add(responseGUI);
			}

			QuestionGUI questionGUI = new QuestionGUI(question.getIdQuestion(),
					question.getWording(), question.getUrlImage(),
					listResponsesGUI);
			
			request.getSession().setAttribute("selectedQuestion", questionGUI);
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/trainee/TestEnCours.jsp");
			dispatcher.forward(request, response);

			return;
		} catch (Exception ex) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", ex);
			// Passer la main à la page de pr�sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher(
					"/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
