package com.ak.quickorder;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MenuActivity extends Activity {

	private ArrayList<String> lijst;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		lijst = new ArrayList<String>();
		lijst.add("Stella");
		lijst.add("heinekenyuk");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		lijst.add("random");
		TableLayout menulist = (TableLayout) findViewById(R.id.menulist);
		for (String drank : lijst) {
			TableRow row = new TableRow(this);
			TextView tekst = new TextView(this);
			tekst.setText(drank);
			row.addView(tekst);

			Button plus = new Button(this);
			plus.setText("plus");
			row.addView(plus);

			Button min = new Button(this);
			min.setText("min");
			row.addView(min);

			menulist.addView(row);

		}

	}

	public void onResume() {

		super.onResume();
	}
}
