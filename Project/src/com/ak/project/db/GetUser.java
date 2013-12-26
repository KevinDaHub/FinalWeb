package com.ak.project.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ak.project.model.User;

import android.os.AsyncTask;
import android.util.Log;

public class GetUser extends AsyncTask<String, String, String> implements
		Serializable {

	private String url_get_user = "/get_user.php";
	private JSONParser jParser = new JSONParser();
	private List<NameValuePair> param = new ArrayList<NameValuePair>();
	private String name;
	private String password;
	private User user;
	private JSONObject json;

	public GetUser() {

	}

	public User getuser(String name, String password) {
		this.name = name;
		this.password = password;
		try {
			this.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	@Override
	protected String doInBackground(String... params) {
		param.add(new BasicNameValuePair("name", name));
		param.add(new BasicNameValuePair("password", password));
		json = jParser.makeHttpRequest(url_get_user, "POST", param);
		try {
			if (json.getInt("succes") == 1) {
				JSONObject userobject = json.getJSONObject("user");
				String naam = userobject.getString("name");
				String surname = userobject.getString("surname");
				String email = userobject.getString("email");
				double saldo = userobject.getDouble("saldo");
				String pass = userobject.getString("password");
				this.user = new User(naam, surname, pass, email, saldo);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
