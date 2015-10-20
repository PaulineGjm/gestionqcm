package fr.gestionqcm.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Animateur;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.ConnexionDAO;

/**
 * Servlet implementation class AuthentificationServlet
 */
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			
			Utilisateur utilisateur = ConnexionDAO.connexion("jbeaucousin@sonmail.fr", "jb");
			if(null == utilisateur)
			{
				
			}
			else if(utilisateur instanceof Animateur)
			{
				Animateur animateur = (Animateur) utilisateur;
				String nom = animateur.getFirstName();
				nom += "";
			}
			else if(utilisateur instanceof Stagiaire)
			{
				Stagiaire stagiaire = (Stagiaire) utilisateur;
				String nom = stagiaire.getFirstName();
				nom += "";
			}
			
		} catch (SQLException sqle) {
			// Placer l'objet représentant l'exception dans le contexte de requete
			request.setAttribute("erreur", sqle);
			// Passer la main à la page de pr�sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher("/erreur/erreur.jsp");
			dispatcher.forward(request, response);
			return;
		}
		}
	}

}
