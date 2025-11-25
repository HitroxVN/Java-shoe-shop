package models;

public class Categories {
	
	private int id;
	private int name;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getName() {
		return name;
	}



	public void setName(int name) {
		this.name = name;
	}



	public Categories() {
		// TODO Auto-generated constructor stub
	}



	public Categories(int id, int name) {
		super();
		this.id = id;
		this.name = name;
	}



	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + "]";
	}
	
	

}