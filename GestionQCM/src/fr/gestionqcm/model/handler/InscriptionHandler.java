package fr.gestionqcm.model.handler;

import java.util.Date;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.InscriptionDAO;

public class InscriptionHandler {
	public static List<InscriptionTest> getInscriptionsToTest()
			throws Exception {
		return InscriptionDAO.getInscriptionsToTest();
	}

	public static List<InscriptionTest> getInscriptionsByTestAndDate(Test test,
			Date date) throws Exception {
		return InscriptionDAO.getInscriptionsByTestAndDate(test, date);
	}

	public static void deleteInscriptionsByTestAndDate(Test test, Date date)
			throws Exception {
		InscriptionDAO.deleteInscriptionsByTestAndDate(test, date);
	}
}
