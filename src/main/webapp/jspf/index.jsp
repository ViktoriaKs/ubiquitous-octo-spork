<%@page language="java" contentType="text/html"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Главная страница</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="main">
<h2>Функции системы</h2>
<nav>
<ul>
<li><a href="/person/personsme">Клиенты</a>
<li><a href="/person/citizens">Физические лица</a>
<li><a href="/person/documents">Документы</a>
</ul>
</nav>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
