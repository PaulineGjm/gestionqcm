package fr.gestionqcm.view.beans;

import java.util.Map;

import fr.gestionqcm.model.bo.Theme;

public class TestResultGUI {
	private int globalResult;
	private Map<Theme, Integer> themesResult;

	public int getGlobalResult() {
		return globalResult;
	}

	public void setGlobalResult(int globalResult) {
		this.globalResult = globalResult;
	}

	public Map<Theme, Integer> getThemesResult() {
		return themesResult;
	}

	public void setThemesResult(Map<Theme, Integer> themesResult) {
		this.themesResult = themesResult;
	}
}
