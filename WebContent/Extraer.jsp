<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Extraer en la cuenta <%=(String)session.getAttribute("numeroCuenta") %></h1>

<form action="Gestor" method="post">
<label>Introduce Cantidad</label><input type="number" name="cantidad">
<br>
   <button type="submit" name="opcion" value="extraccion">Entrar</button>
</form>



</body>
</html>