package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gestionqcm.model.bo.ReponseCandidat;
import fr.gestionqcm.model.bo.SelectQuestion;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class ReponseCandidatDAO {

	public static void ajouter(ReponseCandidat reponse) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {

			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("insert into reponse_des_candidats(id_reponse, id_user, id_question, id_inscription) values (?,?,?,?)");
			rqt.setInt(1, reponse.getIdReponse());
			rqt.setInt(2, reponse.getIdUser());
			rqt.setInt(3, reponse.getIdQuestion());
			rqt.setInt(4, reponse.getIdInscription());
			rqt.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}
	
	public static void delete(Integer idQuestion, Integer idInscription) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {

			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("delete from reponse_des_candidats where id_question = ? and id_inscription = ?");
			rqt.setInt(1, idQuestion);
			rqt.setInt(2, idInscription);
			rqt.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}
	
	public static List<Integer> getListIdResponse(Integer idQuestion,
			Integer idInscription) throws SQLException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Integer> listIdResponse = new ArrayList<Integer>();
		try {
			cnx = AccessDatabase.getConnection();
			rqt = cnx
					.prepareStatement("select id_reponse from reponse_des_candidats where id_inscription = ? and id_question = ?");
			rqt.setInt(1, idInscription);
			rqt.setInt(2, idQuestion);
			rs = rqt.executeQuery();

			while(rs.next()) {

				Integer idResponse = rs.getInt("id_reponse");

				listIdResponse.add(idResponse);
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

		return listIdResponse;
	}
}
