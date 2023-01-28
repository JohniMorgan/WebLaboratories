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
	HttpSession s = request.getSession();
	if ((s.getAttribute("error")).equals("missedField")) {%>
	<h1>Выглядит так, как будто вы забыли ввести какое-то из полей. Пожалуйста, будьте внимательнее</h1>
	<%} 
	if ((s.getAttribute("error")).equals("wrongColorNumber")) {%>
	<h1>Вы ввели некорректные параметры цвета. Каждое значение должно быть в диапазоне [0; 255]</h1>
	<%}%>
</body>
</html>