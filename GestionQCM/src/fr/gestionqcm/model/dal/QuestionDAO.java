package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Question;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class QuestionDAO {

	private enum Columns {
		idQuestion("id_question"),

		idTheme("id_theme"),

		enonce("enonce"),

		estArchive("estArchive"),

		urlImage("urlImage");

		private String text;

		private Columns(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	private final static String nomTable = "QUESTION";
	private static RequestFactory requestFactory = new RequestFactory(nomTable);

	public static Question getQuestionById(Integer idQuestion) throws Exception {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Question question = null;

		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from QUESTION where id_question = ? and estArchive = 0");
			rqt.setInt(1, idQuestion);
			rs = rqt.executeQuery();

			if (rs.next()) {
				question = questionMapping(rs);
			}

		} catch (SQLException ex) {
			throw new Exception(
					"Probleme de connection avec la base de donn�es");
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

	public static List<Question> getQuestionByTheme(Integer idTheme)
			throws Exception {
		PreparedStatement cmd = null;
		List<Question> questions = new ArrayList<Question>();
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Columns.idTheme.getText()));
		cmd.setInt(1, idTheme);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				Question question = questionMapping(rs);
				if (question != null) {
					questions.add(question);
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
		return questions;
	}

	private static Question questionMapping(ResultSet rs) throws Exception {
		Integer idQuestion = rs.getInt(Columns.idQuestion.getText());
		Integer idTheme = rs.getInt(Columns.idTheme.getText());
		String wording = rs.getString(Columns.enonce.getText());
		Boolean isArchive = rs.getBoolean(Columns.estArchive.getText());
		String urlImage = rs.getString(Columns.urlImage.getText());

		return new Question(idQuestion, idTheme, wording, isArchive, urlImage);
	}
}
