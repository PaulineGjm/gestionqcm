package fr.gestionqcm.model.handler;

import java.util.List;

import fr.gestionqcm.model.bo.ReponseCandidat;
import fr.gestionqcm.model.dal.ReponseCandidatDAO;

public class TestResultHandler {

	public static List<ReponseCandidat> getResponsesByInscription(
			int idInscription) throws Exception {
		return ReponseCandidatDAO.getByInscription(idInscription);
	}
}
