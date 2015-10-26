package fr.gestionqcm.controler.teacher.inscriptions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.handler.UserHandler;

/**
 * Servlet implementation class InscriptionsServlet
 */
public class StagiaireSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StagiaireSearchServlet() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String idPromo = request.getParameter("idPromo");
		Promotion promotion;
		if (idPromo != null) {
			promotion = new Promotion();
			promotion.setIdPromo(Integer.parseInt(idPromo));
		} else {
			promotion = null;
		}

		try {
			List<Stagiaire> listStagiaire = UserHandler.searchStagiaire(
					lastName, firstName, promotion);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new JSONArray(listStagiaire).toString());
		} catch (Exception e) {
			e.printStackTrace();
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
