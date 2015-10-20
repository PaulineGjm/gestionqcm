package fr.gestionqcm.model.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class InscriptionDAO {

	private static String tableName = "INSCRIPTION_TEST";

	private enum Column {
		inscriptionId("id_inscription"),

		testId("id_test"),

		inscriptionDate("date_inscription"),

		userId("id_user"),

		testStartDate("dateDebutTest"),

		timesRemaining("tempsRestant"),

		issueNumber("nbIncident"),

		questionPosition("positionQuestion");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static List<InscriptionTest> getAllInscriptions() throws Exception {
		PreparedStatement cmd = null;
		List<InscriptionTest> testInscriptions = new ArrayList<InscriptionTest>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectAll());
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				testInscriptions.add(inscriptionMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return testInscriptions;
	}

	public static InscriptionTest getInscription(int id) throws Exception {
		PreparedStatement cmd = null;
		InscriptionTest inscription = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.inscriptionId
						.getColumnName()));
		cmd.setInt(1, id);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			if (rs.next()) {
				inscription = inscriptionMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return inscription;
	}

	public static void addInscription(InscriptionTest inscription)
			throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert(Column.testId.getColumnName(),
						Column.inscriptionDate.getColumnName(),
						Column.userId.getColumnName(),
						Column.testStartDate.getColumnName(),
						Column.timesRemaining.getColumnName(),
						Column.issueNumber.getColumnName(),
						Column.questionPosition.getColumnName()),
				Statement.RETURN_GENERATED_KEYS);

		cmd.setInt(1, inscription.getTestId());
		cmd.setDate(2, (inscription.getInscriptionDate() != null) ? new Date(
				inscription.getInscriptionDate().getTime()) : null);
		cmd.setInt(3, inscription.getUserId());
		cmd.setDate(4, (inscription.getInscriptionDate() != null) ? new Date(
				inscription.getInscriptionDate().getTime()) : null);
		cmd.setInt(5, inscription.getTimesRemaining());
		cmd.setInt(6, inscription.getIssueNumber());
		cmd.setInt(7, inscription.getQuestionPosition());

		try {
			cmd.executeUpdate();
			ResultSet results = cmd.getGeneratedKeys();
			if (results.next()) {
				inscription.setInscriptionId(results.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Probl�me de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
	}

	public static void updateInscription(InscriptionTest inscription)
			throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = AccessDatabase.getConnection()
					.prepareStatement(
							requestFactory.getUpdate(
									Column.inscriptionId.getColumnName(),
									Column.testId.getColumnName(),
									Column.inscriptionDate.getColumnName(),
									Column.userId.getColumnName(),
									Column.testStartDate.getColumnName(),
									Column.timesRemaining.getColumnName(),
									Column.issueNumber.getColumnName(),
									Column.questionPosition.getColumnName()));

			cmd.setInt(1, inscription.getTestId());
			cmd.setDate(2,
					(inscription.getInscriptionDate() != null) ? new Date(
							inscription.getInscriptionDate().getTime()) : null);
			cmd.setInt(3, inscription.getUserId());
			cmd.setDate(4, (inscription.getTestStartDate() != null) ? new Date(
					inscription.getTestStartDate().getTime()) : null);
			cmd.setInt(5, inscription.getTimesRemaining());
			cmd.setInt(6, inscription.getIssueNumber());
			cmd.setInt(7, inscription.getQuestionPosition());
			cmd.setInt(8, inscription.getInscriptionId());

			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Probl�me de connexion avec la base de données !");
			} finally {
				cmd.getConnection().close();
				cmd = null;
			}
		}
	}

	public static void supprimerFormation(InscriptionTest inscription)
			throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = null;
			cmd = AccessDatabase.getConnection().prepareStatement(
					requestFactory.getDelete(Column.inscriptionId
							.getColumnName()));
			cmd.setInt(1, inscription.getInscriptionId());

			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Problème de connexion avec la base de données !");
			} finally {
				cmd.getConnection().close();
				cmd = null;
			}
		}
	}

	private static InscriptionTest inscriptionMapping(ResultSet rs)
			throws SQLException {
		InscriptionTest inscriptionTest = new InscriptionTest();

		inscriptionTest.setInscriptionId(rs.getInt(Column.inscriptionId
				.getColumnName()));
		inscriptionTest.setTestId(rs.getInt(Column.testId.getColumnName()));
		inscriptionTest.setInscriptionDate(rs.getDate(Column.inscriptionDate
				.getColumnName()));
		inscriptionTest.setUserId(rs.getInt(Column.userId.getColumnName()));
		inscriptionTest.setTestStartDate(rs.getDate(Column.testStartDate
				.getColumnName()));
		inscriptionTest.setTimesRemaining(rs.getInt(Column.timesRemaining
				.getColumnName()));
		inscriptionTest.setIssueNumber(rs.getInt(Column.issueNumber
				.getColumnName()));
		inscriptionTest.setQuestionPosition(rs.getInt(Column.questionPosition
				.getColumnName()));
		return inscriptionTest;
	}

}
