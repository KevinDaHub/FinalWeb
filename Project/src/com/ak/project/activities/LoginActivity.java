package com.ak.project.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.ak.project.R;
import com.ak.project.model.Beverage;
import com.ak.project.model.Club;
import com.ak.project.model.Quickorder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Quickorder quickorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		quickorder = new Quickorder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void logIn(View view) {

		EditText namefield = (EditText) findViewById(R.id.editText1);
		String name = namefield.getText().toString();
		EditText passwordfield = (EditText) findViewById(R.id.editText2);
		String password = passwordfield.getText().toString();
		String login = quickorder.login(name, password);
		if (login.equals("ok")) {
			quickorder.importClubs();
			writeXml();
			Intent myIntent = new Intent(this, PlacesActivity.class);
			Bundle data = new Bundle();
			data.putSerializable("quickorder", quickorder);
			myIntent.putExtras(data);
			startActivity(myIntent);
		} else {
			Toast.makeText(getApplicationContext(), login, Toast.LENGTH_LONG)
					.show();
		}

	}

	private void writeXml() {
		XmlSerializer xml = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			xml.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			xml.startTag(null, "quickorder");
			for (Club club : quickorder.getClublist()) {
				xml.startTag(null, "club");
				xml.startTag(null, "clubname");
				xml.text(club.getNaam());
				xml.endTag(null, "clubname");
				xml.startTag(null, "adres");
				xml.text(club.getAdres());
				xml.endTag(null, "adres");
				xml.startTag(null, "menu");
				for (Beverage beverage : club.getMenu()) {
					xml.startTag(null, "drank");
					xml.startTag(null, "beveragename");
					xml.text(beverage.getName());
					xml.endTag(null, "beveragename");
					xml.startTag(null, "type");
					xml.text(beverage.getType());
					xml.endTag(null, "type");
					xml.startTag(null, "prijs");
					xml.text(beverage.getPrice() + "");
					xml.endTag(null, "prijs");
					xml.endTag(null, "drank");
				}
				xml.endTag(null, "menu");
				xml.endTag(null, "club");
			}
			xml.endTag(null, "quickorder");
			xml.endDocument();

			String tekst = writer.toString();

			OutputStreamWriter outwriter = new OutputStreamWriter(
					openFileOutput("quickorder.xml", Context.MODE_PRIVATE));
			outwriter.write(tekst);
			outwriter.close();
			Log.i("xml", tekst);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void readXML() {
		XmlPullParser parser = Xml.newPullParser();
		try {
			File file = new File(getFilesDir(), "quickorder.xml");
			InputStream is = new FileInputStream(file);
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, null);
			parseXML(parser);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseXML(XmlPullParser parser) {
		try {
			ArrayList<Club> clubs = null;
			ArrayList<Beverage>menu = null;
			Beverage currentbeverage = null;
			int eventType = parser.getEventType();
			Club currentclub = null;
			String clubname = null;
			String clubadres = null;
			String beveragename = null;
			String beveragetype = null;
			double beveragepice;
			while(eventType != XmlPullParser.END_DOCUMENT){
				String name = null;
				switch(eventType){
				case XmlPullParser.START_DOCUMENT:
					clubs = new ArrayList<Club>();
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if(name == "club" && currentclub != null){
						currentclub.setMenu(menu);
						clubs.add(currentclub);
						currentclub = null;
					}else if(name == "clubname"){
						clubname = parser.nextText();
					}else if(name =="adres"){
						clubadres = parser.nextText();
						currentclub = new Club(clubname, clubadres);
					}else if(name=="menu" && menu != null){
						menu = new ArrayList<Beverage>();
					}else if(name == "drank" && currentbeverage != null){
						currentbeverage = null;
					}else if(name == "beveragename"){
						beveragename = parser.nextText();
					}else if(name == "type"){
						beveragetype = parser.nextText();
						
					}
					else if(name =="prijs"){
						beveragepice = Double.parseDouble(parser.nextText());
						currentbeverage = new Beverage(beveragename, beveragetype, beveragepice);
						menu.add(currentbeverage);
						
					}
					break;
				}
				eventType = parser.next();
				
			}
			quickorder.setClubs(clubs);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void offline(View v) {
		readXML();
		quickorder.importClubs();
		Intent myIntent = new Intent(this, OfflinePlacesActivity.class);
		Bundle data = new Bundle();
		data.putSerializable("quickorder", quickorder);
		myIntent.putExtras(data);
		startActivity(myIntent);
	}
}
