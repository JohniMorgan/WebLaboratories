package lab.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.objects.Song;

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
    	request.setCharacterEncoding("UTF-8");
    	String artist = request.getParameter("artist");
    	String lang = request.getParameter("lang");
    	if(lang == null) {
    		 response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
	    		"Ожидался параметр lang");
	    		 return;
    		 }
    	if(!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
    		 response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
    		"Параметр lang может принимать значения en или ru");
    		 return;
    	}
    	
    	response.setContentType("text/html;charset=UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	ResourceBundle res = ResourceBundle.getBundle(
    			"/Song", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());
    	
    	ArrayList<Song> songs = new ArrayList<Song>();
    	songs.add(new Song("Би-2", "Варвара", 301));
    	songs.add(new Song("Би-2", "Молитва", 337));
    	songs.add(new Song("Би-2", "Серебро", 280));
    	songs.add(new Song("Ария", "Штиль", 335));
    	songs.add(new Song("Ария", "Беспечный ангел", 238));
    	String src1;
    	PrintWriter out = response.getWriter();
    	try {
    		out.println("<html>");
    		src1 = res.getString("title");
    		//System.out.println(src1.getBytes("ISO-8859-1"));
    		//System.out.println(new String(src1.getBytes("ISO-8859-1"), "UTF-8"));
    		out.println("<head><title>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8") + "</title></head>");
    		out.println("<body>");
    		src1 = res.getString("header");
    		out.print("<h1>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8"));
    		if (artist != null) out.print(" группы " + artist);
    		out.println("</h1>");
    		out.println("<table border='1'>");
    		out.print("<tr>");
    		if (artist == null) {
    			src1 = res.getString("songer");
    			out.print("<td><b>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8") + "</b></td>");
    		}
    		src1 = res.getString("song");
    		//System.out.println(src1);
    		//System.out.println(new String(src1.getBytes("ISO-8859-1"), "UTF-8"));
    		out.println("<td><b>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8") + "</b></td>");
    		src1 = res.getString("time");
    		out.println("<td><b>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8") + "</b></td>");
    		src1 = res.getString("genre");
    		out.println("<td><b>" + new String(src1.getBytes("ISO-8859-1"), "UTF-8") + "</b></td></tr>");
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
