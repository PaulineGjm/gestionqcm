package fr.gestionqcm.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.bo.Reponse;
import fr.gestionqcm.model.bo.ReponseCandidat;
import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.handler.TestResultHandler;
import fr.gestionqcm.model.handler.ThemeHandler;
import fr.gestionqcm.view.beans.TestResultGUI;

/**
 * Servlet implementation class TestResultServlet
 */
public class TestResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		try {
			TestResultGUI testsGUI = new TestResultGUI();
			int idInscription = Integer.valueOf(request.getParameter("id"));
			List<ReponseCandidat> responses = TestResultHandler.getResponsesByInscription(idInscription);
			Question question = null;

			Map<Question, List<Reponse>> responsesByQuestion = new TreeMap<Question, List<Reponse>>();
			Map<Integer, Integer> responsesCorrectByTheme = new TreeMap<Integer, Integer>();
			Map<Integer, Integer> responsesWrongByTheme = new TreeMap<Integer, Integer>();

			for (ReponseCandidat rc : responses) {
				if (responsesByQuestion.get(rc.getQuestion()) == null)
					responsesByQuestion.put(rc.getQuestion(), new ArrayList<Reponse>());

				responsesByQuestion.get(rc.getQuestion()).add(rc.getResponse());
			}

			for (Entry<Question, List<Reponse>> entry : responsesByQuestion.entrySet()) {
				question = entry.getKey();
				List<Reponse> rs = entry.getValue();

				List<Reponse> correctResponses = question.getCorrectResponse();
				int nb = 1;
				if (allResponsesCorrect(rs, correctResponses)) {
					if ((responsesCorrectByTheme.containsKey(question.getIdTheme()))) {
						nb = responsesCorrectByTheme.get(question.getIdTheme());
						nb++;

					}
					responsesCorrectByTheme.put(question.getIdTheme(), nb);
				} else {
					if ((responsesWrongByTheme.containsKey(question.getIdTheme()))) {
						nb = responsesWrongByTheme.get(question.getIdTheme());
						nb++;

					}
					responsesWrongByTheme.put(question.getIdTheme(), nb);
				}
			}

			int totalCorrect = 0;
			int totalWrong = 0;

			for (Entry entry : responsesCorrectByTheme.entrySet()) {
				int idTheme = (int) entry.getKey();
				int correct = (int) entry.getValue();
				int wrong = responsesWrongByTheme.get(idTheme);
				responsesWrongByTheme.remove(idTheme);
				int total = correct + wrong;
				totalCorrect += correct;
				totalWrong += wrong;

				int result = (correct * 100) / total;
				if (testsGUI.getThemesResult() == null)
					testsGUI.setThemesResult(new TreeMap<Theme, Integer>());
				testsGUI.getThemesResult().put(ThemeHandler.getOne(idTheme), result);
			}

			if (responsesWrongByTheme.size() > 0) {
				for (Entry<Integer, Integer> entry : responsesWrongByTheme.entrySet()) {
					int idTheme = (int) entry.getKey();
					totalWrong += (int) entry.getValue();

					testsGUI.getThemesResult().put(ThemeHandler.getOne(idTheme), 0);
				}
			}

			int global = (totalCorrect * 100) / (totalCorrect + totalWrong);
			testsGUI.setGlobalResult(global);

			request.setAttribute("testsResult", testsGUI);

			request.getRequestDispatcher("/view/trainee/resultTest.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Boolean allResponsesCorrect(List<Reponse> responsesByQuestion, List<Reponse> correctResponses) {

		if (responsesByQuestion.size() != correctResponses.size())
			return false;

		for (Reponse r : responsesByQuestion) {
			if (!correctResponses.contains(r))
				return false;
		}

		return true;

	}

}
