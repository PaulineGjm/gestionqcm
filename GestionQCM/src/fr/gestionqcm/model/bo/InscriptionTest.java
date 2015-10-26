package fr.gestionqcm.model.bo;

import java.util.Date;

public class InscriptionTest {

	private int inscriptionId;
	private Test test;
	private Date inscriptionDate;
	private Stagiaire user;
	private Date testStartDate;
	private int timesRemaining;
	private int issueNumber;
	private int questionPosition;

	public int getInscriptionId() {
		return inscriptionId;
	}

	public void setInscriptionId(int inscriptionId) {
		this.inscriptionId = inscriptionId;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public Stagiaire getUser() {
		return user;
	}

	public void setUser(Stagiaire users) {
		this.user = users;
	}

	public Date getTestStartDate() {
		return testStartDate;
	}

	public void setTestStartDate(Date testStartDate) {
		this.testStartDate = testStartDate;
	}

	public int getTimesRemaining() {
		return timesRemaining;
	}

	public void setTimesRemaining(int timesRemaining) {
		this.timesRemaining = timesRemaining;
	}

	public int getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}

	public int getQuestionPosition() {
		return questionPosition;
	}

	public void setQuestionPosition(int questionPosition) {
		this.questionPosition = questionPosition;
	}
}
