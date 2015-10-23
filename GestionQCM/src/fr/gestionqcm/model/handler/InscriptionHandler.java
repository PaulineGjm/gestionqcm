package fr.gestionqcm.model.handler;

import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.dal.InscriptionDAO;

public class InscriptionHandler {
	public static List<InscriptionTest> getInscriptionsToTest()
			throws Exception {
		return InscriptionDAO.getInscriptionsToTest();
	}
}
