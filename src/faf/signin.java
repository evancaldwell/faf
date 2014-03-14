package faf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create a new Facebook instance based on facebook4j
		Facebook fb = new FacebookFactory().getInstance();
		// set the new Facebook instance on the session
		request.getSession().setAttribute("fb", fb);
		
		// get the URL of the request
		StringBuffer requestUrl = request.getRequestURL();
		// get the index of the last slash from the request URL
		int lastSlashIndex = requestUrl.lastIndexOf("/");
		// use the last slash index to rebuild a callback URL
		String callBackUrl = requestUrl.substring(0, lastSlashIndex) + "/callback";
		// build the facebook URL including the callback URL
		String facebookUrl = fb.getOAuthAuthorizationURL(callBackUrl);
		
		response.sendRedirect(facebookUrl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
