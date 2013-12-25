package com.ak.project.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.ak.project.db.Encrypter;
import com.ak.project.db.GetClubs;
import com.ak.project.db.GetUser;

import android.os.Parcel;
import android.os.Parcelable;

public class Quickorder implements Serializable {

	private User user;
	private Encrypter enc;
	private ArrayList<Club> clubs;
	private Club selectedClub;

	public Quickorder() {
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
	public String getCurrentSaldo(){
		return ""+user.getSaldo();
	}
	public String getTotal(){
		return ""+ selectedClub.getTotal();
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
		GetClubs clubsdb = new GetClubs();
		clubs = clubsdb.getclubs();

	}

	public void setMenuList(String naam) {
		for (Club club : clubs) {
			if (club.getNaam().equals(naam)) {
				selectedClub = club;
				selectedClub.importMenuList();
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
}
