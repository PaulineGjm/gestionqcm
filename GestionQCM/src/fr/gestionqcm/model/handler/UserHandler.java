package fr.gestionqcm.model.handler;

import java.util.List;

import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.dal.UserDAO;

public class UserHandler {

	private UserHandler() {

	}

	/**
	 * Search a stagiaire by is complete name and id promotion; if one of the
	 * value is empty it's using the others parameters only. If all parameters
	 * are empty we return all the stagiaire stored.
	 * 
	 * @param promotion
	 * @param lastName
	 * @param firstName
	 * @return
	 * @throws Exception
	 */
	public static List<Stagiaire> searchStagiaire(String lastName,
			String firstName, Promotion promotion) throws Exception {
		return UserDAO.searchStagiaire(lastName, firstName, promotion);
	}
}
