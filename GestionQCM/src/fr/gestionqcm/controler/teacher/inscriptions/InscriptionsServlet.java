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
import fr.gestionqcm.view.beans.EditInscriptionGUI.FormFields;

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
		request.setAttribute("editInscriptionGUI", editInscriptionGUI);
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

			// Get date and test selected for edition and deletion
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

				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
			} else if (TypeAction.add.equals(typeAction)) {
				dateSelectionne = new Date();
				testSelectionne = (editInscriptionGUI.getTests().isEmpty()) ? null
						: editInscriptionGUI.getTests().get(0);

				editInscriptionGUI.setStartDateSelected(DateUtils
						.getDateFromDate(dateSelectionne));
				editInscriptionGUI.setStartHourSelected(DateUtils
						.getDateHourDate(dateSelectionne));
				editInscriptionGUI.setTestSelected(testSelectionne);

				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
			} else if (TypeAction.delete.equals(typeAction)) {
				InscriptionHandler.deleteInscriptionsByTestAndDate(
						testSelectionne, dateSelectionne);
			} else if (TypeAction.save.equals(typeAction)) {
				String[] users = request
						.getParameterValues(FormFields.usersSelected.name());
				System.out.println(users);
			} else {
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
			}

			// If rd is fill (edition, addition or default) we redirect the user
			// to the page else we return to the inscriptions list
			if (rd != null) {
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/teacher/inscriptions/");
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
