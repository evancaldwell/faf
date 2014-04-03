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
	
//	Facebook fb = new FacebookFactory().getInstance(); //TODO: need to pass the fb from session instead
	Map <String, Friend> friends = new HashMap <String, Friend>();
	
	public FriendListBuilder() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Friend> getData(Facebook fb) throws FacebookException, JSONException {
		// Multiple FQL
		Map<String, String> queries = new HashMap<String, String>();
		
		queries.put("user_counts", "SELECT uid, name, friend_count, likes_count, wall_count, notes_count, games, pic_square FROM user WHERE uid IN(SELECT uid2 FROM friend WHERE uid1 = me())");
		queries.put("photo_info", "SELECT aid, owner, name, photo_count FROM album WHERE aid IN(SELECT aid FROM album WHERE owner IN(SELECT uid2 FROM friend WHERE uid1 = me())) AND photo_count > 0");
		queries.put("checkin_info", "SELECT author_uid,  checkin_id FROM checkin WHERE author_uid IN(SELECT uid2 FROM friend WHERE uid1 = me()) OR tagged_uids IN(SELECT uid2 FROM friend WHERE uid1 = me())");
		
		Map<String, JSONArray> result = fb.executeMultiFQL(queries);
		JSONArray userCountsJSONArray = result.get("user_counts");
		JSONArray photoInfoJSONArray = result.get("photo_info");
		JSONArray checkinInfoJSONArray = result.get("checkin_info");
		
		// loop through the serCounts results and create a Friend object with the data, initialize photo and checkin counts to 0
		for (int i=0; i<userCountsJSONArray.length(); i++) {
			JSONObject obj = userCountsJSONArray.getJSONObject(i);
			
			if (!obj.get("games").toString().isEmpty()) {  //TODO: ask why this has to have the .toString on it when it was already showing as a string type
				System.out.println("obj games: " + obj.get("games").getClass().getName());
				String gameOut = obj.get("games").toString(); 
				int gameCount = 1;
				String games = obj.get("games").toString();
				for(int j=0; j<games.length(); j++) {
				    if(games.charAt(j) == ',') gameCount++;
				}
			} else {
				int gameCount = 0;
			}
			
			Friend f = new Friend(obj.get("uid").toString(), 
								  obj.get("name").toString(), 
								  obj.get("pic_square").toString(), 
								  obj.get("friend_count").toString(), 
								  obj.get("wall_count").toString(),
								  obj.get("likes_count").toString(),
								  obj.get("notes_count").toString(),
								  obj.get("games").toString(),
								  0,
//								  gameCount, //TODO: figure out why this won't pull the variable from my if statement above
								  "0",
								  "0",
								  0);
			friends.put(f.getUid(), f);
		}
		System.out.println("friends: " + friends.get("123999").getFriendCount());
		// loop through the photo info, pull the friend from friends using the "owner" property and tally the number of photos
		for (int i=0; i<photoInfoJSONArray.length(); i++) {
			JSONObject obj = photoInfoJSONArray.getJSONObject(i);
			Friend f = friends.get(obj.get("owner"));
			f.setPhotoCount(Integer.toString(Integer.parseInt(f.getPhotoCount()) + Integer.parseInt(obj.get("photo_count").toString())));  //TODO: there is probably a cleaner way to do this without all the casting but it was saying it was an object if I didn't use .toString()
		}
		for (int i=0; i<checkinInfoJSONArray.length(); i++) {
			JSONObject obj = checkinInfoJSONArray.getJSONObject(i);
			Friend f = friends.get(obj.get("author_uid"));
			f.setCheckinCount(Integer.toString(Integer.parseInt(f.getCheckinCount()) + 1));
			System.out.println("id: " + f.getName() + " | checkin count: " + f.getCheckinCount());
		}
		
		calcScore(friends);
		
		return friends;
	}
	
	public void calcScore(Map<String, Friend> friends) {
		//TODO: loop through friends and total scores
		for (Friend friend : friends.values()) {
			int fc = (friend.getFriendCount() != "null") ? Integer.parseInt(friend.getFriendCount()) : 0;
			int wc = (friend.getWallCount() != "null") ? Integer.parseInt(friend.getWallCount()) : 0;
			int lc = (friend.getLikesCount() != "null") ? Integer.parseInt(friend.getLikesCount()) : 0;
			int nc = (friend.getNotesCount() != "null") ? Integer.parseInt(friend.getNotesCount()) : 0;
			int gc = friend.getGameCount();
//			int gc = 0;
			int pc = (friend.getPhotoCount() != "null") ? Integer.parseInt(friend.getPhotoCount()) : 0; // I think this is capped at 20
			int cc = (friend.getCheckinCount() != "null") ? Integer.parseInt(friend.getCheckinCount()) : 0;
			
			friend.setScore(fc+wc+lc+nc+gc+pc+cc);
			System.out.println("name: " + friend.getName() + " | score: " + friend.getScore() + " | games: " + friend.getGames());
			int some = 0;
		}
	}

}
