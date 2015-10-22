package fr.gestionqcm.model.handler;

import fr.gestionqcm.model.bo.Section;
import fr.gestionqcm.model.dal.SectionDAO;

public class SectionHandler {
	public static void add(Section s) throws Exception
	{
		SectionDAO.add(s);
	}
}
