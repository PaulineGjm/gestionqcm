package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class TestDAO {
	private static String tableName = "TEST";

	private enum Column {
		testId("id_test"),

		name("nom"),

		testDuration("dureeDuTest"),

		currentThreshold("seuilEnCours"),

		acquisitionThreshold("seuilAcquis");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static List<Test> getAllTests() throws Exception {
		PreparedStatement cmd = null;
		List<Test> tests = new ArrayList<Test>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectAll());
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				tests.add(testMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return tests;
	}

	public static Test getTest(int id) throws Exception {
		PreparedStatement cmd = null;
		Test test = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.testId.getColumnName()));
		cmd.setInt(1, id);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			if (rs.next()) {
				test = testMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return test;
	}

	public static void addTest(Test test) throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert(Column.name.getColumnName(),
						Column.testDuration.getColumnName(),
						Column.currentThreshold.getColumnName(),
						Column.acquisitionThreshold.getColumnName()),
				Statement.RETURN_GENERATED_KEYS);

		cmd.setString(1, test.getName());
		cmd.setInt(2, test.getTestDuration());
		cmd.setInt(3, test.getCurrentThreshold());
		cmd.setInt(4, test.getAcquisitionThreshold());

		try {
			cmd.executeUpdate();
			ResultSet results = cmd.getGeneratedKeys();
			if (results.next()) {
				test.setTestId(results.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de donnÃ©es !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
	}

	public static void modifierTest(Test inscription) throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = AccessDatabase
					.getConnection()
					.prepareStatement(
							requestFactory.getUpdate(
									Column.testId.getColumnName(),
									Column.name.getColumnName(),
									Column.testDuration.getColumnName(),
									Column.currentThreshold.getColumnName(),
									Column.acquisitionThreshold.getColumnName()));

			cmd.setInt(1, inscription.getTestId());
			cmd.setString(2, inscription.getName());
			cmd.setInt(3, inscription.getTestDuration());
			cmd.setInt(4, inscription.getCurrentThreshold());
			cmd.setInt(5, inscription.getAcquisitionThreshold());

			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Probléme de connexion avec la base de donnÃ©es !");
			} finally {
				cmd.getConnection().close();
				cmd = null;
			}
		}
	}

	public static void supprimerFormation(Test inscription) throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = null;
			cmd = AccessDatabase.getConnection().prepareStatement(
					requestFactory.getDelete(Column.testId.getColumnName()));
			cmd.setInt(1, inscription.getTestId());

			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"ProblÃ¨me de connexion avec la base de donnÃ©es !");
			} finally {
				cmd.getConnection().close();
				cmd = null;
			}
		}
	}

	private static Test testMapping(ResultSet rs) throws SQLException {
		Test test = new Test();
		// nom, dureeDuTest, seuilEnCours, seuilAcquis
		test.setTestId(rs.getInt(Column.testId.getColumnName()));
		test.setName(rs.getString(Column.name.getColumnName()));
		test.setTestDuration(rs.getInt(Column.testDuration.getColumnName()));
		test.setCurrentThreshold(rs.getInt(Column.currentThreshold
				.getColumnName()));
		test.setAcquisitionThreshold(rs.getInt(Column.acquisitionThreshold
				.getColumnName()));
		return test;
	}
}
