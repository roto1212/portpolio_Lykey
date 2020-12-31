package com.Luce.vo;

import java.util.Date;

public class LyKeyVO {
	String email;
	String pw;
	Date signup_date;
	
	@Override
	public String toString() {
		return "LyKeyVO [email=" + email + ", pw=" + pw + ", signup_date=" + signup_date + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getSignup_date() {
		return signup_date;
	}
	public void setSignup_date(Date signup_date) {
		this.signup_date = signup_date;
	}
	
	
	
	
}
