package fr.gestionqcm.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Animateur;
import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.ConnexionDAO;
import fr.gestionqcm.model.dal.InscriptionDAO;
import fr.gestionqcm.model.dal.TestDAO;
import fr.gestionqcm.view.beans.AnimateurGUI;
import fr.gestionqcm.view.beans.StagiaireGUI;
import fr.gestionqcm.view.beans.TestDisponibleGUI;

/**
 * Servlet implementation class AuthentificationServlet
 */
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String accueilAnimateur = "/view/teacher/indexTeacher.jsp";
	private String accueilStagiaire = "/view/trainee/indexTrainee.jsp";
       
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher;
		String mail = request.getParameter("identifiant");
		String motdepasse = request.getParameter("motdepasse");
		
		try
		{
			
			Utilisateur utilisateur = ConnexionDAO.connexion(mail, motdepasse);
			if(null == utilisateur)
			{
				// Retour à la page d'accueil
				response.sendRedirect(request.getContextPath()+ "/view/login.jsp");
				return;
			}
			else if(utilisateur instanceof Animateur)
			{
				Animateur animateur = (Animateur) utilisateur;
				// Création animateur utilisé par l'IHM
				AnimateurGUI animateurConnecte = new AnimateurGUI(animateur.getId(), animateur.getLastName(), animateur.getFirstName(),
						animateur.getMail(), animateur.getPassword());
				
				// Invalider la session en cours dans le cas où c'est un autre profil qui est déjà connecté
				request.getSession().invalidate();
				
				// Placer le bean dans le contexte de session
				request.getSession().setAttribute("animateurConnecte", animateurConnecte);
				// PrÃ©senter la rÃ©ponse
				response.sendRedirect(request.getContextPath()+ accueilAnimateur);
				return;
			}
			else if(utilisateur instanceof Stagiaire)
			{
				Stagiaire stagiaire = (Stagiaire) utilisateur;
				
				StagiaireGUI stagiaireConnecte = new StagiaireGUI(stagiaire.getIdPromotion(), stagiaire.getId(), stagiaire.getLastName(),
						stagiaire.getFirstName(), stagiaire.getMail(), stagiaire.getPassword());
				
				List<InscriptionTest> listInscriptions = InscriptionDAO.getInscriptionsByTrainee(stagiaireConnecte.getId());
				
				List<TestDisponibleGUI> listTestsDisponibles = new ArrayList<TestDisponibleGUI>();
				
				for(InscriptionTest inscription : listInscriptions)
				{
					Test test = TestDAO.getTest(inscription.getTestId());
					listTestsDisponibles.add(new TestDisponibleGUI(inscription.getInscriptionId(), inscription.getUserId(), test.getName(),
							test.getTestDuration(), inscription.getTimesRemaining(), inscription.getQuestionPosition(), inscription.getTestStartDate()));
				}
				
				// Invalider la session en cours dans le cas où c'est un autre profil qui est déjà connecté
				request.getSession().invalidate();
				
				// Placer le bean dans le contexte de session
				request.getSession().setAttribute("stagiaireConnecte", stagiaireConnecte);
				request.getSession().setAttribute("listTestsDisponibles", listTestsDisponibles);
				// Présenter la réponse
				response.sendRedirect(request.getContextPath()+ accueilStagiaire);
				return;
			}
			
		} catch (Exception ex) {
			// Placer l'objet reprÃ©sentant l'exception dans le contexte de requete
			request.setAttribute("error", ex);
			// Passer la main Ã  la page de prï¿½sentation des erreurs
			dispatcher = getServletContext().getRequestDispatcher("/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
