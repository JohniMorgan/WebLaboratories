import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

public class Provider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SongList() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("utf-8");
    	
    	String user = request.getParameter("user");
    	String red = request.getParameter("red");
    	String green = request.getParameter("green");
    	String blue = request.getParameter("blue");
    	
    	RequestDispatcher dispatcher;
    	if (user == '' || red == '' || green == '' || blue = '') {
    		request.setParameter("error", "emptyField");
    		dispatcher = getServletContext().getRequestDispatcher("/Errors.jsp");
			dispatcher.forward(request, response);
    	}
    	
    	if (Integer.parseInt(red) > 255 || Integer.parseInt(red) < 0 || 
    		Integer.parseInt(green) > 255 || Integer.parseInt(green) < 0
    		Integer.parseInt(blue) > 255 || Integer.parseInt(blue) < 0) {
    		request.setParameter("error", "wrongColorNumber");
    		dispatcher = getServletContext().getRequestDispatcher("/Errors.jsp");
			dispatcher.forward(request, response);
    	}
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("user", user);
    	Integer cnt = session.getAttribute("count");
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
    	
    	response.sendRedirect(response.encodeRedirectURL(response.getContextPath() + "/Result"));
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}