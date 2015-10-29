package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.bo.Section;
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.bo.Utilisateur;
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
				InscriptionTest inscriptionTest = inscriptionMapping(rs);
				if (inscriptionTest != null) {
					testInscriptions.add(inscriptionTest);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
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
			while (rs.next()) {
				inscription = inscriptionMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
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

		// Pioche un nombre de question dans le refeentiel de question en se
		// basant sur les sections associ� au theme et les insert dans Selec
		// question
		Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
		cmd.setInt(1, inscription.getTest().getTestId());
		cmd.setTimestamp(2,
				(inscription.getInscriptionDate() != null) ? new Timestamp(
						inscription.getInscriptionDate().getTime())
						: currentDate);
		cmd.setInt(3, inscription.getUser().getId());
		cmd.setTimestamp(4,
				(inscription.getTestStartDate() != null) ? new Timestamp(
						inscription.getTestStartDate().getTime()) : currentDate);
		cmd.setInt(5, inscription.getTest().getTestDuration());
		cmd.setInt(6, inscription.getIssueNumber());
		cmd.setInt(7, 1);

		try {
			cmd.executeUpdate();
			ResultSet results = cmd.getGeneratedKeys();
			if (results.next()) {
				inscription.setInscriptionId(results.getInt(1));
			}
			selectQuestion(inscription);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Probl�me de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
	}

	private static void selectQuestion(InscriptionTest inscription)
			throws Exception {
		if (inscription != null) {
			for (Section section : inscription.getTest().getSections()) {
				int idTheme = section.getIdTheme();
				List<Question> questionsList = QuestionDAO
						.getQuestionByTheme(idTheme);
				Map<Integer, Question> questionsMap = new HashMap<Integer, Question>();

				for (Question question : questionsList) {
					questionsMap.put(question.getIdQuestion(), question);
				}

				Random random = new Random();
				List<Integer> questionsIds = new ArrayList<Integer>(
						questionsMap.keySet());

				for (int i = 0; i < section.getNbQuestions(); i++) {
					int indexPicked = random.nextInt(questionsIds.size());
					int questionIndex = questionsIds.get(indexPicked);
					Question question = questionsMap.get(questionIndex);
					questionsIds.remove(indexPicked);

					SelectQuestion selectQuestion = new SelectQuestion();
					selectQuestion.setIdTest(inscription.getTest().getTestId());
					selectQuestion.setIdQuestion(question.getIdQuestion());
					selectQuestion.setIdUser(inscription.getUser().getId());
					selectQuestion.setIdInscription(inscription
							.getInscriptionId());

					SelectQuestionDAO.addSelectQuestion(selectQuestion);
				}

			}
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

			cmd.setInt(1, inscription.getTest().getTestId());
			cmd.setTimestamp(2,
					(inscription.getInscriptionDate() != null) ? new Timestamp(
							inscription.getInscriptionDate().getTime()) : null);
			cmd.setInt(3, inscription.getUser().getId());
			cmd.setTimestamp(4,
					(inscription.getTestStartDate() != null) ? new Timestamp(
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
				cmd.close();
			}
		}
	}

	public static void deleteInscription(InscriptionTest inscription)
			throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = null;
			cmd = AccessDatabase.getConnection().prepareStatement(
					requestFactory.getDelete(Column.inscriptionId
							.getColumnName()));
			cmd.setInt(1, inscription.getInscriptionId());

			try {
				SelectQuestionDAO.deleteByInscritpion(inscription);
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

	private static InscriptionTest inscriptionMapping(ResultSet rs)
			throws Exception {
		InscriptionTest inscriptionTest = null;

		Utilisateur user = UserDAO.getUser(rs.getInt(Column.userId
				.getColumnName()));

		if (user.isStagiaire()) {
			inscriptionTest = new InscriptionTest();

			inscriptionTest.setUser((Stagiaire) user);
			inscriptionTest.setInscriptionId(rs.getInt(Column.inscriptionId
					.getColumnName()));
			inscriptionTest.setTest(TestDAO.getTest(rs.getInt(Column.testId
					.getColumnName())));
			inscriptionTest.setInscriptionDate(rs
					.getTimestamp(Column.inscriptionDate.getColumnName()));

			inscriptionTest.setTestStartDate(rs
					.getTimestamp(Column.testStartDate.getColumnName()));
			inscriptionTest.setTimesRemaining(rs.getInt(Column.timesRemaining
					.getColumnName()));
			inscriptionTest.setIssueNumber(rs.getInt(Column.issueNumber
					.getColumnName()));
			inscriptionTest.setQuestionPosition(rs
					.getInt(Column.questionPosition.getColumnName()));
		}

		return inscriptionTest;
	}

	public static List<InscriptionTest> getInscriptionsByTrainee(int idTrainee)
			throws Exception {
		PreparedStatement cmd = null;
		List<InscriptionTest> listInscriptions = new ArrayList<InscriptionTest>();
		InscriptionTest inscription = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.userId.getColumnName()));
		cmd.setInt(1, idTrainee);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				listInscriptions.add(inscriptionMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
		return listInscriptions;
	}

	public static List<InscriptionTest> getInscriptionsToTest()
			throws Exception {
		PreparedStatement cmd = null;
		List<InscriptionTest> testInscriptions = new ArrayList<InscriptionTest>();

		cmd = AccessDatabase.getConnection().prepareStatement(
				String.format("SELECT %s, %s FROM %s GROUP BY %s, %s;",
						Column.testId.getColumnName(),
						Column.testStartDate.getColumnName(), tableName,
						Column.testStartDate.getColumnName(),
						Column.testId.getColumnName()));

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				InscriptionTest inscriptionTest = new InscriptionTest();

				inscriptionTest.setTest(TestDAO.getTest(rs.getInt(Column.testId
						.getColumnName())));
				inscriptionTest.setTestStartDate(rs
						.getTimestamp(Column.testStartDate.getColumnName()));

				testInscriptions.add(inscriptionTest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
		return testInscriptions;
	}

	public static List<InscriptionTest> getInscriptionsByTestAndDate(Test test,
			java.util.Date date) throws Exception {
		PreparedStatement cmd = null;
		List<InscriptionTest> testInscriptions = new ArrayList<InscriptionTest>();
		if (test != null && date != null) {
			cmd = AccessDatabase.getConnection().prepareStatement(
					String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?;",
							tableName, Column.testStartDate.getColumnName(),
							Column.testId.getColumnName()));

			cmd.setTimestamp(1, new Timestamp(date.getTime()));
			cmd.setInt(2, test.getTestId());
			try {
				cmd.executeQuery();
				ResultSet rs = cmd.getResultSet();
				while (rs.next()) {
					InscriptionTest inscriptionTest = inscriptionMapping(rs);
					if (inscriptionTest != null) {
						testInscriptions.add(inscriptionTest);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Problème de connexion avec la base de données !");
			} finally {
				cmd.getConnection().close();
				cmd.close();
			}
		}
		return testInscriptions;
	}

	public static void deleteInscriptionsByTestAndDate(Test test,
			java.util.Date date) throws Exception {
		PreparedStatement cmd = null;
		if (test != null && date != null) {
			cmd = AccessDatabase.getConnection().prepareStatement(
					String.format("DELETE FROM %s WHERE %s = ? AND %s = ?;",
							tableName, Column.testStartDate.getColumnName(),
							Column.testId.getColumnName()));

			cmd.setTimestamp(1, new Timestamp(date.getTime()));
			cmd.setInt(2, test.getTestId());
			try {
				for (InscriptionTest inscription : getInscriptionsByTestAndDate(
						test, date)) {
					SelectQuestionDAO.deleteByInscritpion(inscription);
				}
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

	public static void updateRemainingTimeByInscriptionId(
			Integer remainingTime, Integer idInscription) throws Exception {
		PreparedStatement cmd = null;
		if (remainingTime != null && idInscription != null) {
			cmd = AccessDatabase.getConnection().prepareStatement(
					String.format("UPDATE %s set %s = ? WHERE %s = ?;",
							tableName, Column.timesRemaining.getColumnName(),
							Column.inscriptionId.getColumnName()));

			cmd.setInt(1, remainingTime);
			cmd.setInt(2, idInscription);
			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Problème de connexion avec la base de données !");
			} catch (Exception ex) {
				String test = ex.getMessage();
			} finally {
				cmd.getConnection().close();
				cmd.close();
			}
		}
	}

	public static void updateQuestionPositionByIdInscription(
			Integer questionPosition, Integer idInscription) throws Exception {
		PreparedStatement cmd = null;
		if (questionPosition != null && idInscription != null) {
			cmd = AccessDatabase.getConnection().prepareStatement(
					String.format("UPDATE %s set %s = ? WHERE %s = ?;",
							tableName, Column.questionPosition.getColumnName(),
							Column.inscriptionId.getColumnName()));

			cmd.setInt(1, questionPosition);
			cmd.setInt(2, idInscription);
			try {
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception(
						"Problème de connexion avec la base de données !");
			} catch (Exception ex) {
				String test = ex.getMessage();
			} finally {
				cmd.getConnection().close();
				cmd.close();
			}
		}
	}
}
