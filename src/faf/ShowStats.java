package faf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.ResponseList;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;

/**
 * Servlet implementation class ShowFriends
 */
@WebServlet("/ShowStats")
public class ShowStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get a new Facebook instance from the session
		Facebook fb = (Facebook) request.getSession().getAttribute("fb");
		FriendListBuilder flb = new FriendListBuilder();
		
		try {
			JSONArray friendList = flb.getData(fb);
			System.out.println("ShowStats friend list: " + friendList);
		} catch (FacebookException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Friend friend = new Friend();		
//		JSONArray userCounts;
//		try {
//			userCounts = Friend.;
//			for (Friend friend : friendList) {
//				// process friend stuff
//			}
//		} catch (FacebookException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
