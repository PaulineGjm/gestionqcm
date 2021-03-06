package fr.gestionqcm.model.bo;

import java.util.List;

public class Test {

	private int testId;

	private String name;

	private int testDuration;

	private int currentThreshold;

	private int acquisitionThreshold;

	private List<Section> sections;

	private boolean isArchived;

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
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

	public int getCurrentThreshold() {
		return currentThreshold;
	}

	public void setCurrentThreshold(int currentThreshold) {
		this.currentThreshold = currentThreshold;
	}

	public int getAcquisitionThreshold() {
		return acquisitionThreshold;
	}

	public void setAcquisitionThreshold(int acquisitionThreshold) {
		this.acquisitionThreshold = acquisitionThreshold;
	}
}
