package models;

public class Orders {
	
	private int id;
	private int user_id;
	private String shipping_address;
	private double total_amount;
	private String payment_method;
	private String status;
	private String created_at;
	private String updated_at;
	
	
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getUser_id() {
		return user_id;
	}




	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}




	public String getShipping_address() {
		return shipping_address;
	}




	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}




	public double getTotal_amount() {
		return total_amount;
	}




	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}




	public String getPayment_method() {
		return payment_method;
	}




	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getCreated_at() {
		return created_at;
	}




	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}




	public String getUpdated_at() {
		return updated_at;
	}




	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}




	public Orders() {
		// TODO Auto-generated constructor stub
	}




	public Orders(int id, int user_id, String shipping_address, double total_amount, String payment_method,
			String status, String created_at, String updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.shipping_address = shipping_address;
		this.total_amount = total_amount;
		this.payment_method = payment_method;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}




	@Override
	public String toString() {
		return "Orders [id=" + id + ", user_id=" + user_id + ", shipping_address=" + shipping_address
				+ ", total_amount=" + total_amount + ", payment_method=" + payment_method + ", status=" + status
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	

}
