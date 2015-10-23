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
import fr.gestionqcm.model.dal.QuestionDAO;
import fr.gestionqcm.model.dal.ReponseCandidatDAO;
import fr.gestionqcm.model.dal.ReponseDAO;
import fr.gestionqcm.model.dal.SelectQuestionDAO;
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
		
		// TODO
		// Penser � re-setter le temps restant + la position de question

		TestEnCoursGUI runningTest = (TestEnCoursGUI) request.getSession()
				.getAttribute("runningTest");

		if(runningTest.getQuestionPosition() > 0)
		{
			QuestionGUI selectedQuestion = (QuestionGUI) request.getSession()
			.getAttribute("selectedQuestion");
			
			for(ReponseGUI reponse : selectedQuestion.getListResponses())
			{
				String valRecup  = request.getParameter("response"+reponse.getIdResponse());
				if(valRecup.equals("on"))
				{
					if(reponse.getIsCorrect() == true)
					{
						// Insertion en base
						ReponseCandidatDAO.ajouter(new ReponseCandidat(reponse.getIdResponse(), runningTest., selectedQuestion.getIdQuestion(), runningTest.getInscriptionID()));;
					}
				}
			}
			
			
		}
		
		if(runningTest.getTimeRemaining() == 0)
		{
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/trainee/overview.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if(runningTest.getQuestionPosition() == runningTest.getNbQuestion())
		{
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/trainee/overview.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		Integer questionNumber = runningTest.getQuestionPosition() +1;
		runningTest.setQuestionPosition(questionNumber);
		
		List<Integer> listIdQuestions = (List<Integer>) request.getSession()
				.getAttribute("listIdQuestions");

		// Comme c'est une liste son index commence � 0 et non � 1
		// En cons�quence si le num�ro de la question est 1, on va demander
		// l'index 0
		Integer idNextQuestion = listIdQuestions.get(questionNumber - 1);
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
					"/view/trainee/runningTest.jsp");
			dispatcher.forward(request, response);

			return;
		} catch (Exception ex) {
			// Placer l'objet repr�sentant l'exception dans le contexte de
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
