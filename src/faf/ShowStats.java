package faf;

import java.io.IOException;
import java.util.Map;

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
		Map<String, faf.Friend> friendList;
		
		try {
			friendList = flb.getData(fb);
			request.setAttribute("fl", friendList);
		} catch (FacebookException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/displayFriends.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
