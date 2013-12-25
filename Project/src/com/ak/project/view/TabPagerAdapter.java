package com.ak.project.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	String id = "1";
	String type = "";

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new BeerFragment();
		case 1:
			return new WineFragment();
		case 2:
			return new StrongFragment();
		case 3:
			return new SoftFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

}
