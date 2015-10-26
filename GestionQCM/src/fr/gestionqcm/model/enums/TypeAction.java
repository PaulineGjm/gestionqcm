package fr.gestionqcm.model.enums;

public enum TypeAction {
	add, get, edit, delete, save;

	public static TypeAction fromString(String value) {
		TypeAction typeActionFound = null;
		if (value != null) {
			for (TypeAction typeAction : TypeAction.values()) {
				if (typeAction.toString().equals(value)) {
					typeActionFound = typeAction;
				}
			}
		}
		return typeActionFound;
	}
}
