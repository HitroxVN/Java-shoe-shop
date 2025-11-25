/**
 * Author: Hoàng Văn Trường
 * Created on: Nov 24, 2025
 */
package utils;

import java.util.HashMap;
import java.util.Map;

import models.Users;

public class Validate {
	
	// phần add admin
	public static Map<String, String> validateUsersAdminAdd(Users u){
		Map<String, String> loi = new HashMap<>();
		
		//		họ tên 
		if(u.getFull_name().trim().isEmpty() || u.getFull_name() == null) {
			loi.put("loiTen", "Hãy nhập họ tên!");
		} else if(!u.getFull_name().matches("^[\\p{L} ]+$")) {
			loi.put("loiTen", "Họ tên không được chứa số hoặc ký tự đặc biệt");
		}
		
		//  email
	    if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
	        loi.put("loiEmail", "Hãy nhập email!");
	    } else if (!u.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        loi.put("loiEmail", "Email không hợp lệ!");
	    }
	    
	    //  mật khẩu
	    if (u.getPassword() == null || u.getPassword().trim().isEmpty()) {
	        loi.put("loiPassword", "Hãy nhập mật khẩu!");
	    } else if (u.getPassword().length() < 6) {
	        loi.put("loiPassword", "Mật khẩu phải tối thiểu 6 ký tự!");
	    }
	    
	    if (u.getPhone() == null || !u.getPhone().matches("^\\d{10}$")) {
            loi.put("loiPhone", "Số điện thoại phải gồm 10 chữ số.");
        }
	    
	    if (u.getAddress() == null || u.getAddress().trim().isEmpty()) {
	        loi.put("loiAddress", "Hãy nhập địa chỉ!");
	    }
	    
	    if (u.getRole() == null ||
	            !(u.getRole().equals("customer") ||
	              u.getRole().equals("admin") ||
	              u.getRole().equals("staff"))) {
	            loi.put("loiRole", "Vai trò không hợp lệ!");
	        }
		
		return loi;
	}
	
	
	// phần edit admin
	public static Map<String, String> validateUsersAdminEdit(Users u){
		Map<String, String> loi = new HashMap<>();
		
		//		họ tên 
		if(u.getFull_name().trim().isEmpty() || u.getFull_name() == null) {
			loi.put("loiTen", "Hãy nhập họ tên!");
		} else if(!u.getFull_name().matches("^[\\p{L} ]+$")) {
			loi.put("loiTen", "Họ tên không được chứa số hoặc ký tự đặc biệt");
		}
		
		//  email
	    if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
	        loi.put("loiEmail", "Hãy nhập email!");
	    } else if (!u.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        loi.put("loiEmail", "Email không hợp lệ!");
	    }
	    
	    if (u.getPhone() == null || !u.getPhone().matches("^\\d{10}$")) {
            loi.put("loiPhone", "Số điện thoại phải gồm 10 chữ số.");
        }
	    
	    if (u.getAddress() == null || u.getAddress().trim().isEmpty()) {
	        loi.put("loiAddress", "Hãy nhập địa chỉ!");
	    }
	    
	    if (u.getRole() == null ||
	            !(u.getRole().equals("customer") ||
	              u.getRole().equals("admin") ||
	              u.getRole().equals("staff"))) {
	            loi.put("loiRole", "Vai trò không hợp lệ!");
	        }
		
		return loi;
	}
	
	// phần sửa của user
	public static Map<String, String> validateUser(Users u){
		Map<String, String> loi = new HashMap<>();
		
		//		họ tên 
		if(u.getFull_name().trim().isEmpty() || u.getFull_name() == null) {
			loi.put("loiTen", "Hãy nhập họ tên!");
		} else if(!u.getFull_name().matches("^[\\p{L} ]+$")) {
			loi.put("loiTen", "Họ tên không được chứa số hoặc ký tự đặc biệt");
		}
		
		//  email
	    if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
	        loi.put("loiEmail", "Hãy nhập email!");
	    } else if (!u.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        loi.put("loiEmail", "Email không hợp lệ!");
	    }
	    
	    // sđt
	    if (u.getPhone() == null || !u.getPhone().matches("^\\d{10}$")) {
            loi.put("loiPhone", "Số điện thoại phải gồm 10 chữ số.");
        }
	    
	    // address
	    if (u.getAddress() == null || u.getAddress().trim().isEmpty()) {
	        loi.put("loiAddress", "Hãy nhập địa chỉ!");
	    }
		
		return loi;
	}

}
