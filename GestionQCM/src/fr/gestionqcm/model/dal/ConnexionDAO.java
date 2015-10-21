package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gestionqcm.model.bo.Animateur;
import fr.gestionqcm.model.bo.Stagiaire;
import fr.gestionqcm.model.bo.Utilisateur;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class ConnexionDAO {
	
	private static int ANIMATEUR = 1;
	private static int STAGIAIRE = 2;
	
	public static Utilisateur connexion(String mail, String password) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try{
			cnx = AccessDatabase.getConnection();
			rqt = cnx.prepareStatement("select * from utilisateur where email = ? and password = ?");
			rqt.setString(1, mail);
			rqt.setString(2, password);
			rs=rqt.executeQuery();
			
			if(rs.next()){
				Integer statut = rs.getInt("id_statut");
				Integer id = rs.getInt("id_user");
				String lastName = rs.getString("nom");
				String firstName = rs.getString("prenom");
				
				if(statut == ANIMATEUR)
				{
					utilisateur = new Animateur(id, lastName, firstName, mail, password);
				}
				else // Sinon c'est un stagiaire
				{
					Integer idPromo = rs.getInt("id_promo");
					utilisateur = new Stagiaire(idPromo, id, lastName, firstName, mail, password);
				}
				
			}
			// ...sinon on renvoie null
			
		}
		catch(SQLException ex)
		{
			utilisateur = null;
			ex.printStackTrace();
			throw new SQLException(ex);
		}
		finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return utilisateur;
	}
}
