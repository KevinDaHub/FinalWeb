package com.ak.quickorder;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLayoutActivity extends TabActivity {

	TabHost host;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		host = getTabHost();

	}

	public void onResume() {
		host.setup();
		Intent menu = new Intent(this, MenuActivity.class);

		TabSpec spec = host.newTabSpec("Beer");
		spec.setContent(menu);
		spec.setIndicator("Beer");
		host.addTab(spec);

		spec = host.newTabSpec("Wine");
		spec.setContent(menu);
		spec.setIndicator("Wine");
		host.addTab(spec);

		spec = host.newTabSpec("Strong");
		spec.setContent(menu);
		spec.setIndicator("Strong");
		host.addTab(spec);
		
		spec = host.newTabSpec("Soft");
		spec.setContent(menu);
		spec.setIndicator("Soft");
		host.addTab(spec);
		super.onResume();
	}

}
