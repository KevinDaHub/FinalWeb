package com.ak.project.activities;

import java.util.ArrayList;

import com.ak.project.R;
import com.ak.project.model.Beverage;
import com.ak.project.model.Quickorder;
import com.ak.project.view.OfflineTabPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class OfflineTabActivity extends FragmentActivity implements TabListener {

	private ViewPager viewpager;
	private ActionBar actionbar;
	private OfflineTabPagerAdapter adapter;
	private ArrayList<String> tabs = new ArrayList<String>();
	private Quickorder quickorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlinetabs);
		Intent i = getIntent();
		Bundle data = i.getExtras();
		quickorder = (Quickorder) data.getSerializable("quickorder");
		tabs.add("Beer");
		tabs.add("Wine");
		tabs.add("Strong");
		tabs.add("Soft");

		viewpager = (ViewPager) findViewById(R.id.pager);
		actionbar = getActionBar();
		adapter = new OfflineTabPagerAdapter(getSupportFragmentManager());
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

}
