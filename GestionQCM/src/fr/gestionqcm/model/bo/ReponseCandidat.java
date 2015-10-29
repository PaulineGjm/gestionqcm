package fr.gestionqcm.model.bo;

public class ReponseCandidat {

	private Reponse response;
	private Question question;
	private InscriptionTest inscription;

	public ReponseCandidat(Reponse response, Question question,
			InscriptionTest inscription) {
		setResponse(response);
		setQuestion(question);
		setInscription(inscription);
	}

	public ReponseCandidat() {

	}

	public Reponse getResponse() {
		return response;
	}

	public void setResponse(Reponse response) {
		this.response = response;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public InscriptionTest getInscription() {
		return inscription;
	}

	public void setInscription(InscriptionTest inscription) {
		this.inscription = inscription;
	}
}
