package com.example.WebScrapping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;






@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userid;

	String name;
	String email;
	
	@NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String password;

	String codechef_username;
	String hackerank_username;
	String codeforces_username;
	String leetcode_username;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCodechef_username() {
		return codechef_username;
	}
	public void setCodechef_username(String codechef_username) {
		this.codechef_username = codechef_username;
	}
	public String getHackerank_username() {
		return hackerank_username;
	}
	public void setHackerank_username(String hackerank_username) {
		this.hackerank_username = hackerank_username;
	}
	public String getCodeforces_username() {
		return codeforces_username;
	}
	public void setCodeforces_username(String codeforces_username) {
		this.codeforces_username = codeforces_username;
	}
	public String getLeetcode_username() {
		return leetcode_username;
	}
	public void setLeetcode_username(String leetcode_username) {
		this.leetcode_username = leetcode_username;
	}
	
	

	
	


}
