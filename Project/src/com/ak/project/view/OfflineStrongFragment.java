package com.ak.project.view;

import java.util.ArrayList;

import com.ak.project.R;
import com.ak.project.R.layout;
import com.ak.project.activities.OfflineTabActivity;
import com.ak.project.activities.TabActivity;
import com.ak.project.model.Beverage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OfflineStrongFragment extends Fragment {

	private ArrayList<Beverage> menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final OfflineTabActivity activity = (OfflineTabActivity) getActivity();
		menu = activity.getMenu();
		View rootView = inflater
				.inflate(R.layout.menu_layout, container, false);

		ScrollView scroll = new ScrollView(getActivity()
				.getApplicationContext());
		TableLayout menulist = new TableLayout(getActivity()
				.getApplicationContext());
		TableRow.LayoutParams param = new TableRow.LayoutParams();
		param.setMargins(0, 0, 15, 0);
		final String sid = "3";
		int id = 0;
		for (Beverage beverage : menu) {
			if (beverage.getType().equals("strong")) {
				id++;
				TableRow row = new TableRow(getActivity()
						.getApplicationContext());

				TextView tekst = new TextView(getActivity()
						.getApplicationContext());
				tekst.setText(beverage.getName());
				tekst.setLayoutParams(param);
				tekst.setId(Integer.parseInt(sid + id));
				row.addView(tekst);

				TextView amount = new TextView(getActivity()
						.getApplicationContext());
				amount.setText("€" + beverage.getPrice());
				amount.setLayoutParams(param);
				row.addView(amount);
				
				menulist.addView(row);
			}

		}
		scroll.setPadding(20, 5, 0, 5);
		scroll.addView(menulist);
		return scroll;
	}
}
