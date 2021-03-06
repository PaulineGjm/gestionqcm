package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.Reponse;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class ReponseDAO {

	public static List<Reponse> getResponsesByIdQuestion(Integer idQuestion)
			throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Reponse> listResponses = new ArrayList<Reponse>();
		try {

			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select * from reponse where id_question = ?");
			rqt.setInt(1, idQuestion);
			rs = rqt.executeQuery();

			while (rs.next()) {

				Integer idResponse = rs.getInt("id_reponse");
				String wording = rs.getString("libelle");
				Boolean isCorrect = rs.getBoolean("estCorrect");

				Reponse response = new Reponse(idResponse, wording, isCorrect,
						idQuestion);

				listResponses.add(response);
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

		return listResponses;
	}

	public static Reponse getOne(int id) throws Exception {
		PreparedStatement cmd = null;
		Reponse response = null;
		String request = "SELECT * FROM REPONSE WHERE id_reponse = ?";
		cmd = AccessDatabase.getConnection().prepareStatement(request);
		cmd.setInt(1, id);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			if (rs.next()) {
				Integer idResponse = rs.getInt("id_reponse");
				String wording = rs.getString("libelle");
				Boolean isCorrect = rs.getBoolean("estCorrect");
				Integer idQuestion = rs.getInt("id_question");

				response = new Reponse(idResponse, wording, isCorrect,
						idQuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Probl�me de connexion avec la base de donn�es !");
		} finally {
			cmd.getConnection().close();
			cmd.close();
		}
		return response;
	}

}
