package faf;

import java.util.HashMap;
import java.util.Map;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class Friend {
	
	Facebook fb = new FacebookFactory().getInstance();

	public Friend() {
		// TODO Auto-generated constructor stub
	}
	
	public JSONArray getData() throws FacebookException, JSONException {
		// Multiple FQL
		Map<String, String> queries = new HashMap<String, String>();
		
		queries.put("User Counts", "SELECT uid, name, friend_count, likes_count, wall_count, notes_count, games, pic_square FROM user WHERE uid IN(SELECT uid2 FROM friend WHERE uid1 = me()");
		queries.put("Photo Info", "SELECT aid, owner, name, photo_count FROM album WHERE aid IN(SELECT aid FROM album WHERE owner IN(SELECT uid2 FROM friend WHERE uid1 = me())) AND photo_count > 0");
		queries.put("Checkin Info", "SELECT author_uid,  checkin_id FROM checkin WHERE author_uid IN(SELECT uid2 FROM friend WHERE uid1 = me()) OR tagged_uids IN(SELECT uid2 FROM friend WHERE uid1 = me())");
		
		Map<String, JSONArray> result = fb.executeMultiFQL(queries);
		JSONArray userCountsJSONArray = result.get("User Counts");
		for (int i = 0; i < userCountsJSONArray.length(); i++) {
		    JSONObject jsonObject = userCountsJSONArray.getJSONObject(i);
		    System.out.println(jsonObject.get("uid2"));
		}
		JSONArray myNameJSONArray = result.get("my name");
		System.out.println(myNameJSONArray.getJSONObject(0).get("name"));
		
		return userCountsJSONArray;
	}

}
