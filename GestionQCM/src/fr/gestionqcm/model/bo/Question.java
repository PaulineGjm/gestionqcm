package fr.gestionqcm.model.bo;

public class Question {

	private Integer idQuestion;
	private Integer idTheme;
	private String wording;
	private Boolean isArchive;
	private String urlImage;

	public Question() {

	}

	public Question(Integer idQuestion, Integer idTheme, String wording,
			Boolean isArchive, String urlImage) {
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
}
