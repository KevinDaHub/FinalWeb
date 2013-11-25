package com.ak.quickorder;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class PlacesActivity extends Activity {

	private ArrayList<String> plaatsen = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		Log.d("activity", "oncreate places");

	}
	
	public void onResume(){
		Log.d("activity", "onresume places");
		plaatsen.add("R1");
		plaatsen.add("R2");
		plaatsen.add("R3");
		plaatsen.add("R4");
		plaatsen.add("R5");
		addList();
		super.onResume();
	}

	private void addList(){
		LinearLayout lo = (LinearLayout) findViewById(R.id.list);
		for (String plaats : plaatsen) {
			Log.d("plaats", plaats);
			TextView tekst = new TextView(this);
			tekst.setText(plaats);
			tekst.setTextSize(20);
			ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);
			lo.addView(tekst);

		}
	}
	private void updateList(View view){
		addList();
		Log.d("klik", "kliktest");
	}
}
