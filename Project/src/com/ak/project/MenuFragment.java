package com.ak.project;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class MenuFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater
				.inflate(R.layout.menu_layout, container, false);
		

		ArrayList<String> items = new ArrayList<String>();
		items.add("Stella");
		items.add("I2");
		items.add("I3");
		items.add("I4");
		items.add("I5");
		items.add("I6");
		items.add("I7");
		items.add("I8");
		items.add("I9");
		items.add("I10");
		items.add("I11");
		items.add("I12");
		items.add("I13");
		items.add("I14");
		ScrollView scroll = new ScrollView(getActivity().getApplicationContext());
		TableLayout menulist = new TableLayout(getActivity()
				.getApplicationContext());
		TableRow.LayoutParams param = new TableRow.LayoutParams();
		param.setMargins(0, 0, 15, 0);
		for (String item : items) {
			TableRow row = new TableRow(getActivity().getApplicationContext());
			TextView tekst = new TextView(getActivity().getApplicationContext());
			tekst.setText(item);
			tekst.setLayoutParams(param);
			row.addView(tekst);
			Button plus = new Button(getActivity().getApplicationContext());
			plus.setText("plus");
			plus.setLayoutParams(param);
			row.addView(plus);
			Button min = new Button(getActivity().getApplicationContext());
			min.setText("min");
			min.setLayoutParams(param);
			row.addView(min);
			TextView amount = new TextView(getActivity().getApplicationContext());
			amount.setText("€0.00");
			amount.setLayoutParams(param);
			row.addView(amount);
			TextView total = new TextView(getActivity().getApplicationContext());
			total.setLayoutParams(param);
			total.setText("€0.00");
			row.addView(total);
			menulist.addView(row);


		}
		scroll.setPadding(20, 5, 0, 5);
		scroll.addView(menulist);
		return scroll;
	}
}
