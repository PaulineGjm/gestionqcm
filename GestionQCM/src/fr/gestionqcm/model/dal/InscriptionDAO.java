package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class InscriptionDAO {
	/**
	 * Constante pour la requête d'insertion dans la table Messages.
	 */
	private static final String RQ_SELECT_ALL = "SELECT * FROM INSCRIPTION_TEST;";
	private static final String RQ_SELECT_ONE = "SELECT * FROM formations WHERE id = ?;";
	private static final String RQ_INSERT = "INSERT INTO formations (libelle, description, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String RQ_UPDATE = "UPDATE formations SET libelle = ?, description = ?, debut = ?, fin = ? WHERE id = ?;";
	private static final String RQ_DELETE = "DELETE FROM formations WHERE id = ?;";

	public static List<InscriptionTest> getAllInscriptions() throws Exception {
		PreparedStatement cmd = null;
		List<InscriptionTest> testInscriptions = new ArrayList<InscriptionTest>();
		cmd = AccessDatabase.getConnection().prepareStatement(RQ_SELECT_ALL);
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				InscriptionTest inscriptionTest = new InscriptionTest();

				inscriptionTest.setInscriptionId(rs.getInt("id_inscription"));
				inscriptionTest.setTestId(rs.getInt("id_test"));
				inscriptionTest.setInscriptionDate(rs
						.getDate("date_inscription"));
				inscriptionTest.setUserId(rs.getInt("id_user"));
				inscriptionTest.setTimesRemaining(rs.getInt("tempsRestant"));
				inscriptionTest.setIssueNumber(rs.getInt("nbIncident"));
				inscriptionTest.setQuestionPosition(rs
						.getInt("positionQuestion"));

				testInscriptions.add(inscriptionTest);
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
	// public static Formation lireFormation(int id) throws Exception {
	// PreparedStatement cmd = null;
	// Formation formation = null;
	// cmd = AccesBase.getConnection().prepareStatement(RQ_SELECT_ONE);
	// cmd.setInt(1, id);
	//
	// try {
	// cmd.executeQuery();
	// ResultSet rs = cmd.getResultSet();
	// if (rs.next()) {
	// formation = new Formation();
	//
	// formation.setId(rs.getInt("id"));
	// formation.setLibelle(rs.getString("libelle"));
	// formation.setDescription(rs.getString("description"));
	// formation.setDebut(rs.getDate("debut"));
	// formation.setFin(rs.getDate("fin"));
	//
	// } else {
	// throw new FormationInexistante(id);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// return formation;
	// }
	//
	// public static void ajouterFormation(Formation formation) throws Exception
	// {
	// PreparedStatement cmd = null;
	// cmd = AccesBase.getConnection().prepareStatement(RQ_INSERT,
	// Statement.RETURN_GENERATED_KEYS);
	// cmd.setString(1, formation.getLibelle());
	// cmd.setString(2, formation.getDescription());
	// cmd.setDate(3, (formation.getDebut() != null) ? new Date(formation
	// .getDebut().getTime()) : null);
	// cmd.setDate(4, (formation.getFin() != null) ? new Date(formation
	// .getFin().getTime()) : null);
	//
	// try {
	// cmd.executeUpdate();
	// ResultSet results = cmd.getGeneratedKeys();
	// if (results.next()) {
	// formation.setId(results.getInt(1));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	//
	// public static void modifierFormation(Formation formation) throws
	// Exception {
	// if (formation != null) {
	// PreparedStatement cmd = AccesBase.getConnection().prepareStatement(
	// RQ_UPDATE);
	// cmd.setString(1, formation.getLibelle());
	// cmd.setString(2, formation.getDescription());
	// cmd.setDate(3, (formation.getDebut() != null) ? new Date(formation
	// .getDebut().getTime()) : null);
	// cmd.setDate(4, (formation.getFin() != null) ? new Date(formation
	// .getFin().getTime()) : null);
	// cmd.setInt(5, formation.getId());
	//
	// try {
	// cmd.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	// }
	//
	// public static void supprimerFormation(Formation formation) throws
	// Exception {
	// if (formation != null) {
	// boolean formationSansInscription = DBInscriptions
	// .verifierFormationSansInscitpion(formation);
	//
	// if (formationSansInscription) {
	// PreparedStatement cmd = null;
	// cmd = AccesBase.getConnection().prepareStatement(RQ_DELETE);
	// cmd.setInt(1, formation.getId());
	//
	// try {
	// cmd.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Problème de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// } else {
	// throw new FormationAvecInsciptionException(formation);
	// }
	// }
	// }

}
