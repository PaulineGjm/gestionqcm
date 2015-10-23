package fr.gestionqcm.view.beans;

import java.util.Date;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Test;

public class EditInscriptionGUI {

	private List<Test> tests;

	private Test testSelected;

	private Date startDateSelected;

	private List<InscriptionTest> inscriptionsTest;

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Test getTestSelected() {
		return testSelected;
	}

	public void setTestSelected(Test testSelected) {
		this.testSelected = testSelected;
	}

	public Date getStartDateSelected() {
		return startDateSelected;
	}

	public void setStartDateSelected(Date startDateSelected) {
		this.startDateSelected = startDateSelected;
	}

	public List<InscriptionTest> getInscriptionsTest() {
		return inscriptionsTest;
	}

	public void setInscriptionsTest(List<InscriptionTest> inscriptionsTest) {
		this.inscriptionsTest = inscriptionsTest;
	}
}
