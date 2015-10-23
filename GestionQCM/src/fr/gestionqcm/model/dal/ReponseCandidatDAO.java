package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.gestionqcm.model.bo.ReponseCandidat;
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
			rqt.setInt(2, reponse.getIdTest());
			rqt.setInt(3, reponse.getIdUser());
			rqt.setInt(3, reponse.getIdQuestion());
			rqt.setInt(3, reponse.getIdInscription());
			rqt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}
}
