package fr.gestionqcm.model.handler;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.dal.ThemeDAO;

public class ThemeHandler {
	public static ArrayList<Theme> getAll() throws SQLException {
		return ThemeDAO.getAll();
	}

	public static Theme getOne(int id) throws Exception {
		return ThemeDAO.getOne(id);
	}

	public static void update(Theme theme) throws Exception {
		ThemeDAO.update(theme);
	}

	public static void delete(int idTheme) throws Exception {
		ThemeDAO.delete(idTheme);

	}

	public static void add(Theme theme) throws Exception {
		ThemeDAO.add(theme);
	}
}
