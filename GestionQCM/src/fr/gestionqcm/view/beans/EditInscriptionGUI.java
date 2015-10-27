package fr.gestionqcm.view.beans;

import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.bo.Test;

public class EditInscriptionGUI {

	public enum FormFields {
		inscriptionTestSelected, startDateSelected, startHourSelected, testSelected, usersSelected;
	}

	private List<Test> tests;

	private Test testSelected;

	private String startDateSelected;

	private String startHourSelected;

	private List<InscriptionTest> inscriptionsTest;

	private List<InscriptionTest> subscribedInscriptionsTest;

	private List<Promotion> promotions;

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public String getStartDateSelected() {
		return startDateSelected;
	}

	public void setStartDateSelected(String startDateSelected) {
		this.startDateSelected = startDateSelected;
	}

	public String getStartHourSelected() {
		return startHourSelected;
	}

	public void setStartHourSelected(String startHourSelected) {
		this.startHourSelected = startHourSelected;
	}

	public Test getTestSelected() {
		return testSelected;
	}

	public void setTestSelected(Test testSelected) {
		this.testSelected = testSelected;
	}

	public List<InscriptionTest> getInscriptionsTest() {
		return inscriptionsTest;
	}

	public void setInscriptionsTest(List<InscriptionTest> inscriptionsTest) {
		this.inscriptionsTest = inscriptionsTest;
	}

	public List<InscriptionTest> getSubscribedInscriptionsTest() {
		return subscribedInscriptionsTest;
	}

	public void setSubscribedInscriptionsTest(
			List<InscriptionTest> subscribedInscriptionsTest) {
		this.subscribedInscriptionsTest = subscribedInscriptionsTest;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
}
