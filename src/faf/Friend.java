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
	
	private String uid;
	private String name;
	private String picSquare;
	private String friendCount;
	private String wallCount;
	private String likesCount;
	private String notesCount;
	private String games;
	
	public Friend() {
		setUid("");
		setName("");
		setPicSquare("");
		setFriendCount("");
		setWallCount("");
		setLikesCount("");
		setNotesCount("");
		setGames("");
	}
	
	public Friend(String uid, String name, String picSquare, String freindCount, String wallCount, String likesCount, String notesCount, String games) {
		this.setUid(uid);
		this.setName(name);
		this.setPicSquare(picSquare);
		this.setFriendCount(freindCount);
		this.setWallCount(wallCount);
		this.setLikesCount(likesCount);
		this.setNotesCount(notesCount);
		this.setGames(games);
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicSquare() {
		return picSquare;
	}

	public void setPicSquare(String picSquare) {
		this.picSquare = picSquare;
	}

	public String getFriendCount() {
		return friendCount;
	}

	public void setFriendCount(String friendCount) {
		this.friendCount = friendCount;
	}

	public String getWallCount() {
		return wallCount;
	}

	public void setWallCount(String wallCount) {
		this.wallCount = wallCount;
	}

	public String getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(String likesCount) {
		this.likesCount = likesCount;
	}

	public String getNotesCount() {
		return notesCount;
	}

	public void setNotesCount(String notesCount) {
		this.notesCount = notesCount;
	}

	public String getGames() {
		return games;
	}

	public void setGames(String games) {
		this.games = games;
	}
	
	

}
