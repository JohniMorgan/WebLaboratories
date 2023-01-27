<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<style>
	.non-valid {
		color: red;
	}
	.valid {
		font-size: 0px;
	}
	.field {
		color: blue;
	}
</style>

<body>
	<h1 style="color: red">Внимание! Возникла ошибка!</h1>
	<% 
		String validName = request.getParameter("name") != "" ? "valid" : "non-valid";
		String validPhone = request.getParameter("phone") != "" ? "valid" : "non-valid";
		String validEmail = request.getParameter("email") != "" ? "valid" : "non-valid";
	%>
	<label class="<%=validName%>">Вы не заполнили поле <span class="field">Имя</span></label>
	<br>
	<label class="<%=validPhone%>">Вы не заполнили поле <span class="field">Телефон</span></label>
	<br>
	<label class="<%=validEmail%>">Вы не заполнили поле <span class="field">Email</span></label>
	<br>
</body>
</html>