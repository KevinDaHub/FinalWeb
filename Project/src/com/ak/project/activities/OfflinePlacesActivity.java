package com.ak.project.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.project.R;
import com.ak.project.model.Quickorder;

public class OfflinePlacesActivity extends Activity{
	private ArrayList<String> plaatsen = new ArrayList<String>();
	private Quickorder quickorder;

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			quickorder.setMenuList(((TextView) v).getText().toString());
			Intent myIntent = new Intent(OfflinePlacesActivity.this, OfflineTabActivity.class);
			Bundle data = new Bundle();
			data.putSerializable("quickorder", quickorder);
			myIntent.putExtras(data);
			startActivity(myIntent);
			startActivity(myIntent);

		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		Intent i = getIntent();
		Bundle data = i.getExtras();
		quickorder = (Quickorder) data.getSerializable("quickorder");
		plaatsen = quickorder.getClubs();
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

	public void finish() {
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		super.finish();

	}
}
