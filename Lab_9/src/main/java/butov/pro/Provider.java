package butov.pro;

import java.io.IOException;
import java.net.URLEncoder;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

public class Provider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Provider() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
    	
    	String user = request.getParameter("user");
    	String red = request.getParameter("red");
    	String green = request.getParameter("green");
    	String blue = request.getParameter("blue");
    	
    	RequestDispatcher dispatcher;
    	if (user.equals("") || red.equals("") || green.equals("")|| blue.equals("")) {
    		session.setAttribute("error", "missedField");
    		RequestDispatcher d = request.getRequestDispatcher("Error.jsp");
    		d.forward(request, response);
    	}
    	else
    	if (Integer.parseInt(red) > 255 || Integer.parseInt(red) < 0 || 
    		Integer.parseInt(green) > 255 || Integer.parseInt(green) < 0 ||
    		Integer.parseInt(blue) > 255 || Integer.parseInt(blue) < 0) {
    		session.setAttribute("error", "wrongColorNumber");
    		RequestDispatcher d = request.getRequestDispatcher("Error.jsp");
    		d.forward(request, response);	
    	} else {
    	
    	session.setAttribute("user", user);
    	Integer cnt = (Integer)session.getAttribute("count");
    	if (cnt != null) {
    		session.setAttribute("count", ++cnt);
    	}
    	else {
    		session.setAttribute("count", 1);
    	}
    	
    	Cookie r = new Cookie("cookie.red", URLEncoder.encode(red, "UTF-8"));
    	Cookie g = new Cookie("cookie.green", URLEncoder.encode(green, "UTF-8"));
    	Cookie b = new Cookie("cookie.blue", URLEncoder.encode(blue, "UTF-8"));
    	r.setMaxAge(50);
    	g.setMaxAge(50);
    	b.setMaxAge(50);
    	response.addCookie(r);
    	response.addCookie(g);
    	response.addCookie(b);
    	
    	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Result.jsp"));
    	}
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}