package fr.gestionqcm.model.bo;

import java.io.Serializable;

public class Promotion implements Serializable {

	private int idPromo;

	private String text;

	public int getIdPromo() {
		return idPromo;
	}

	public void setIdPromo(int id_promo) {
		this.idPromo = id_promo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
