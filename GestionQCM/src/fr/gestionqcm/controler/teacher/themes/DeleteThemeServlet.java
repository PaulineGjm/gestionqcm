package fr.gestionqcm.controler.teacher.themes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.handler.ThemeHandler;

/**
 * Servlet implementation class DeleteThemeServlet
 */
public class DeleteThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteThemeServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		int idTheme = Integer.valueOf(request.getParameter("id"));

		RequestDispatcher dispatcher = null;

		try {
			ThemeHandler.delete(idTheme);
		} catch (Exception e) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", e);
			// Passer la main à la page de présentation des erreurs
			dispatcher = request.getRequestDispatcher("/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/ListThemes");
	}
}
