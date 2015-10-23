package fr.gestionqcm.model.handler;

import java.sql.Date;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.InscriptionDAO;

public class InscriptionHandler {
	public static List<InscriptionTest> getInscriptionsToTest(Date startDate,
			Test test) throws Exception {
		return InscriptionDAO.getInscriptionsToTest(startDate, test);
	}
}
