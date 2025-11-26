/**
 * Author: Hoàng Văn Trường
 * Created on: Nov 21, 2025
 */
package models;

public class Users {
	
	private int id;
	private String full_name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String role;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFull_name() {
		return full_name;
	}


	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Users() {
		// TODO Auto-generated constructor stub
	}


	public Users(int id, String full_name, String email, String password, String phone, String address, String role) {
		super();
		this.id = id;
		this.full_name = full_name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", full_name=" + full_name + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", address=" + address + ", role=" + role + "]";
	}
	
	

}