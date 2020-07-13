package main.obj;

public class Feeling {
	private int id;
	private String name;
	
	public Feeling() {}
	
	public Feeling(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getTrimmedName() {
		return name.replace(" ", "");
	}
}
