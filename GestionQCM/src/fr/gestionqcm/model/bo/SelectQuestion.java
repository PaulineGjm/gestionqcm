package fr.gestionqcm.model.bo;

public class SelectQuestion {

	private Integer idTest;
	private Integer idQuestion;
	private Integer idUser;
	private Integer idInscription;
	private Boolean isAnswered;
	private Boolean isBranded;

	public SelectQuestion() {

	}

	public SelectQuestion(Integer idTest, Integer idQuestion, Integer idUser,
			Integer idInscription, Boolean isAnswered, Boolean isBranded) {
		setIdTest(idTest);
		setIdQuestion(idQuestion);
		setIdUser(idUser);
		setIdInscription(idInscription);
		setIsAnswered(isAnswered);
		setIsBranded(isBranded);
	}

	public Integer getIdTest() {
		return idTest;
	}

	public void setIdTest(Integer idTest) {
		this.idTest = idTest;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(Integer idInscription) {
		this.idInscription = idInscription;
	}

	public Boolean getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public Boolean getIsBranded() {
		return isBranded;
	}

	public void setIsBranded(Boolean isBranded) {
		this.isBranded = isBranded;
	}
}
