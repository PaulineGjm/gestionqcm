package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class ThemeDAO {

	private static String tableName = "THEME";

	private enum Column {
		id("id_theme"), label("libelle");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static ArrayList<Theme> getAll() throws SQLException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Theme> themes = new ArrayList<Theme>();
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery("select * from theme");
			Theme theme;
			while (rs.next()) {
				theme = new Theme(rs.getInt("id_theme"),
						rs.getString("libelle"));
				themes.add(theme);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return themes;
	}

	public static Theme getOne(int id) throws Exception {
		PreparedStatement cmd = null;
		Theme theme = null;

		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.id.getColumnName()));
		cmd.setInt(1, id);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			if (rs.next()) {
				theme = mapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}

		return theme;
	}

	private static Theme mapping(ResultSet rs) throws SQLException {
		Theme theme = new Theme(rs.getInt(Column.id.getColumnName()),
				rs.getString(Column.label.getColumnName()));
		return theme;
	}

	public static void update(Theme theme) throws Exception {
		if (theme != null) {
			String request = requestFactory.getUpdate(
					Column.id.getColumnName(), Column.label.getColumnName());

			PreparedStatement cmd = AccessDatabase.getConnection()
					.prepareStatement(request);

			cmd.setString(1, theme.getLabel());
			cmd.setInt(2, theme.getId());

			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Problème de connexion avec la base de données !");
			} finally {
				cmd.getConnection().close();
				cmd.close();
			}
		}
	}

	public static void delete(int idTheme) throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getDelete(Column.id.getColumnName()));
		cmd.setInt(1, idTheme);

		try {
			cmd.executeUpdate();

			// suppression des sections associées au test
			RequestFactory rf = new RequestFactory("SECTION");
			cmd = AccessDatabase.getConnection().prepareStatement(
					rf.getDelete(Column.id.getColumnName()));
			cmd.setInt(1, idTheme);
			cmd.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}

	}

	public static void add(Theme theme) throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert(Column.label.getColumnName()),
				Statement.RETURN_GENERATED_KEYS);

		cmd.setString(1, theme.getLabel());

		try {
			cmd.executeUpdate();
			ResultSet results = cmd.getGeneratedKeys();
			if (results.next()) {
				theme.setId(results.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de donnÃ©es !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}

	}

}
