package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;
import fr.gestionqcm.model.enums.TypeEstRepondu;

public class SelectQuestionDAO {

	private static final String tableName = "SELECT_QUESTION";
	private static RequestFactory requestFactory = new RequestFactory(tableName);

	private enum Column {
		idInscription("id_inscription"),

		testId("id_test"),

		idQuestion("id_question"),

		userId("id_user"),

		estRepondu("estRepondu"),

		estMarque("estMarque");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	public static List<SelectQuestion> getSelectQuestionByIdInscription(
			Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<SelectQuestion> listSelectQuestions = new ArrayList<SelectQuestion>();
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from SELECT_QUESTION where id_inscription = ? order by id_question");
			rqt.setInt(1, idInscription);
			rs = rqt.executeQuery();

			while (rs.next()) {

				Integer idTest = rs.getInt("id_test");
				Integer idQuestion = rs.getInt("id_question");
				Integer idUser = rs.getInt("id_user");
				Integer isAnswered = rs.getInt("estRepondu");
				Boolean isBranded = rs.getBoolean("estMarque");

				SelectQuestion selectQuestion = new SelectQuestion(idTest,
						idQuestion, idUser, idInscription, isAnswered,
						isBranded);
				listSelectQuestions.add(selectQuestion);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return listSelectQuestions;
	}

	public static void addSelectQuestion(SelectQuestion selectQuestion)
			throws Exception {
		PreparedStatement cmd = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getInsert(Column.testId.getColumnName(),
						Column.idQuestion.getColumnName(),
						Column.userId.getColumnName(),
						Column.idInscription.getColumnName(),
						Column.estRepondu.getColumnName(),
						Column.estMarque.getColumnName()));

		cmd.setInt(1, selectQuestion.getIdTest());
		cmd.setInt(2, selectQuestion.getIdQuestion());
		cmd.setInt(3, selectQuestion.getIdUser());
		cmd.setInt(4, selectQuestion.getIdInscription());
		cmd.setInt(5, 0);
		cmd.setBoolean(6, false);

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

	public static void updateByRunningTest(SelectQuestion selectQuestion)
			throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("update select_question set estRepondu = ?, estMarque = ? where id_inscription = ? and id_question = ?");
			rqt.setInt(1, selectQuestion.getIsAnswered());
			rqt.setBoolean(2, selectQuestion.getIsBranded());
			rqt.setInt(3, selectQuestion.getIdInscription());
			rqt.setInt(4, selectQuestion.getIdQuestion());

			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	public static void updateEndTest(Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("update select_question set estRepondu = 3 where id_inscription = ? and estRepondu = 0");
			rqt.setInt(1, idInscription);

			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	public static SelectQuestion getSelectQuestion(Integer idQuestion,
			Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		SelectQuestion selectQuestion = null;
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from select_question where id_inscription = ? and id_question = ?");
			rqt.setInt(1, idInscription);
			rqt.setInt(2, idQuestion);
			rs = rqt.executeQuery();

			if (rs.next()) {

				Integer idTest = rs.getInt("id_test");
				Integer idUser = rs.getInt("id_user");
				Integer isAnswered = rs.getInt("estRepondu");
				Boolean isBranded = rs.getBoolean("estMarque");

				selectQuestion = new SelectQuestion(idTest, idQuestion, idUser,
						idInscription, isAnswered, isBranded);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return selectQuestion;
	}

	public static Integer getNbSelectQuestion(TypeEstRepondu kindSearched,
			Integer idInscription) throws SQLException {

		Integer nbSearched = null;

		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;

		Integer kindSearchedInt = null;

		switch (kindSearched) {
		case fullyAnswered:
			kindSearchedInt = 5;
			break;
		case partiallyAnswered:
			kindSearchedInt = 4;
			break;
		case notAnswered:
			kindSearchedInt = 3;
			break;
		}

		if (null == kindSearchedInt)
			return null;

		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select count(*) as nbSearched from select_question where estRepondu = ? and id_inscription = ?");
			rqt.setInt(1, kindSearchedInt);
			rqt.setInt(2, idInscription);

			rs = rqt.executeQuery();
			if (rs.next()) {
				nbSearched = rs.getInt("nbSearched");
			}
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return nbSearched;
	}

	public static void deleteByInscritpion(InscriptionTest inscription)
			throws Exception {
		if (inscription != null) {
			PreparedStatement cmd = null;
			cmd = AccessDatabase.getConnection().prepareStatement(
					requestFactory.getDelete(Column.idInscription
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
				cmd.close();
			}
		}
	}
}
