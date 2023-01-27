package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab3.web.Song;

/**
 * Servlet implementation class SongList
 */

public class SongList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String artist = request.getParameter("artist");
    	response.setContentType("text/html;charset=UTF-8");
    	ArrayList<Song> songs = new ArrayList<Song>();
    	songs.add(new Song("Би-2", "Варвара", 301));
    	songs.add(new Song("Би-2", "Молитва", 337));
    	songs.add(new Song("Би-2", "Серебро", 280));
    	songs.add(new Song("Ария", "Штиль", 335));
    	songs.add(new Song("Ария", "Беспечный ангел", 238));
    	
    	PrintWriter out = response.getWriter();
    	try {
    		out.println("<html>");
    		out.println("<head><title>Список песен</title></head>");
    		out.println("<body>");
    		out.print("<h1>Список песен");
    		if (artist != null) out.print(" группы " + artist);
    		out.println("</h1>");
    		out.println("<table border='1'>");
    		out.print("<tr>");
    		if (artist == null) out.print("<td><b>Исполнитель</b></td>");
    		out.println("<td><b>Композиция</b></td><td><b>Время</b></td><td><b>Жанр</b></td></tr>");
    		for (int i = 0; i < songs.size(); i++) {
    			if (artist == null || (artist != null && artist.equals(songs.get(i).getSonger()))) {
    				out.print("<tr>");
    				if (artist == null) out.print("<td><b>" + songs.get(i).getSonger() + "</b></td>");
    				out.println("<td><b>" + songs.get(i).getTitle() + "</td></b><td><b>" +
    				Integer.toString(songs.get(i).getTime()/60)+":"+Integer.toString(songs.get(i).getTime()%60) +
    				"</td></b><td><b>Рок</b></td></tr>");
    			}
    		}
    		out.println("</table>");
    		out.println("</body>");
    		out.println("</html>");
    	} finally {
    		out.close();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
