package models;

public class ProductStatistic {
	private int id;
	private String name;
	private int sold;
	
	public ProductStatistic(int id, String name, int sold) {
		this.id = id;
		this.name = name;
		this.sold = sold;
	}

	public ProductStatistic() {
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

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "ProductStatistic [id=" + id + ", name=" + name + ", sold=" + sold + "]";
	}
	
	
	
	
}
