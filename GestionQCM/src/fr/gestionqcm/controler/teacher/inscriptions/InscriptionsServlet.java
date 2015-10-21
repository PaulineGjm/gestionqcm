package fr.gestionqcm.controler.teacher.inscriptions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.dal.InscriptionDAO;
import fr.gestionqcm.model.enums.TypeAction;

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

		String requestURI = request.getRequestURI();
		String action = requestURI.substring(requestURI.lastIndexOf('/') + 1);
		TypeAction typeAction = TypeAction.fromString(action);

		if (TypeAction.edit.equals(typeAction)) {
			if (request.getParameter("id") != null) {
				try {
					InscriptionTest inscription = InscriptionDAO
							.getInscription(Integer.parseInt(request
									.getParameter("id")));
					if (inscription != null) {
						request.setAttribute("inscription", inscription);
						rd = getServletContext()
								.getRequestDispatcher(
										"/view/teacher/inscriptions/editInscription.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
