package fr.gestionqcm.view.beans;

public class SelectQuestionGUI {
	
	private Integer idQuestion;
	private Integer isAnswered;
	private Boolean isBranded;
	
	public SelectQuestionGUI()
	{
		
	}
	
	public SelectQuestionGUI(Integer idQuestion, Integer isAnswered, Boolean isBranded)
	{
		setIdQuestion(idQuestion);
		setIsAnswered(isAnswered);
		setIsBranded(isBranded);
	}
	
	public Integer getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Integer getIsAnswered() {
		return isAnswered;
	}
	public void setIsAnswered(Integer isAnswered) {
		this.isAnswered = isAnswered;
	}
	public Boolean getIsBranded() {
		return isBranded;
	}
	public void setIsBranded(Boolean isBranded) {
		this.isBranded = isBranded;
	}

}
