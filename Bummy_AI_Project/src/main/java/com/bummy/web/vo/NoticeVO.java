package com.bummy.web.vo;

import java.util.Date;

public class NoticeVO {
	private int level,notice_notiNO,notice_parentNO;
	private String notice_title,notice_content,notice_filename,notice_id,notice_name;
	Date notice_Date;
	
	
	public NoticeVO(int notice_notiNO) {
		setNotice_notiNO(notice_notiNO);
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNotice_notiNO() {
		return notice_notiNO;
	}
	public void setNotice_notiNO(int notice_notiNO) {
		this.notice_notiNO = notice_notiNO;
	}
	public int getNotice_parentNO() {
		return notice_parentNO;
	}
	public void setNotice_parentNO(int notice_parentNO) {
		this.notice_parentNO = notice_parentNO;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public Date getNotice_Date() {
		return notice_Date;
	}
	public void setNotice_Date(Date notice_Date) {
		this.notice_Date = notice_Date;
	}
	
	public String getNotice_filename() {
		return notice_filename;
	}
	public void setNotice_filename(String notice_filename) {
		this.notice_filename = notice_filename;
	}
	public String getNotice_name() {
		return notice_name;
	}
	public void setNotice_name(String notice_name) {
		this.notice_name = notice_name;
	}
	@Override
	public String toString() {
		return "NoticeVO [level=" + level + ", notice_notiNO=" + notice_notiNO + ", notice_parentNO=" + notice_parentNO
				+ ", notice_title=" + notice_title + ", notice_content=" + notice_content + ", notice_filename="
				+ notice_filename + ", notice_id=" + notice_id + ", notice_name=" + notice_name + ", notice_Date="
				+ notice_Date + "]";
	}
	

}