package fr.gestionqcm.controler.teacher.inscriptions;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.dal.InscriptionDAO;
import fr.gestionqcm.model.dal.TestDAO;
import fr.gestionqcm.model.enums.TypeAction;
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

		if (TypeAction.edit.equals(typeAction)) {
			try {
				EditInscriptionGUI editInscriptionGUI = new EditInscriptionGUI();
				editInscriptionGUI.setTests(TestDAO.getAllTests());
				List<InscriptionTest> inscriptionsTest = InscriptionDAO
						.getInscriptionsToTest(
								editInscriptionGUI.getStartDateSelected(),
								editInscriptionGUI.getTestSelected());
				editInscriptionGUI.setInscriptionsTest(inscriptionsTest);
				request.setAttribute("editInscriptionGUI", editInscriptionGUI);
				rd = getServletContext().getRequestDispatcher(
						"/view/teacher/inscriptions/editInscription.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (rd == null) {
			response.sendRedirect(request.getContextPath() + "/");
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
