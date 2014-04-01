package faf;

import java.util.HashMap;
import java.util.Map;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class FriendListBuilder {
	
	Facebook fb = new FacebookFactory().getInstance(); //TODO: need to pass the fb from session instead
	Map <String, Friend> friends = new HashMap <String, Friend>;
	
	public FriendListBuilder() {
		// TODO Auto-generated constructor stub
	}

	public JSONArray getData() throws FacebookException, JSONException {
		// Multiple FQL
		Map<String, String> queries = new HashMap<String, String>();
		
		queries.put("user_counts", "SELECT uid, name, friend_count, likes_count, wall_count, notes_count, games, pic_square FROM user WHERE uid IN(SELECT uid2 FROM friend WHERE uid1 = me()");
		queries.put("photo_info", "SELECT aid, owner, name, photo_count FROM album WHERE aid IN(SELECT aid FROM album WHERE owner IN(SELECT uid2 FROM friend WHERE uid1 = me())) AND photo_count > 0");
		queries.put("checkin_info", "SELECT author_uid,  checkin_id FROM checkin WHERE author_uid IN(SELECT uid2 FROM friend WHERE uid1 = me()) OR tagged_uids IN(SELECT uid2 FROM friend WHERE uid1 = me())");
		
		Map<String, JSONArray> result = fb.executeMultiFQL(queries);
		JSONArray userCountsJSONArray = result.get("user_counts");
		JSONArray photoInfoJSONArray = result.get("photo_info");
		JSONArray checkinInfoJSONArray = result.get("checkin_info");
		
		for (int i=0; i<userCountsJSONArray.length(); i++) {
			JSONObject obj = userCountsJSONArray.getJSONObject(i);
			System.out.println("userconts obj: " + obj);
			System.out.println("obj uid: " + obj.get("uid"));
			Friend f = new Friend(obj.get("uid"), 
								  obj.get("name"), 
								  obj.get("pic_square"), 
								  obj.get("friend_count"), 
								  obj.get("wall_count"),
								  obj.get("likes_count"),
								  obj.get("notes_count"),
								  obj.get("games"));
			friends.put(f.getUid(), f);
		}
		for (int i=0; i<photoInfoJSONArray.length(); i++) {
			//TODO: loop through photos and count them up
		}
		for (int i=0; i<checkinInfoJSONArray.length(); i++) {
			//TODO: loop through checkins and add them up
		}
		
//		for (int i = 0; i < userCountsJSONArray.length(); i++) {
//		    JSONObject jsonObject = userCountsJSONArray.getJSONObject(i);
//		    System.out.println(jsonObject.get("uid2"));
//		}
		
		return userCountsJSONArray;
	}

}
