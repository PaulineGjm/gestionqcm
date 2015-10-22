package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Animateur;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class UserDAO {

	private static String tableName = "UTILISATEUR";

	private enum Status {
		Animateur("Animateur"), Stagiaire("Stagiaire");

		private String text;

		private Status(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	private enum Column {
		id("id_user"),

		lastName("nom"),

		firstName("prenom"),

		idPromotion("id_promo"),

		idStatut("id_statut"),

		estArchive("estArchive"),

		password("password"),

		mail("email");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static List<Utilisateur> getAllUsers() throws Exception {
		PreparedStatement cmd = null;
		List<Utilisateur> testInscriptions = new ArrayList<Utilisateur>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectAll());
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				testInscriptions.add(userMapping(rs));
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

	public static Utilisateur getUser(int id) throws Exception {
		PreparedStatement cmd = null;
		Utilisateur inscription = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.id.getColumnName()));
		cmd.setInt(1, id);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				inscription = userMapping(rs);
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

	public static void addUser(Utilisateur user) throws Exception {
		PreparedStatement cmd = null;

		ArrayList<String> columnsToUpdate = new ArrayList<String>();
		columnsToUpdate.add(Column.lastName.getColumnName());
		columnsToUpdate.add(Column.firstName.getColumnName());
		columnsToUpdate.add(Column.mail.getColumnName());
		columnsToUpdate.add(Column.password.getColumnName());

		if (user.isStagiaire()) {
			Stagiaire stagiaire = (Stagiaire) user;
			columnsToUpdate.add(Column.idPromotion.getColumnName());
		} else if (user.isAnimateur()) {
			Animateur animateur = (Animateur) user;
		}

		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert((String[]) columnsToUpdate.toArray()),
				Statement.RETURN_GENERATED_KEYS);

		cmd.setString(1, user.getLastName());
		cmd.setString(2, user.getFirstName());
		cmd.setString(3, user.getMail());
		cmd.setString(4, user.getPassword());
		if (user.isStagiaire()) {
			cmd.setInt(5, ((Stagiaire) user).getIdPromotion());
		}

		try {
			cmd.executeUpdate();
			ResultSet results = cmd.getGeneratedKeys();
			if (results.next()) {
				user.setId(results.getInt(1));
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

	// public static void updateInscription(Utilisateur inscription)
	// throws Exception {
	// if (inscription != null) {
	// PreparedStatement cmd = AccessDatabase.getConnection()
	// .prepareStatement(
	// requestFactory.getUpdate(
	// Column.inscriptionId.getColumnName(),
	// Column.testId.getColumnName(),
	// Column.inscriptionDate.getColumnName(),
	// Column.userId.getColumnName(),
	// Column.testStartDate.getColumnName(),
	// Column.timesRemaining.getColumnName(),
	// Column.issueNumber.getColumnName(),
	// Column.questionPosition.getColumnName()));
	//
	// cmd.setInt(1, inscription.getTest().getTestId());
	// cmd.setDate(2,
	// (inscription.getInscriptionDate() != null) ? new Date(
	// inscription.getInscriptionDate().getTime()) : null);
	// cmd.setInt(3, inscription.getUser().getId());
	// cmd.setDate(4, (inscription.getTestStartDate() != null) ? new Date(
	// inscription.getTestStartDate().getTime()) : null);
	// cmd.setInt(5, inscription.getTimesRemaining());
	// cmd.setInt(6, inscription.getIssueNumber());
	// cmd.setInt(7, inscription.getQuestionPosition());
	// cmd.setInt(8, inscription.getInscriptionId());
	//
	// try {
	// cmd.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// throw new Exception(
	// "Probl�me de connexion avec la base de données !");
	// } finally {
	// cmd.getConnection().close();
	// cmd = null;
	// }
	// }
	// }

	//
	// public static void deleteInscription(Utilisateur inscription)
	// throws Exception {
	// if (inscription != null) {
	// PreparedStatement cmd = null;
	// cmd = AccessDatabase.getConnection().prepareStatement(
	// requestFactory.getDelete(Column.inscriptionId
	// .getColumnName()));
	// cmd.setInt(1, inscription.getInscriptionId());
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

	private static Utilisateur userMapping(ResultSet rs) throws Exception {
		Utilisateur utilisateur = null;
		int idStatus = rs.getInt(Column.idStatut.getColumnName());
		String textStatus = getStatusById(idStatus);

		if (Status.Stagiaire.getText().equals(textStatus)) {
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setIdPromotion(rs.getInt(Column.idPromotion
					.getColumnName()));
			utilisateur = stagiaire;
		} else if (Status.Animateur.getText().equals(textStatus)) {
			Animateur animateur = new Animateur();
			utilisateur = animateur;
		}

		utilisateur.setId(rs.getInt(Column.id.getColumnName()));
		utilisateur.setLastName(rs.getString(Column.lastName.getColumnName()));
		utilisateur
				.setFirstName(rs.getString(Column.firstName.getColumnName()));
		utilisateur.setMail(rs.getString(Column.mail.getColumnName()));
		utilisateur.setPassword(rs.getString(Column.password.getColumnName()));
		return utilisateur;
	}

	private static String getStatusById(int id) throws Exception {
		PreparedStatement cmd = null;
		String status = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				"SELECT libelle FROM STATUT WHERE id_statut = ?;");
		cmd.setInt(1, id);
		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			if (rs.next()) {
				status = rs.getString("libelle");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return status;
	}
}
