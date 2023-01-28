<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Выберите цвет</title>
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
	.color-form {
		display: flex;
		width: 100%;
		justify-content: space-between;
	}
	.color-field {
		max-width: 50px;
	}
</style>
<body>
	<%  response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Integer cnt = (Integer)session.getAttribute("count");
		if (cnt == null)
			session.setAttribute("count", 1);
		else
			session.setAttribute("count", ++cnt);
		String[] fields = {"user", "red", "green", "blue"};
		String[] RusFields = {"Пользователь", "Красный", "Зелёный", "Синий"};
		Cookie[] c = request.getCookies(); %>
	<div class="wrapper">
	<label>Введите данные в формате "Пользователь RGB"</label>
	<br>
	<form class="color-form" method="GET" action="Provider">
	<%for (int i = 0;i < 4;  i++) { %>
		<div class="color-field">
			<label><%=RusFields[i]%></label>
			<input type="text" name="<%=fields[i]%>" 
			<% 
				if (c != null)
					for (int j = 0; j < c.length; j++)
						if (("cookie." + fields[i]).equals(c[j].getName())) {
							out.print(
			" value='" + java.net.URLDecoder.decode(c[j].getValue(), "UTF-8") + "'"
									);
							break;
						}%> >
		</div>
	<% };
		session.setAttribute("last_date_call", new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new java.util.Date()));%>
	<input type="submit" value="Ввод">
	</form>
	</div>
</body>
</html>