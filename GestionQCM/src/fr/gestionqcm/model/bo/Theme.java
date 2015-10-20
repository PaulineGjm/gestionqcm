package fr.gestionqcm.model.bo;

public class Theme {
	private int id;
	private String label;
	
	public Theme(int id, String label)
	{
		setId(id);
		setLabel(label);
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
}
