<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Form</title>
</head>
<body>
	<form action="ResultForm.jsp">
		<div>
			<label>ФИО<br></label>
			<input type="text" name="name">
		</div>
		<div>
			<label>Телефон<br></label>
			<input type="text" name="phone">
		</div>
		<div>
			<label>Email<br></label>
			<input type="text" name="email">
		</div>
		<input type="submit" value="Отправить форму">
	</form>
</body>
</html>