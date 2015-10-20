package fr.gestionqcm.model.handler;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.dal.ThemeDAO;

public class ThemeHandler
{
	public static ArrayList<Theme> getAll() throws SQLException
	{
		return ThemeDAO.getAll();
	}
}
