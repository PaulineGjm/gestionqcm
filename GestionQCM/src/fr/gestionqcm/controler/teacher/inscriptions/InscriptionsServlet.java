package fr.gestionqcm.controler.teacher.inscriptions;

import static fr.gestionqcm.view.beans.EditInscriptionGUI.FormFields.inscriptionTestSelected;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.TestDAO;
import fr.gestionqcm.model.dal.UserDAO;
import fr.gestionqcm.model.enums.TypeAction;
import fr.gestionqcm.model.handler.InscriptionHandler;
import fr.gestionqcm.model.handler.PromotionHandler;
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

			List<Test> tests = TestDAO.getAllTestsNotArchived();
			editInscriptionGUI.setTests(tests);

			List<Promotion> promotions = PromotionHandler.getAllPromotions();
			editInscriptionGUI.setPromotions(promotions);

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
				editInscriptionGUI.setDateSelected(dateSelectionne);
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

				editInscriptionGUI.setDateSelected(dateSelectionne);
				editInscriptionGUI.setTestSelected(testSelectionne);

				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
			} else if (TypeAction.delete.equals(typeAction)) {
				InscriptionHandler.deleteInscriptionsByTestAndDate(
						testSelectionne, dateSelectionne);
			} else if (TypeAction.save.equals(typeAction)) {
				List<InscriptionTest> inscriptionsBeforeModification = InscriptionHandler
						.getInscriptionsByTestAndDate(testSelectionne,
								dateSelectionne);

				String testSelectedString = request
						.getParameter(FormFields.testSelected.name());
				String startDateSelectedString = request
						.getParameter(FormFields.startDateSelected.name());
				String startHourSelectedString = request
						.getParameter(FormFields.startHourSelected.name());
				ArrayList<String> usersIds = new ArrayList<String>();
				if (request.getParameterValues(FormFields.users.name()) != null) {
					usersIds.addAll(Arrays.asList(request
							.getParameterValues(FormFields.users.name())));
				}
				String formatedDate = String.format("%s %s:00.0",
						startDateSelectedString, startHourSelectedString);

				Date dateSelected = DateUtils.stringToDate(formatedDate);
				Test testSelected = TestDAO.getTest(Integer
						.valueOf(testSelectedString));

				List<InscriptionTest> inscriptionsNotFound = new ArrayList<InscriptionTest>();
				// Loop the original list to update the existing inscriptions
				for (InscriptionTest inscriptionTest : inscriptionsBeforeModification) {
					String userIdString = inscriptionTest.getUser().getId()
							.toString();
					if (usersIds.contains(userIdString)) {
						inscriptionTest.setTestStartDate(dateSelected);
						inscriptionTest.setTest(testSelected);
						usersIds.remove(userIdString);
					} else {
						inscriptionsNotFound.add(inscriptionTest);
					}
				}

				for (String userId : usersIds) {
					InscriptionTest inscriptionTest = new InscriptionTest();
					inscriptionTest.setTestStartDate(dateSelected);
					inscriptionTest.setTest(testSelected);
					inscriptionTest.setUser((Stagiaire) UserDAO.getUser(Integer
							.valueOf(userId)));

					inscriptionsBeforeModification.add(inscriptionTest);
				}

				InscriptionHandler.saveModifications(
						inscriptionsBeforeModification, inscriptionsNotFound);

			} else {
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
			}

			// If rd is fill (edition, addition or default) we redirect the user
			// to the page else we return to the inscriptions list
			if (rd != null) {
				request.setAttribute("inscriptionactive", "active");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/teacher/inscriptions/");
			}

		} catch (Exception e) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", e);
			// Passer la main à la page de présentation des erreurs
			rd = request.getRequestDispatcher("/view/error/error.jsp");
			rd.forward(request, response);
			return;
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
