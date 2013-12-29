package com.ak.project.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.ak.project.db.GetMenu;

public class Club implements Serializable {

	private String naam;
	private String adres;
	private ArrayList<Beverage> menu;
	private String id;
	private ArrayList<Beverage> bestellingen;

	public Club(String naam, String adres) {
		setNaam(naam);
		setAdres(adres);
		setId(id);
		bestellingen = new ArrayList<Beverage>();
	}

	public void importMenuList() {
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

	public ArrayList<Beverage> getBestellingen() {
		return bestellingen;
	}

	public void setBestellingen(ArrayList<Beverage> bestellingen) {
		this.bestellingen = bestellingen;
	}

	public double increaseBestelling(String naam) {
		Beverage beverage = null;
		int index = 0;
		for (Beverage b : bestellingen) {
			if (b.getName().equals(naam)) {
				beverage = b;
				index = bestellingen.indexOf(b);
			}
		}
		if (beverage == null) {
			for (Beverage b : menu) {
				if (b.getName().equals(naam)) {
					beverage = b;
					bestellingen.add(beverage);
					index = bestellingen.indexOf(beverage);
				}
			}
		}
		beverage.increaseAmount(1);
		double price = beverage.getPrice() * beverage.getAmount();
		bestellingen.set(index, beverage);
		return price;

	}

	public double decreaseBestelling(String naam) {
		Beverage beverage = null;
		int index = 0;
		for (Beverage b : bestellingen) {
			if (b.getName().equals(naam)) {
				beverage = b;
				index = bestellingen.indexOf(b);
			}
		}
		if (beverage == null) {
			for (Beverage b : menu) {
				if (b.getName().equals(naam)) {
					beverage = b;
					bestellingen.add(beverage);
					index = bestellingen.indexOf(beverage);
				}
			}
		}
		if (beverage.getAmount() != 0) {
			beverage.decreaseAmount(1);
		}
		double price = beverage.getPrice() * beverage.getAmount();
		bestellingen.set(index, beverage);
		return price;
	}
	public double getTotal(){
		double total = 0.00;
		for(Beverage beverage : bestellingen){
			total+=(beverage.getAmount()*beverage.getPrice());
		}
		return total;
	}

}
