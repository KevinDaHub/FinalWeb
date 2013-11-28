package com.ak.quickorder;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLayoutActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

	}

	public void onResume() {
		TabHost host = (TabHost) findViewById(R.id.tab_host);
		host.setup();
		Intent menu = new Intent(this, MenuActivity.class);

		TabSpec spec = host.newTabSpec("Beer");
		spec.setContent(menu);
		spec.setIndicator("Tab One");
		host.addTab(spec);

		spec = host.newTabSpec("Wine");
		spec.setContent(menu);
		spec.setIndicator("Tab Two");
		host.addTab(spec);

		spec = host.newTabSpec("Strong");
		spec.setContent(menu);
		spec.setIndicator("Tab Two");
		host.addTab(spec);
		
		spec = host.newTabSpec("Soft");
		spec.setContent(menu);
		spec.setIndicator("Tab Two");
		host.addTab(spec);
		super.onResume();
	}

}
