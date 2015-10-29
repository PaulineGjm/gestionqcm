package fr.gestionqcm.model.bo;

import java.util.ArrayList;
import java.util.List;

public class Question implements Comparable {

	private Integer idQuestion;
	private Integer idTheme;
	private String wording;
	private Boolean isArchive;
	private String urlImage;
	private List<Reponse> responses;

	public Question() {

	}

	public Question(Integer idQuestion, Integer idTheme, String wording, Boolean isArchive, String urlImage) {
		setIdQuestion(idQuestion);
		setIdTheme(idTheme);
		setWording(wording);
		setIsArchive(isArchive);
		setUrlImage(urlImage);
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Integer getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Integer idTheme) {
		this.idTheme = idTheme;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Reponse> getResponses() {
		return responses;
	}

	public void setResponses(List<Reponse> responses) {
		this.responses = responses;
	}

	public List<Reponse> getCorrectResponse() {
		List<Reponse> correctResponses = new ArrayList<Reponse>();
		for (Reponse r : responses) {
			if (r.getIsCorrect())
				correctResponses.add(r);
		}
		return correctResponses;
	}

	@Override
	public int compareTo(Object o) {
		Question q = (Question) o;
		return this.getIdQuestion().compareTo(q.getIdQuestion());
	}
}
