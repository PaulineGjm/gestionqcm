package fr.gestionqcm.controler.teacher.inscriptions;

import static fr.gestionqcm.view.beans.EditInscriptionGUI.FormFields.inscriptionTestSelected;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.TestDAO;
import fr.gestionqcm.model.enums.TypeAction;
import fr.gestionqcm.model.handler.InscriptionHandler;
import fr.gestionqcm.model.util.DateUtils;
import fr.gestionqcm.view.beans.EditInscriptionGUI;

/**
 * Servlet implementation class InscriptionsServlet
 */
public class InscriptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionsServlet() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		// Get request last word to know the user action
		String requestURI = request.getRequestURI();
		String action = requestURI.substring(requestURI.lastIndexOf('/') + 1);
		TypeAction typeAction = TypeAction.fromString(action);

		EditInscriptionGUI editInscriptionGUI = new EditInscriptionGUI();

		try {
			List<InscriptionTest> inscriptionsTest = InscriptionHandler
					.getInscriptionsToTest();
			editInscriptionGUI.setInscriptionsTest(inscriptionsTest);

			List<Test> tests = TestDAO.getAllTests();
			editInscriptionGUI.setTests(tests);

			Date dateSelectionne = null;

			Test testSelectionne = null;

			String inscriptionsTestSelected = request
					.getParameter(inscriptionTestSelected.name());

			if (inscriptionsTestSelected != null) {
				JSONObject testSelected = new JSONObject(
						inscriptionsTestSelected);

				dateSelectionne = DateUtils.stringToDate(testSelected
						.getString("testStartDate"));
				testSelectionne = TestDAO
						.getTest(testSelected.getInt("testId"));

			}

			if (TypeAction.edit.equals(typeAction)) {
				editInscriptionGUI.setStartDateSelected(DateUtils
						.getDateFromDate(dateSelectionne));
				editInscriptionGUI.setStartHourSelected(DateUtils
						.getDateHourDate(dateSelectionne));
				editInscriptionGUI.setTestSelected(testSelectionne);
				editInscriptionGUI
						.setSubscribedInscriptionsTest(InscriptionHandler
								.getInscriptionsByTestAndDate(testSelectionne,
										dateSelectionne));

				request.setAttribute("editInscriptionGUI", editInscriptionGUI);
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
				rd.forward(request, response);
			} else if (TypeAction.add.equals(typeAction)) {
				dateSelectionne = new Date();
				testSelectionne = (editInscriptionGUI.getTests().isEmpty()) ? null
						: editInscriptionGUI.getTests().get(0);

				request.setAttribute("editInscriptionGUI", editInscriptionGUI);
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
				rd.forward(request, response);
			} else if (TypeAction.delete.equals(typeAction)) {
				InscriptionHandler.deleteInscriptionsByTestAndDate(
						testSelectionne, dateSelectionne);

				response.sendRedirect(request.getContextPath()
						+ "/view/teacher/inscriptions/");
			} else if (TypeAction.save.equals(typeAction)) {
				response.sendRedirect(request.getContextPath()
						+ "/view/teacher/inscriptions/");
			} else {
				request.setAttribute("editInscriptionGUI", editInscriptionGUI);
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

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

}
