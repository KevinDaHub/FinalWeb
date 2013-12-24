package com.ak.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Quickorder quickorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		quickorder = new Quickorder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void logIn(View view) {

		EditText namefield = (EditText) findViewById(R.id.editText1);
		String name = namefield.getText().toString();
		EditText passwordfield = (EditText) findViewById(R.id.editText2);
		String password = passwordfield.getText().toString();
		String login = quickorder.login(name, password);
		if (login.equals("ok")) {
			quickorder.importClubs();
			Intent myIntent = new Intent(this, PlacesActivity.class);
			Bundle data = new Bundle();
			data.putSerializable("quickorder", quickorder);
			myIntent.putExtras(data);
			startActivity(myIntent);
		} else {
			Toast.makeText(getApplicationContext(), login, Toast.LENGTH_LONG)
					.show();
		}

	}
}
