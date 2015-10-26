package fr.gestionqcm.model.bo;

public class ReponseCandidat {

	private Integer idReponse;
	private Integer idUser;
	private Integer idQuestion;
	private Integer idInscription;

	public ReponseCandidat(Integer idReponse, Integer idUser,
			Integer idQuestion, Integer idInscription) {
		setIdReponse(idReponse);
		setIdUser(idUser);
		setIdQuestion(idQuestion);
		setIdInscription(idInscription);
	}

	public ReponseCandidat() {

	}

	public Integer getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(Integer idReponse) {
		this.idReponse = idReponse;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Integer getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(Integer idInscription) {
		this.idInscription = idInscription;
	}
}
