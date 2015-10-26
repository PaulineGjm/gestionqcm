package fr.gestionqcm.model.bo;

public class Stagiaire extends Utilisateur {

	private static final long serialVersionUID = 1L;

	private Promotion promotion;

	/**
	 * Constructeur par défaut.
	 */
	public Stagiaire() {
		super();
	}

	public Stagiaire(Integer idPromo, int id, String lastName,
			String firstName, String mail, String password) {
		super(id, lastName, firstName, mail, password);
		promotion = new Promotion();
		promotion.setIdPromo(idPromo);
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}
