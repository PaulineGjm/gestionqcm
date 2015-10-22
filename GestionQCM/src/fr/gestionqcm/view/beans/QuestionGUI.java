package fr.gestionqcm.view.beans;

import java.util.List;

import fr.gestionqcm.model.bo.Reponse;

public class QuestionGUI {

	private Integer idQuestion;
	private String wording;
	private String urlImage;
	private List<ReponseGUI> listResponses;
	
	public QuestionGUI()
	{
		
	}
	
	public QuestionGUI(Integer idQuestion, String wording, String urlImage,  List<ReponseGUI> listResponses)
	{
		setIdQuestion(idQuestion);
		setWording(wording);
		setUrlImage(urlImage);
		setListResponses(listResponses);
	}
	
	public Integer getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public List<ReponseGUI> getListResponses() {
		return listResponses;
	}
	public void setListResponses(List<ReponseGUI> listResponses) {
		this.listResponses = listResponses;
	}
	
	
}
