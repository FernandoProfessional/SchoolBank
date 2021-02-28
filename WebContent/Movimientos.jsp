<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.bins.Movimiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Cuenta: <%=(String)session.getAttribute("numeroCuenta") %></h1>
<h1>Saldo: <%=request.getAttribute("saldo") %></h1>



<% List<Movimiento> listatotal = (List<Movimiento>)request.getAttribute("lista"); %>

<table style="border:solid black 2px">
<tr>
<th style="border:solid black 2px">Cantidad</th>
<th style="border:solid black 2px">Fecha</th>
<th style="border:solid black 2px">Tipo</th>
</tr>


<% for(Movimiento transcaccion : listatotal){%>
<tr><td Movimiento="border:solid black 2px"><%=transcaccion.getCantidad()%></td> <td style="border:solid black 2px"> <%=transcaccion.getFecha()%></td> <td style="border:solid black 2px"> <%=transcaccion.getOperacion()%></td> </tr>

<%} %>

</table>
<br>
<a href="Opciones.jsp">Volver</a>


</body>
</html>