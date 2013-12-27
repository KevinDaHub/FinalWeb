package com.ak.project.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.ak.project.db.Encrypter;
import com.ak.project.db.GetClubs;
import com.ak.project.db.GetUser;
import com.ak.project.db.UpdateSaldo;

public class Quickorder implements Serializable {

	private User user = null;
	private ArrayList<Club> clubs;
	private Club selectedClub;

	public Quickorder() {
		clubs = new ArrayList<Club>();
	}

	public String login(String name, String password) {
		GetUser userdb = new GetUser();
		String login = "Invalid username/password";
		Encrypter enc = new Encrypter();
		password = enc.encrypt(password);

		this.user = userdb.getuser(name, password);
		if (user != null) {
			login = "ok";
		}
		return login;
	}

	public String getCurrentSaldo() {
		return "" + user.getSaldo();
	}

	public String getTotal() {
		return "" + selectedClub.getTotal();
	}

	public boolean updateSaldo(double total) {
		boolean success = false;
		if (total < user.getSaldo()) {
			double saldo = user.getSaldo() - total;
			user.setSaldo(saldo);
			UpdateSaldo us = new UpdateSaldo(user.getName(), user.getSaldo());
			us.execute();
			success = true;
		}
		return success;
	}

	public String min(String naam) {
		String saldo;
		saldo = "" + selectedClub.decreaseBestelling(naam);
		return saldo;
	}

	public String plus(String naam) {
		String saldo;
		saldo = "" + selectedClub.increaseBestelling(naam);
		return saldo;
	}

	public void importClubs() {
		if (user != null) {
			GetClubs clubsdb = new GetClubs();
			clubs = clubsdb.getclubs();
		} else {
			Club c1 = new Club("tes1t", "ergens", "1");
			Club c2 = new Club("test2", "ergens", "2");
			Club c3 = new Club("test3", "ergens", "3");
			Club c4 = new Club("test4", "ergens", "4");
			clubs.add(c1);
			clubs.add(c2);
			clubs.add(c3);
			clubs.add(c4);

		}

	}

	public void setMenuList(String naam) {
		if (user != null) {
			for (Club club : clubs) {
				if (club.getNaam().equals(naam)) {
					selectedClub = club;
					selectedClub.importMenuList();
				}
			}
		}else{
			for(Club club : clubs){
				if(club.getNaam().equals(naam)){
					selectedClub = club;
					ArrayList<Beverage> menu = new ArrayList<Beverage>();
					menu.add(new Beverage("1", "cola", "soft", 1.80));
					menu.add(new Beverage("2", "heineken", "soft", 1.80));
					menu.add(new Beverage("3", "stela 25cl", "bier", 1.80));
					menu.add(new Beverage("4", "eristoff w", "strong", 3));
					menu.add(new Beverage("5", "chateau", "wijn", 2.5));
					menu.add(new Beverage("6", "fanta", "soft", 1.80));
					menu.add(new Beverage("7", "whiskey", "strong", 3.5));					
					club.setMenu(menu);
				}
			}
		}
	}

	public ArrayList<String> getClubs() {
		ArrayList<String> clubnamen = new ArrayList<String>();
		for (Club club : clubs) {
			clubnamen.add(club.getNaam());
		}
		return clubnamen;
	}

	public void setClubs(ArrayList<Club> clubs) {
		this.clubs = clubs;
	}

	public Club getSelectedClub() {
		return selectedClub;
	}

	public void setSelectedClub(Club selectedClub) {
		this.selectedClub = selectedClub;
	}

	public User getUser() {
		return user;
	}
}
