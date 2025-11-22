package models;

public class Products {
	private int id;
	private int category_id;
	private String name;
	private String description;
	private double price;
	private String image;
	private String size;
	private int stock;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Products() {
		// TODO Auto-generated constructor stub
	}


	public Products(int id, int category_id, String name, String description, double price, String image, String size,
			int stock) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.size = size;
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Products [id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", image=" + image + ", size=" + size + ", stock=" + stock + "]";
	}
}
