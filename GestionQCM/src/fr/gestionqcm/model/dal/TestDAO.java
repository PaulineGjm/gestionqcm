package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class TestDAO {
	/**
	 * Constante pour la requÃªte d'insertion dans la table Messages.
	 */
	private static final String RQ_SELECT_ALL = "SELECT * FROM TEST;";
	private static final String RQ_SELECT_ONE = "SELECT * FROM TEST WHERE id_test = ?;";
	private static final String RQ_INSERT = "INSERT INTO TEST (nom, dureeDuTest, seuilEnCours, seuilAcquis) VALUES(?, ?, ?, ?);";
	private static final String RQ_UPDATE = "UPDATE TEST SET nom = ?, dureeDuTest = ?, seuilEnCours = ?, seuilAcquis = ? WHERE id_test = ?;";
	private static final String RQ_DELETE = "DELETE FROM TEST WHERE id_test = ?;";

	public static List<Test> getAllTests() throws Exception {
		PreparedStatement cmd = null;
		List<Test> testInscriptions = new ArrayList<Test>();
		cmd = AccessDatabase.getConnection().prepareStatement(RQ_SELECT_ALL);
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				testInscriptions.add(testMapping(rs));
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

	// public static Test getInscription(int id) throws Exception {
	// PreparedStatement cmd = null;
	// Test inscription = null;
	// cmd = AccessDatabase.getConnection().prepareStatement(RQ_SELECT_ONE);
	// cmd.setInt(1, id);
	//
	// try {
	// cmd.executeQuery();
	// ResultSet rs = cmd.getResultSet();
	// if (rs.next()) {
	// inscription = testMapping(rs);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// return inscription;
	// }

	// public static void addInscription(Test test) throws Exception {
	// PreparedStatement cmd = null;
	// cmd = AccessDatabase.getConnection().prepareStatement(RQ_INSERT,
	// Statement.RETURN_GENERATED_KEYS);
	// cmd.setInt(1, test.getInscriptionId());
	// cmd.setDate(2, (test.getInscriptionDate() != null) ? new Date(
	// test.getInscriptionDate().getTime()) : null);
	// cmd.setInt(3, test.getUserId());
	// cmd.setDate(4, (test.getInscriptionDate() != null) ? new Date(
	// test.getInscriptionDate().getTime()) : null);
	// cmd.setInt(5, test.getTimesRemaining());
	// cmd.setInt(6, test.getIssueNumber());
	// cmd.setInt(7, test.getQuestionPosition());
	//
	// try {
	// cmd.executeUpdate();
	// ResultSet results = cmd.getGeneratedKeys();
	// if (results.next()) {
	// test.setInscriptionId(results.getInt(1));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de donnÃ©es !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	//
	// public static void modifierFormation(Test inscription) throws Exception {
	// if (inscription != null) {
	// PreparedStatement cmd = AccessDatabase.getConnection()
	// .prepareStatement(RQ_UPDATE);
	// cmd = AccessDatabase.getConnection().prepareStatement(RQ_INSERT,
	// Statement.RETURN_GENERATED_KEYS);
	// cmd.setInt(1, inscription.getInscriptionId());
	// cmd.setDate(2,
	// (inscription.getInscriptionDate() != null) ? new Date(
	// inscription.getInscriptionDate().getTime()) : null);
	// cmd.setInt(3, inscription.getUserId());
	// cmd.setDate(4,
	// (inscription.getInscriptionDate() != null) ? new Date(
	// inscription.getInscriptionDate().getTime()) : null);
	// cmd.setInt(5, inscription.getTimesRemaining());
	// cmd.setInt(6, inscription.getIssueNumber());
	// cmd.setInt(7, inscription.getQuestionPosition());
	//
	// try {
	// cmd.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Probléme de connexion avec la base de donnÃ©es !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	// }
	//
	// public static void supprimerFormation(Test inscription) throws Exception
	// {
	// if (inscription != null) {
	// PreparedStatement cmd = null;
	// cmd = AccessDatabase.getConnection().prepareStatement(RQ_DELETE);
	// cmd.setInt(1, inscription.getInscriptionId());
	//
	// try {
	// cmd.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "ProblÃ¨me de connexion avec la base de donnÃ©es !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	// }
	//
	private static Test testMapping(ResultSet rs) throws SQLException {
		Test test = new Test();
		// nom, dureeDuTest, seuilEnCours, seuilAcquis
		// test.setInscriptionId(rs.getInt("id_inscription"));
		// test.setTestId(rs.getInt("id_test"));
		// test.setInscriptionDate(rs.getDate("date_inscription"));
		// test.setUserId(rs.getInt("id_user"));
		// test.setTestStartDate(rs.getDate("dateDebutTest"));
		// test.setTimesRemaining(rs.getInt("tempsRestant"));
		// test.setIssueNumber(rs.getInt("nbIncident"));
		// test.setQuestionPosition(rs.getInt("positionQuestion"));
		return test;
	}
}
