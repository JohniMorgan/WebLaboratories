<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="javax.servlet.ServletException" %>;
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
		String[] color = {"red", "green", "blue"};
		Cookie[] c = request.getCookies();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < c.length; j++) {
				if (("cookie."+color[i]).equals(c[j].getName())) color[i] = c[j].getValue();
			}
		}
		%>
	<div class="wrapper" style="color: rgb(<%=color[0] + ", " + color[1] + ", " + color[2]%>)">
		<h3>Данные в объекте сессии</h3>
		<ul>
			<li>Пользователь: <%=(String)session.getAttribute("user")%></li>
			<li>Дата последнего обращения: <%=(String)session.getAttribute("last_date_call")%></li>
			<li>Количество обращений: <%=session.getAttribute("count")%></li>
		</ul>
		<h3>Данные из файлов Cookie</h3>
		<ul>
			<%
				for (int i = 0; i < c.length; i++)
					out.print("<li>" + c[i].getName() + ": " + java.net.URLDecoder.decode(c[i].getValue(), "UTF-8") + "</li>"); %>
		</ul>
	</div>
</body>
</html>