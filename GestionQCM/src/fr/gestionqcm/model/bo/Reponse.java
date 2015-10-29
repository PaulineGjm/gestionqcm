package fr.gestionqcm.model.bo;

public class Reponse {

	private Integer idResponse;
	private String wording;
	private Boolean isCorrect;
	private Integer idQuestion;

	public Reponse() {

	}

	public Reponse(Integer idResponse, String wording, Boolean isCorrect, Integer idQuestion) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idResponse == null) ? 0 : idResponse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reponse other = (Reponse) obj;
		if (idResponse == null) {
			if (other.idResponse != null)
				return false;
		} else if (!idResponse.equals(other.idResponse))
			return false;
		return true;
	}
}
