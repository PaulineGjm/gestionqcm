package fr.gestionqcm.model.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.gestionqcm.model.bo.Theme;
import fr.gestionqcm.model.dal.util.AccessDatabase;

public class ThemeDAO {

	public static ArrayList<Theme> getAll() throws SQLException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<Theme> themes = new ArrayList<Theme>();
		try{
			cnx = AccessDatabase.getConnection();
			rqt = cnx.createStatement();			
			rs = rqt.executeQuery("select * from theme");
			Theme theme;
			while (rs.next()){
				theme = new Theme(
									rs.getInt("id_theme"),
									rs.getString("libelle")
								);
				themes.add(theme);				
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return themes;
	}

}
