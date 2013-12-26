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

public class UpdateSaldo extends AsyncTask<String, String, String> implements
		Serializable {

	private String update_saldo = "/change_saldo.php";
	private JSONParser jParser = new JSONParser();
	private List<NameValuePair> param = new ArrayList<NameValuePair>();
	private String name;
	private double saldo;
	private JSONObject json;

	public UpdateSaldo(String name,double saldo) {
		this.name = name;
		this.saldo = saldo;
	}
	@Override
	protected String doInBackground(String... params) {
		param.add(new BasicNameValuePair("name", name));
		Log.i("naam",name);
		param.add(new BasicNameValuePair("saldo", saldo+""));
		json = jParser.makeHttpRequest(update_saldo, "POST", param);
		try {
			if (json.getInt("success") == 1) {
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
