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

import com.ak.project.model.Beverage;

import android.os.AsyncTask;
import android.util.Log;

public class GetMenu extends AsyncTask<String, String, String> implements
		Serializable {

	private String url_get_menu = "/get_menus.php";
	private JSONParser jParser = new JSONParser();
	private List<NameValuePair> param = new ArrayList<NameValuePair>();
	private JSONObject json;
	private String id;
	private ArrayList<Beverage> menu;
	public GetMenu() {

	}

	public ArrayList<Beverage> getMenu(String id) {
		this.id=id;
		menu = new ArrayList<Beverage>();
		try {
			this.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menu;
	}

	@Override
	protected String doInBackground(String... params) {
		param.add(new BasicNameValuePair("id", id));
		json = jParser.makeHttpRequest(url_get_menu, "POST", param);
		try {
			if (json.getInt("success") == 1) {
				JSONArray beveragelist = json.getJSONArray("beverages");
				Log.i("ronde", beveragelist.length()+"");
				for(int i = 0; i < beveragelist.length() ; i++){
					JSONObject jsonbeverage = beveragelist.getJSONObject(i);
					
					String id = jsonbeverage.getString("id");
					String name = jsonbeverage.getString("name");
					String type = jsonbeverage.getString("type");
					double price = jsonbeverage.getDouble("price");
					
					Beverage beverage = new Beverage(name,type,price);
					beverage .setId(id);
					menu.add(beverage);

					
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
