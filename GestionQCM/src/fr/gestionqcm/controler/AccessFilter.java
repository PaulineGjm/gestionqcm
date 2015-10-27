package fr.gestionqcm.controler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gestionqcm.model.bo.Utilisateur;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AccessFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String context = request.getRequestURI().split("/")[2];

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		Utilisateur user = (Utilisateur) session.getAttribute("user");

		/**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		if (user == null || (user.isAnimateur() && "trainee".equals(context))) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			/* Affichage de la page restreinte */
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
