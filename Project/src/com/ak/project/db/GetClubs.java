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

import com.ak.project.model.Club;

import android.os.AsyncTask;
import android.util.Log;

public class GetClubs extends AsyncTask<String, String, String> implements
Serializable {

private String url_get_clubs = "/get_clubs.php";
private JSONParser jParser = new JSONParser();
private List<NameValuePair> param = new ArrayList<NameValuePair>();
private JSONObject json;
private ArrayList<Club> clubs = new ArrayList<Club>();

public GetClubs() {

}

public ArrayList<Club> getclubs() {
try {
	this.execute().get();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ExecutionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return clubs;
}

@Override
protected String doInBackground(String... params) {
json = jParser.makeHttpRequest(url_get_clubs, "GET", param);
try {
	if (json.getInt("success") == 1) {
		JSONArray clublist = json.getJSONArray("clubs");
		Log.i("ronde", clublist.length()+"");
		for(int i = 0; i < clublist.length() ; i++){
			JSONObject jsonclub = clublist.getJSONObject(i);
			
			String id = jsonclub.getString("id");
			String name = jsonclub.getString("name");
			String address = jsonclub.getString("address");
			
			Club club = new Club(name, address);
			club.setId(id);
			clubs.add(club);
			
		}
	}

} catch (JSONException e) {
	e.printStackTrace();
}
return null;
}

}
