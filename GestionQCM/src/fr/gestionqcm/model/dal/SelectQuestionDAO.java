package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.util.AccessDatabase;

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
					.prepareStatement("select * from select_question where id_inscription = ?");
			rqt.setInt(1, idInscription);
			rs = rqt.executeQuery();

			while (rs.next()) {

				Integer idTest = rs.getInt("id_test");
				Integer idQuestion = rs.getInt("id_question");
				Integer idUser = rs.getInt("id_user");
				Boolean isAnswered = rs.getBoolean("estRepondu");
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
}
