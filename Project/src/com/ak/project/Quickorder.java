package com.ak.project;

public class Quickorder {

	private User user;
	private Database db;
	private Encrypter enc;
	
	public Quickorder(){
	}
	
	public String login(String name,String password){
		String login = "Invalid username/password";
		Encrypter enc = new Encrypter();
		password = enc.encrypt(password);
		Database db = new Database(name, password);
		this.user = db.getuser();
		if(user != null){
			login = "ok";
		}
		return login;
	}
}
