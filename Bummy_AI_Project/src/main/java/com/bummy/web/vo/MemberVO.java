package com.bummy.web.vo;

public class MemberVO {
	private String user_id,user_pw,user_pwc,user_phone,user_name,user_email,user_belong,user_type,user_img;
	private int check_time;
	private Boolean user_attend;

	public MemberVO(String user_id, String user_pw, String user_phone, String user_name, String user_email, String user_belong, String user_type, String user_img, Boolean user_attend, int  check_time) {
		super();
		setUser_id(user_id);
		setUser_pw(user_pw);
		setUser_name(user_name);
		setUser_phone(user_phone);
		setUser_email(user_email);
		setUser_belong(user_belong);
		setUser_type(user_type);
		setUser_img(user_img);
		setUser_attend(user_attend);
		setCheck_time(check_time);
	}
	
	public MemberVO(String user_id, String user_pw) {
		setUser_id(user_id);
		setUser_pw(user_pw);
	}

	public MemberVO(String user_id, String user_belong, String user_type) {
		setUser_id(user_id);
		setUser_belong(user_belong);
		setUser_type(user_type);
	}

	public MemberVO(String user_id) {
		setUser_id(user_id);
	}

	public MemberVO(String user_belong, int check_time) {
		setUser_belong(user_belong);
		setCheck_time(check_time);
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pwc() {
		return user_pwc;
	}
	public void setUser_pwc(String user_pwc) {
		this.user_pwc = user_pwc;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_belong() {
		return user_belong;
	}
	public void setUser_belong(String user_belong) {
		this.user_belong = user_belong;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public Boolean getUser_attend() {
		return user_attend;
	}
	public void setUser_attend(Boolean user_attend) {
		this.user_attend = user_attend;
	}
	
	public int getCheck_time() {
		return check_time;
	}

	public void setCheck_time(int check_time) {
		this.check_time = check_time;
	}

	@Override
	public String toString() {
		return "MemberVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_pwc=" + user_pwc + ", user_phone="
				+ user_phone + ", user_name=" + user_name + ", user_email=" + user_email + ", user_belong="
				+ user_belong + ", user_type=" + user_type + ", user_img=" + user_img + ", check_time=" + check_time
				+ ", user_attend=" + user_attend + "]";
	}	
}
