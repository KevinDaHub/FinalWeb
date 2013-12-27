package com.ak.project.activities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
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
			//writeXml();
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
			xml.startTag(null, "club");
			xml.startTag(null, "name");
			xml.text("rio");
			xml.endTag(null, "name");
			xml.startTag(null, "adres");
			xml.text("sint katelijne");
			xml.endTag(null, "adres");
			xml.startTag(null, "menu");
			xml.startTag(null, "drank");
			xml.startTag(null, "name");
			xml.text("cola");
			xml.endTag(null, "name");
			xml.startTag(null, "type");
			xml.text("soft");
			xml.endTag(null, "type");
			xml.startTag(null, "prijs");
			xml.text("1.8");
			xml.endTag(null, "prijs");
			xml.endTag(null, "drank");
			xml.endTag(null, "menu");
			xml.endTag(null, "club");
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
		int currentSection = 0;
		String name ="";
		String adres = "";
		ArrayList<Beverage> menu = new ArrayList<Beverage>();
		ArrayList<Club> clublist = new ArrayList<Club>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xmlparser = factory.newPullParser();

			File file = new File(getFilesDir(), "quickorder.xml");

			FileInputStream fis = new FileInputStream(file);
			xmlparser.setInput(new InputStreamReader(fis));
			int eventType = xmlparser.getEventType();
			String nodeName = xmlparser.getName();
			if (nodeName.contentEquals("quickorder")) {
				currentSection = 1;
			}

			switch (currentSection) {
			case 0:
				break;
			case 1:
				if(nodeName.contentEquals("name")){
					name = xmlparser.nextText();
				}
				if(nodeName.contentEquals("adres")){
					adres = xmlparser.nextText();
					Club club = new Club(name, adres, "");
					clublist.add(club);
					
				}
				if(nodeName.contentEquals("menu")){
					currentSection = 2;
				}
			}

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * String tekst = ""; try { InputStream is =
		 * openFileInput("quickorder.xml"); if(is != null){ InputStreamReader
		 * isreader = new InputStreamReader(is); BufferedReader buffreader = new
		 * BufferedReader(isreader); String recieve = ""; StringBuilder sBuilder
		 * = new StringBuilder(); while((recieve = buffreader.readLine())!=
		 * null){ sBuilder.append(recieve); } is.close(); tekst =
		 * sBuilder.toString(); Log.i("gelezen xml", tekst); } } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	public void offline(View v) {
		//readXML();
		quickorder.importClubs();
		Intent myIntent = new Intent(this, OfflinePlacesActivity.class);
		Bundle data = new Bundle();
		data.putSerializable("quickorder", quickorder);
		myIntent.putExtras(data);
		startActivity(myIntent);
	}
}
