<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Результаты формы</title>
</head>
<style>
	.wrapper {
		width: 50%;
		padding-left: auto;
		padding-right:auto;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-right: -50%;
		transform: translate(-50%, -50%);
		display: flex;
		flex-direction:column;
		align-items:center;
	}
</style>
<body>
	<%  request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("user");
		String red = request.getParameter("red");
		String blue = request.getParameter("blue");
		String green = request.getParameter("green");
		RequestDispatcher dispatcher;
		if (user == "" || red == "" || blue == "" || green == "") {
			dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		};
		if (Integer.parseInt(red) > 255 || Integer.parseInt(red) < 0 
				|| Integer.parseInt(blue) > 255 || Integer.parseInt(blue) < 0 
				|| Integer.parseInt(green) > 255 || Integer.parseInt(green) < 0) {
			dispatcher = getServletContext().getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		};
		session.setAttribute("user", user);
		Cookie r = new Cookie("cookie.red", URLEncoder.encode(red, "UTF-8"));
		r.setMaxAge(100);
		Cookie g = new Cookie("cookie.green", URLEncoder.encode(green, "UTF-8"));
		g.setMaxAge(100);
		Cookie b = new Cookie("cookie.blue", URLEncoder.encode(blue, "UTF-8"));
		b.setMaxAge(100);
		
		response.addCookie(r);
		response.addCookie(b);
		response.addCookie(g); %>
	<div class="wrapper" style="color: rgb(<%=red + ", " + green + ", " + blue%>)">
		<h3>Данные в объекте сессии</h3>
		<ul>
			<li>Пользователь: <%=(String)session.getAttribute("user")%></li>
			<li>Дата последнего обращения: <%=(String)session.getAttribute("last_date_call")%></li>
			<li>Количество обращений: <%=session.getAttribute("count")%></li>
		</ul>
		<h3>Данные из файлов Cookie</h3>
		<ul>
			<%  Cookie[] c = request.getCookies();
				for (int i = 0; i < c.length; i++)
					out.print("<li>" + c[i].getName() + ": " + java.net.URLDecoder.decode(c[i].getValue(), "UTF-8") + "</li>"); %>
		</ul>
	</div>
</body>
</html>