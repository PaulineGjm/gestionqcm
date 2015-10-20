package fr.gestionqcm.view.beans;

public class AnimateurGUI extends UtilisateurGUI
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur par défaut.
	 */
	public AnimateurGUI(){
		super();
	}
	
	public AnimateurGUI(int id, String lastName, String firstName,
			String mail, String password) {
		super(id, lastName, firstName, mail, password);
	}
}
