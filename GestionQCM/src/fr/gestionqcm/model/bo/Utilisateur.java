package fr.gestionqcm.model.bo;

import java.io.Serializable;

public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String lastName;
	private String firstName;
	private String password;
	private String mail;

	/**
	 * Constructeur par défaut.
	 */
	public Utilisateur() {
		super();
	}

	/**
	 * Constructeur pour créer un utilisateur.
	 * 
	 * @param id
	 *            identifiant de l'animateur
	 * @param lastName
	 *            nom de l'animateur.
	 * @param firstName
	 *            prenom de l'animateur.
	 * @param password
	 *            mot de passe.
	 * @param mail
	 *            email.
	 */
	public Utilisateur(Integer id, String lastName, String firstName,
			String mail, String password) {
		setId(id);
		setLastName(lastName);
		setFirstName(firstName);
		setMail(mail);
		setPassword(password);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isStagiaire() {
		return this instanceof Stagiaire;
	}

	public boolean isAnimateur() {
		return this instanceof Animateur;
	}
}
