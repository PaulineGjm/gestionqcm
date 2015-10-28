package fr.gestionqcm.view.beans;

import java.util.Date;
import java.util.List;

import fr.gestionqcm.model.bo.InscriptionTest;
import fr.gestionqcm.model.bo.Promotion;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.util.DateUtils;

public class EditInscriptionGUI {

	public enum FormFields {
		inscriptionTestSelected, startDateSelected, startHourSelected, testSelected, usersSelected, users;
	}

	private List<Test> tests;

	private Test testSelected;

	private Date dateSelected;

	private List<InscriptionTest> inscriptionsTest;

	private List<InscriptionTest> subscribedInscriptionsTest;

	private List<Promotion> promotions;

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Date getDateSelected() {
		return dateSelected;
	}

	public void setDateSelected(Date dateSelected) {
		this.dateSelected = dateSelected;
	}

	public String getFormatDateSelected() {
		return DateUtils.dateToString(dateSelected);
	}

	public String getStartDateSelected() {
		return DateUtils.getDateFromDate(dateSelected);
	}

	public String getStartHourSelected() {
		return DateUtils.getDateHourDate(dateSelected);
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
