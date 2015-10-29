package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.bo.Reponse;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class QuestionDAO {

	public static Question getQuestionById(Integer idQuestion) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Question question = null;
		List<Reponse> responses = null;
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select q.*, r.id_reponse"
							+ " from question q join reponse r on q.id_question = r.id_question"
							+ " where q.id_question = ? and estArchive = 0");
			rqt.setInt(1, idQuestion);
			rs = rqt.executeQuery();

			while (rs.next()) {

				if (question == null) {
					Integer idTheme = rs.getInt("id_theme");
					String wording = rs.getString("enonce");
					Boolean isArchive = rs.getBoolean("estArchive");
					String urlImage = rs.getString("urlImage");

					question = new Question(idQuestion, idTheme, wording,
							isArchive, urlImage);
				}

				if (responses == null)
					responses = new ArrayList<Reponse>();

				responses.add(ReponseDAO.getOne(rs.getInt("id_reponse")));
			}

			if (question != null)
				question.setResponses(responses);

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
