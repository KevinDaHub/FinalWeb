package com.ak.project.view;

import java.util.ArrayList;

import com.ak.project.R;
import com.ak.project.R.layout;
import com.ak.project.activities.TabActivity;
import com.ak.project.model.Beverage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class WineFragment extends Fragment {

	private ArrayList<Beverage> menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final TabActivity activity = (TabActivity) getActivity();
		menu = activity.getMenu();
		View rootView = inflater
				.inflate(R.layout.menu_layout, container, false);

		ScrollView scroll = new ScrollView(getActivity()
				.getApplicationContext());
		TableLayout menulist = new TableLayout(getActivity()
				.getApplicationContext());
		TableRow.LayoutParams param = new TableRow.LayoutParams();
		param.setMargins(0, 0, 15, 0);
		final String sid = "2";
		int id = 0;
		for (Beverage beverage : menu) {
			if (beverage.getType().equals("wijn")) {
				id++;
				TableRow row = new TableRow(getActivity()
						.getApplicationContext());

				TextView tekst = new TextView(getActivity()
						.getApplicationContext());
				tekst.setText(beverage.getName());
				tekst.setLayoutParams(param);
				tekst.setId(Integer.parseInt(sid + id));
				row.addView(tekst);

				Button plus = new Button(getActivity().getApplicationContext());
				plus.setText("plus");
				plus.setLayoutParams(param);
				plus.setId(id);
				plus.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						int id = v.getId();
						TextView name = (TextView) getView().findViewById(
								Integer.parseInt(sid + id));
						String naam = name.getText().toString();
						String currentsaldo = activity.plus(naam);
						TextView total = (TextView) getView().findViewById(
								Integer.parseInt(sid + id + id));
						total.setText("€ " + currentsaldo);
						activity.setTotal();
						total.invalidate();
					}
				});
				row.addView(plus);

				Button min = new Button(getActivity().getApplicationContext());
				min.setText("min");
				min.setLayoutParams(param);
				min.setId(id);
				min.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int id = v.getId();
						TextView name = (TextView) getView().findViewById(
								Integer.parseInt(sid + id));
						String naam = name.getText().toString();
						String currentsaldo = activity.min(naam);
						TextView total = (TextView) getView().findViewById(
								Integer.parseInt(sid + id + id));
						total.setText("€ " + currentsaldo);
						activity.setTotal();
						total.invalidate();
					}
				});
				row.addView(min);

				TextView amount = new TextView(getActivity()
						.getApplicationContext());
				amount.setText("€" + beverage.getPrice());
				amount.setLayoutParams(param);
				row.addView(amount);

				TextView total = new TextView(getActivity()
						.getApplicationContext());
				total.setLayoutParams(param);
				total.setText("€ 0.00");
				total.setId(Integer.parseInt(sid + id + id));
				row.addView(total);

				menulist.addView(row);
			}

		}
		scroll.setPadding(20, 5, 0, 5);
		scroll.addView(menulist);
		return scroll;
	}
}
