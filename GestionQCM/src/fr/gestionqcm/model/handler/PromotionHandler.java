package fr.gestionqcm.model.handler;

import java.util.List;

import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.dal.PromotionDao;

public class PromotionHandler {
	public static List<Promotion> getAllPromotions() throws Exception {
		return PromotionDao.getAllPromotions();
	}
}
