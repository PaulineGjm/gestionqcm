package fr.gestionqcm.model.bo;

public class Theme implements Comparable<Theme> {
	private int id;
	private String label;

	public Theme(int id, String label) {
		setId(id);
		setLabel(label);
	}

	public Theme() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int compareTo(Theme t) {
		Integer thisId = this.getId();
		Integer otherId = t.getId();

		return thisId.compareTo(otherId);
	}
}
