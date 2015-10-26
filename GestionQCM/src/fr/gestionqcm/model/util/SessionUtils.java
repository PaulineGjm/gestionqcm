package fr.gestionqcm.model.util;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static String CLE_UTILISATEUR_CONNECTE = "utilisateurConnecte";

	private SessionUtils() {
		super();
	}

	public static boolean testSiUtilisateurConnecte(HttpSession session) {
		return (session != null && session
				.getAttribute(CLE_UTILISATEUR_CONNECTE) != null);
	}
}
