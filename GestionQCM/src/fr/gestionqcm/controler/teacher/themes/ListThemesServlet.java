package fr.gestionqcm.controler.teacher.themes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.handler.ThemeHandler;

/**
 * Servlet implementation class ListThemesServlet
 */
public class ListThemesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListThemesServlet() {
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

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		ArrayList<Theme> listThemes = new ArrayList<Theme>();

		RequestDispatcher dispatcher = null;

		// Construire la liste des formations et la placer en session
		try {
			listThemes = ThemeHandler.getAll();
		} catch (Exception e) {
			// Placer l'objet représentant l'exception dans le contexte de
			// requete
			request.setAttribute("error", e);
			// Passer la main à la page de présentation des erreurs
			dispatcher = request.getRequestDispatcher("/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		request.setAttribute("listThemes", listThemes);
		request.setAttribute("themesactive", "active");
		dispatcher = request
				.getRequestDispatcher("/view/teacher/themes/listThemes.jsp");
		dispatcher.forward(request, response);

	}

}
