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
import fr.gestionqcm.model.enums.ModeRunningTest;
import fr.gestionqcm.view.beans.TestDisponibleGUI;
import fr.gestionqcm.view.beans.TestEnCoursGUI;

/**
 * Servlet implementation class DemarrerTest
 */
public class BeginTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BeginTestServlet() {
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
		Integer idInscription = Integer.parseInt(request
				.getParameter("idInscription"));

		try {
			List<TestDisponibleGUI> listTestsDispos = (List<TestDisponibleGUI>) request
					.getSession().getAttribute("listTestsDisponibles");
			TestDisponibleGUI testEnCours = null;
			for (TestDisponibleGUI testDispo : listTestsDispos) {
				if (testDispo.getInscriptionID() == idInscription)
					testEnCours = testDispo;
			}

			if (null == testEnCours) {
				throw new Exception(
						"Le test sélectionné ne fait pas partie de la liste des tests disponibles");
			}

			List<SelectQuestion> listQuestions = SelectQuestionDAO
					.getSelectQuestionByIdInscription(idInscription);

			List<Integer> listIdQuestions = new ArrayList<Integer>();

			for (SelectQuestion question : listQuestions) {
				listIdQuestions.add(question.getIdQuestion());
			}

			if (testEnCours.getQuestionPosition() > listIdQuestions.size())
				request.getSession().setAttribute("mode",
						ModeRunningTest.overview);
			else
				request.getSession().setAttribute("mode",
						ModeRunningTest.runningTest);

			TestEnCoursGUI runningTest = new TestEnCoursGUI(idInscription,
					testEnCours.getName(), testEnCours.getTimeRemaining(),
					testEnCours.getQuestionPosition(), listIdQuestions.size());

			request.getSession().setAttribute("runningTest", runningTest);
			request.getSession().setAttribute("listIdQuestions",
					listIdQuestions);
			Integer testTime = (Integer) request.getSession().getAttribute(
					"remainingTime");
			if (null == testTime)
				request.getSession().setAttribute("remainingTime",
						testEnCours.getTimeRemaining() * 60);
			dispatcher = getServletContext().getRequestDispatcher(
					"/trainee/test/nextquestion");
			dispatcher.forward(request, response);

			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Placer l'objet reprÃ©sentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", ex);
			// Passer la main Ã  la page de prï¿½sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher(
					"/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
