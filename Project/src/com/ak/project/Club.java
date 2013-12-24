package com.ak.project;

import java.io.Serializable;
import java.util.ArrayList;

public class Club implements Serializable{

	private String naam;
	private String adres;
	private ArrayList<Beverage> menu;
	private String id;

	public Club(String naam, String adres,String id) {
		setNaam(naam);
		setAdres(adres);
		setId(id);
	}

	public void importMenuList(){
		GetMenu gm = new GetMenu();
		menu = gm.getMenu(this.getId());
	}
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public ArrayList<Beverage> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Beverage> menu) {
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
