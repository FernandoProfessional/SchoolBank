

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Extraer de la cuenta <%=(String)session.getAttribute("numeroCuenta") %></h1>

<form action="Gestor" method="post">
<label>Introduce Cantidad</label><input type="number" name="cantidad">
<br>
<label>Introduce destino</label><input type="number" name="destino">
<br>
   <button type="submit" name="opcion" value="transferencia">Entrar</button>
</form>


</body>
</html>