package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class QuestionDAO {

	public static Question getQuestionById(Integer idQuestion)
			throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Question question = null;

		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from question where id_question = ? and estArchive = 0");
			rqt.setInt(1, idQuestion);
			rs = rqt.executeQuery();

			if (rs.next()) {

				Integer idTheme = rs.getInt("id_theme");
				String wording = rs.getString("enonce");
				Boolean isArchive = rs.getBoolean("estArchive");
				String urlImage = rs.getString("urlImage");

				question = new Question(idQuestion, idTheme, wording,
						isArchive, urlImage);
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

		return question;
	}
}
