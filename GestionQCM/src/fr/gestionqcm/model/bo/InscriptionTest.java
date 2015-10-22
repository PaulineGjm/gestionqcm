package fr.gestionqcm.model.bo;

import java.util.Date;

public class InscriptionTest {

	private int inscriptionId;
	private int testId;
	private Date inscriptionDate;
	private int userId;
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

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
