package fr.gestionqcm.model.bo;

public class Section {
	private int idTest;
	private int idTheme;
	private int nbQuestions;
	
	
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	public int getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public int getNbQuestions() {
		return nbQuestions;
	}
	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}
}
