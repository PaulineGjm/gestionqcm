package fr.gestionqcm.view.beans;

public class TestEnCoursGUI {

	private Integer inscriptionID;

	private String name;

	private Integer timeRemaining;

	private Integer questionPosition;

	private Integer nbQuestion;

	public TestEnCoursGUI(Integer inscriptionID, String name,
			Integer timeRemaining, Integer questionPosition, Integer nbQuestion)
	{
		setInscriptionID(inscriptionID);
		setName(name);
		setTimeRemaining(timeRemaining);
		setQuestionPosition(questionPosition);
		setNbQuestion(nbQuestion);
	}

	public TestEnCoursGUI() {

	}

	public int getInscriptionID() {
		return inscriptionID;
	}

	public void setInscriptionID(int inscriptionID) {
		this.inscriptionID = inscriptionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public int getQuestionPosition() {
		return questionPosition;
	}

	public void setQuestionPosition(int questionPosition) {
		this.questionPosition = questionPosition;
	}

	public int getNbQuestion() {
		return nbQuestion;
	}

	public void setNbQuestion(int nbQuestion) {
		this.nbQuestion = nbQuestion;
	}

}
