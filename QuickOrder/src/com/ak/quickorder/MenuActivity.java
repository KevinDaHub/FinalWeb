package com.ak.quickorder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MenuActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

	}

	public void onResume() {
		TabHost host = (TabHost) findViewById(R.id.tab_host);
		host.setup();

		TabSpec spec = host.newTabSpec("Tab One");
		spec.setContent(R.id.tab_one_container);
		spec.setIndicator("Tab One");
		host.addTab(spec);

		spec = host.newTabSpec("Tab Two");
		spec.setContent(R.id.tab_two_container);
		spec.setIndicator("Tab Two");
		host.addTab(spec);

		spec = host.newTabSpec("Tab Two");
		spec.setContent(R.id.tab_two_container);
		spec.setIndicator("Tab Two");
		host.addTab(spec);
		spec = host.newTabSpec("Tab Two");
		spec.setContent(R.id.tab_two_container);
		spec.setIndicator("Tab Two");
		host.addTab(spec);
		super.onResume();
	}

}
