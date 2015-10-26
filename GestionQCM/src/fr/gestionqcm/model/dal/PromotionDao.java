package fr.gestionqcm.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.dal.util.AccessDatabase;
import fr.gestionqcm.model.dal.util.RequestFactory;

public class PromotionDao {

	private static String tableName = "PROMOTION";

	private enum Column {
		idPromo("id_promo"),

		text("libelle");

		private String columnName;

		private Column(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}

	private static RequestFactory requestFactory = new RequestFactory(tableName);

	public static Promotion getPromotion(int idPromo) throws Exception {
		PreparedStatement cmd = null;
		Promotion promotion = null;
		cmd = AccessDatabase.getConnection().prepareStatement(
				requestFactory.getSelectOne(Column.idPromo.getColumnName()));
		cmd.setInt(1, idPromo);

		try {
			cmd.executeQuery();
			ResultSet rs = cmd.getResultSet();
			while (rs.next()) {
				promotion = promotionMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(
					"Problème de connexion avec la base de données !");
		} finally {
			cmd.getConnection().close();
			cmd = null;
		}
		return promotion;
	}

	private static Promotion promotionMapping(ResultSet rs) throws Exception {
		Promotion promotion = new Promotion();
		promotion.setIdPromo(rs.getInt(Column.idPromo.getColumnName()));
		promotion.setText(rs.getString(Column.text.getColumnName()));
		return promotion;
	}

}
