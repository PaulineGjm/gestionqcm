package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Section;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class SectionDAO {
	private static String tableName = "SECTION";

	private enum Column {
		idTest("id_test"),
		idTheme("id_theme"),
		nbQuestions("nbQuestion");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static List<Section> getAllTests() throws Exception {
		PreparedStatement cmd = null;
		List<Section> sections = new ArrayList<Section>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectAll());
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				sections.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
		return sections;
	}

//	public static Test getTest(int id) throws Exception {
//		PreparedStatement cmd = null;
//		Test test = null;
//		cmd = AccessDatabase.getConnection().prepareStatement(
//				requestFactory.getSelectOne(Column.testId.getColumnName()));
//		cmd.setInt(1, id);
//
//		try {
//			cmd.executeQuery();
//			ResultSet rs = cmd.getResultSet();
//			if (rs.next()) {
//				test = testMapping(rs);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new Exception(
//					"Problème de connexion avec la base de données !");
//		} finally {
//			cmd.getConnection().close();
//			cmd = null;
//		}
//		return test;
//	}
	
	public static List<Section> getByTest(Test test) throws Exception
	{
		PreparedStatement cmd = null;
		List<Section> sections = new ArrayList<Section>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				"SELECT * FROM SECTION WHERE id_test = ?;");
		cmd.setInt(1, test.getTestId());
		
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				sections.add(mapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
		return sections;
	}
	
	public static void deleteByTest(Test test) throws Exception
	{
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				"DELETE FROM SECTION WHERE id_test = ?");
		cmd.setInt(1, test.getTestId());
		
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

	public static void add(Section section) throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert(Column.idTest.getColumnName(),
						Column.idTheme.getColumnName(),
						Column.nbQuestions.getColumnName()));

		cmd.setInt(1, section.getIdTest());
		cmd.setInt(2, section.getIdTheme());
		cmd.setInt(3, section.getNbQuestions());

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
	
	private static Section mapping(ResultSet rs) throws SQLException {
		Section section = new Section();
		section.setIdTest(rs.getInt(Column.idTest.getColumnName()));
		section.setIdTheme(rs.getInt(Column.idTheme.getColumnName()));
		section.setNbQuestions(rs.getInt(Column.nbQuestions.getColumnName()));
		return section;
	}
}
