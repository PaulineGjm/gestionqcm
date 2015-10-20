package fr.gestionqcm.model.bo;

public class Stagiaire extends Utilisateur{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idPromotion;
	
	/**
	 * Constructeur par défaut.
	 */
	public Stagiaire(){
		super();
	}
	
	public Stagiaire(Integer idPromo, int id, String lastName, String firstName,
			String mail, String password) {
		super(id, lastName, firstName, mail, password);
		setIdPromotion(idPromo);
	}

	public Integer getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}
	
}
