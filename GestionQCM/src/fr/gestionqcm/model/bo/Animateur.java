package fr.gestionqcm.model.bo;

public class Animateur extends Utilisateur{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur par défaut.
	 */
	public Animateur(){
		super();
	}
	
	public Animateur(int id, String lastName, String firstName,
			String mail, String password) {
		super(id, lastName, firstName, mail, password);
	}
	
}
