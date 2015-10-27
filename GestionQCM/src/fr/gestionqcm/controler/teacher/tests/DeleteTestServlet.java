package fr.gestionqcm.controler.teacher.tests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.handler.TestHandler;

/**
 * Servlet implementation class DeleteTestServlet
 */
public class DeleteTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processResquest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processResquest(request, response);
	}

	private void processResquest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idTest = Integer.valueOf(request.getParameter("id"));

		RequestDispatcher dispatcher = null;

		try {
			TestHandler.delete(idTest);
		} catch (Exception e) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", e);
			// Passer la main à la page de présentation des erreurs
			dispatcher = request.getRequestDispatcher("/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/teacher/tests/");
	}

}
