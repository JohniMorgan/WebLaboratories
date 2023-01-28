<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		RequestDispatcher dispatcher;
		if (name == "" || phone == "" || email == "") {
			dispatcher = getServletContext().getRequestDispatcher("/Errors.jsp");
			dispatcher.forward(request, response);
		}
	%>
	<h1>Результат отправки формы</h1>
	<ul>
		<li>Имя: <%=name%></li>
		<li>Номер телефона: <%=phone%></li>
		<li>Почтовый адрес: <%=email%></li>
	</ul>
</body>
</html>