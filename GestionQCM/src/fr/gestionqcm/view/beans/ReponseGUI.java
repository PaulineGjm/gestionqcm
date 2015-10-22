package fr.gestionqcm.view.beans;

public class ReponseGUI {
	
	private Integer idResponse;
	private String wording;
	private Boolean isCorrect;
	
	public ReponseGUI()
	{
		
	}
	
	public ReponseGUI(Integer idResponse, String wording, Boolean isCorrect)
	{
		setIdResponse(idResponse);
		setWording(wording);
		setIsCorrect(isCorrect);
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
}
