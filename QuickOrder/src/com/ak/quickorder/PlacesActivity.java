package com.ak.quickorder;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class PlacesActivity extends Activity {

	private ArrayList<String> plaatsen = new ArrayList<String>();
	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent myIntent = new Intent(PlacesActivity.this,
					TabLayoutActivity.class);
			startActivity(myIntent);

		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		plaatsen.add("R1");
		plaatsen.add("R2");
		plaatsen.add("R3");
		plaatsen.add("R4");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");
		plaatsen.add("R5");

		addList();

	}

	public void onResume() {

		super.onResume();
	}

	private void addList() {
		LinearLayout lo = (LinearLayout) findViewById(R.id.list);
		for (String plaats : plaatsen) {
			TextView tekst = new TextView(this);
			tekst.setText(plaats);
			tekst.setTextSize(20);
			tekst.setOnClickListener((android.view.View.OnClickListener) clickListener);
			lo.addView(tekst);

		}
	}

	private void updateList(View view) {
		addList();
	}
}
