package fr.gestionqcm.view.beans;

public class StagiaireGUI extends UtilisateurGUI{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idPromotion;
	
	/**
	 * Constructeur par défaut.
	 */
	public StagiaireGUI(){
		super();
	}
	
	public StagiaireGUI(Integer idPromo, int id, String lastName, String firstName,
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
