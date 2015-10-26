package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.enums.TypeEstRepondu;

public class SelectQuestionDAO {

	public static List<SelectQuestion> getSelectQuestionByIdInscription(
			Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<SelectQuestion> listSelectQuestions = new ArrayList<SelectQuestion>();
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from select_question where id_inscription = ? order by id_question");
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

	public static SelectQuestion getSelectQuestion(Integer idQuestion,
			Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		SelectQuestion selectQuestion = null;
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from select_question where id_inscription = ? nad id_question = ?");
			rqt.setInt(1, idInscription);
			rqt.setInt(2, idQuestion);
			rs = rqt.executeQuery();

			if(rs.next()) {

				Integer idTest = rs.getInt("id_test");
				Integer idUser = rs.getInt("id_user");
				Integer isAnswered = rs.getInt("estRepondu");
				Boolean isBranded = rs.getBoolean("estMarque");

				selectQuestion = new SelectQuestion(idTest,
						idQuestion, idUser, idInscription, isAnswered,
						isBranded);
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

	public static Integer getNbSelectQuestion(TypeEstRepondu kindSearched)
			throws SQLException {

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
					.prepareStatement("select count(*) as nbSearched from select_question where estRepondu = ?");
			rqt.setInt(1, kindSearchedInt);

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
}
