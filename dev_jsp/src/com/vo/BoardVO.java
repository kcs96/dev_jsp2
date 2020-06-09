package com.vo;

public class BoardVO {
	//board_master_t
	private int  bm_no     = 0; 
	private String  bm_title  = ""; 
	private String  bm_write  = ""; 
	private String  bm_email  = ""; 
	private String  bm_content= ""; 
	private int  	bm_hit    = 0; 
	private String  bm_date   = ""; 
	private int  	bm_group  = 0; 
	private int  	bm_tos    = 0; 
	private int  	bm_step   = 0; 
	private String  bm_pw     = ""; 
	
	//board_sub_t
	private int  bm_seq  = 0; 
	private String  bm_file = "";
	private double  bm_size = 0.0;
	public int getBm_no() {
		return bm_no;
	}
	public void setBm_no(int bm_no) {
		this.bm_no = bm_no;
	}
	public String getBm_title() {
		return bm_title;
	}
	public void setBm_title(String bm_title) {
		this.bm_title = bm_title;
	}
	public String getBm_write() {
		return bm_write;
	}
	public void setBm_write(String bm_write) {
		this.bm_write = bm_write;
	}
	public String getBm_email() {
		return bm_email;
	}
	public void setBm_email(String bm_email) {
		this.bm_email = bm_email;
	}
	public String getBm_content() {
		return bm_content;
	}
	public void setBm_content(String bm_content) {
		this.bm_content = bm_content;
	}
	public int getBm_hit() {
		return bm_hit;
	}
	public void setBm_hit(int bm_hit) {
		this.bm_hit = bm_hit;
	}
	public String getBm_date() {
		return bm_date;
	}
	public void setBm_date(String bm_date) {
		this.bm_date = bm_date;
	}
	public int getBm_group() {
		return bm_group;
	}
	public void setBm_group(int bm_group) {
		this.bm_group = bm_group;
	}
	public int getBm_tos() {
		return bm_tos;
	}
	public void setBm_tos(int bm_tos) {
		this.bm_tos = bm_tos;
	}
	public int getBm_step() {
		return bm_step;
	}
	public void setBm_step(int bm_step) {
		this.bm_step = bm_step;
	}
	public String getBm_pw() {
		return bm_pw;
	}
	public void setBm_pw(String bm_pw) {
		this.bm_pw = bm_pw;
	}
	public int getBm_seq() {
		return bm_seq;
	}
	public void setBm_seq(int bm_seq) {
		this.bm_seq = bm_seq;
	}
	public String getBm_file() {
		return bm_file;
	}
	public void setBm_file(String bm_file) {
		this.bm_file = bm_file;
	}
	public double getBm_size() {
		return bm_size;
	}
	public void setBm_size(double bm_size) {
		this.bm_size = bm_size;
	}
	
	
}
