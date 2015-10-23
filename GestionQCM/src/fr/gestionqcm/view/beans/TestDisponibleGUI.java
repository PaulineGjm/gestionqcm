package fr.gestionqcm.view.beans;

import java.io.Serializable;
import java.util.Date;

public class TestDisponibleGUI implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int inscriptionID;

	private String name;

	private int testDuration;

	private int timeRemaining;

	private int questionPosition;

	private Date testStartDate;

	public TestDisponibleGUI() {

	}
	
	public TestDisponibleGUI(int inscriptionID) {
		setInscriptionID(inscriptionID);
	}

	public TestDisponibleGUI(int inscriptionID, String name,
			int testDuration, int timeRemaining, int questionPosition,
			Date testStartDate) {
		setInscriptionID(inscriptionID);
		setName(name);
		setTestDuration(testDuration);
		setTimeRemaining(timeRemaining);
		setQuestionPosition(questionPosition);
		setTestStartDate(testStartDate);
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

	public int getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
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

	public Date getTestStartDate() {
		return testStartDate;
	}

	public void setTestStartDate(Date testStartDate) {
		this.testStartDate = testStartDate;
	}

}
