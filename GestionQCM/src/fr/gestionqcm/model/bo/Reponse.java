package fr.gestionqcm.model.bo;

public class Reponse {

	private Integer idResponse;
	private String wording;
	private Boolean isCorrect;
	private Integer idQuestion;
	
	public Reponse()
	{
		
	}
	
	public Reponse(Integer idResponse, String wording, Boolean isCorrect, Integer idQuestion)
	{
		setIdResponse(idResponse);
		setWording(wording);
		setIsCorrect(isCorrect);
		setIdQuestion(idQuestion);
	}

	public Integer getIdResponse() {
		return idResponse;
	}

	public void setIdResponse(Integer idResponse) {
		this.idResponse = idResponse;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
}
