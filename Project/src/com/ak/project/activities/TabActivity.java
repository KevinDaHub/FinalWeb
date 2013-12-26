package com.ak.project.activities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.ak.project.R;
import com.ak.project.R.id;
import com.ak.project.R.layout;
import com.ak.project.db.SendMail;
import com.ak.project.model.Beverage;
import com.ak.project.model.Quickorder;
import com.ak.project.view.TabPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity extends FragmentActivity implements TabListener {

	private ViewPager viewpager;
	private ActionBar actionbar;
	private TabPagerAdapter adapter;
	private ArrayList<String> tabs = new ArrayList<String>();
	private Quickorder quickorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		Intent i = getIntent();
		Bundle data = i.getExtras();
		quickorder = (Quickorder) data.getSerializable("quickorder");
		quickorder.importClubs();
		tabs.add("Beer");
		tabs.add("Wine");
		tabs.add("Strong");
		tabs.add("Soft");

		viewpager = (ViewPager) findViewById(R.id.pager);
		actionbar = getActionBar();
		adapter = new TabPagerAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);
		actionbar.setHomeButtonEnabled(false);
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab : tabs) {
			actionbar.addTab(actionbar.newTab().setText(tab)
					.setTabListener(this));
		}
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				actionbar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		setSaldo();

	}

	public void sendOrder(View v) {

		TextView total = (TextView) findViewById(R.id.vtotal);
		String[] split = total.getText().toString().split(" ");
		double totalprice = Double.parseDouble(split[1]);
		if (quickorder.updateSaldo(totalprice)) {
			ArrayList<Beverage> bestellingen = quickorder.getSelectedClub()
					.getBestellingen();
			String message = "";
			for (Beverage beverage : bestellingen) {
				message += "drank: " + beverage.getName() + ", aantal: "
						+ beverage.getAmount() + "\n";
			}

			SendMail sm = new SendMail(message);
			try {
				Toast.makeText(TabActivity.this, sm.execute().get(),
						Toast.LENGTH_LONG).show();
					setSaldo();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(TabActivity.this, "Niet genoeg geld",
					Toast.LENGTH_LONG).show();
		}
	}

	public ArrayList<Beverage> getMenu() {
		return quickorder.getSelectedClub().getMenu();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	public String min(String naam) {
		return quickorder.min(naam);
	}

	public String plus(String naam) {
		return quickorder.plus(naam);
	}

	public void setSaldo() {
		TextView saldo = (TextView) findViewById(R.id.vsaldo);

		saldo.setText("€ " + quickorder.getCurrentSaldo());
		saldo.invalidate();
	}

	public void setTotal() {
		TextView total = (TextView) findViewById(R.id.vtotal);
		total.setText("€ " + quickorder.getTotal());
		total.invalidate();
	}

}
