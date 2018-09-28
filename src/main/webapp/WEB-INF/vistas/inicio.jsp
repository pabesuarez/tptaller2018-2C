<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input id="cadena" type="text" /><br/>
	<button onclick="window.location.href='${pageContext.request.contextPath}/pasar-a-mayuscula/'+document.getElementById('cadena').value">pasar a mayusculas</button><br/>
	<button onclick="window.location.href='${pageContext.request.contextPath}/pasar-a-minuscula/'+document.getElementById('cadena').value">pasar a minusculas</button><br/>
	<button onclick="window.location.href='${pageContext.request.contextPath}/invertir-orden/'+document.getElementById('cadena').value">invertir orden</button><br/>
	<button onclick="window.location.href='${pageContext.request.contextPath}/cantidad-caracteres/'+document.getElementById('cadena').value">cantidad de caracteres</button><br/>
</body>
</html>