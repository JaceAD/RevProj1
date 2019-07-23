package com.revature.project1.beans;

public class Employee {
	
	private int id;
	private String fName;
	private String lName;
	private String pWord;
	private String email;
	private char accessLvl;
	
	
	
	public Employee(int id, String fName, String lName, String pWord, String email, char accessLvl) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.pWord = pWord;
		this.email = email;
		this.accessLvl = accessLvl;
	}
	
	public int getId() {
		return id;
	}
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public String getpWord() {
		return pWord;
	}
	public String getEmail() {
		return email;
	}
	public char getAccessLvl() {
		return accessLvl;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setpWord(String pWord) {
		this.pWord = pWord;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAccessLvl(char accessLvl) {
		this.accessLvl = accessLvl;
	}
	
	
	
}
