<%@ page language="java" contentType="text/html"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Person"%>
<%@ page import="domain.Role"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Сотрудники</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Persons</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<div id="main">
<aside class="leftAside">
<h3>Список сотрудников</h3>
<table>
<thead>
<tr>
<th scope="col">Код</th>
<th scope="col">Фамилия</th>
<th scope="col">Имя</th>
<th scope="col">Должность</th>
<th scope="col">Телефон</th>
<th scope="col">Эл. почта</th>
</tr>
</thead>
<tbody>

<c:forEach var="person" items="${persons}">
<tr>
<td>${person.getId()}</td>
<td>${person.getLastName()}</td>
<td>${person.getFirstName()}</td>
<td>${person.getRole()}</td>
<td>${person.getPhone()}</td>
<td>${person.getEmail()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
<section>
<article>
<h3>Данные по сотруднику</h3>
<div class="text-article">
<form method="POST" action="">
<p>
<label for="lastname">Фамилия</label>
<input type="text" name="lastname" />
</p>
<p>
<label for="firstname">Имя</label>
<input type="text" name="firstname" />
</p>
<p>
<label for="rolename">Должность</label>
<select>
<option disabled>Выберите должность</option>
<c:forEach var="role" items="${roles}">
<option value="${role}">
<c:out value="${role.getNamerole()}"></c:out>
</option>
</c:forEach>
</select>
<p>
<label for="phone">Телефон</label>
<input type="text" name="phone" />
</p>
<p>
<label for="email">Эл. почта </label>
<input type="text" name="email" />
</p>
<p>
<button type="submit">Добавить</button>
</p>
</form>
</div>
</article>
</section>
</div>
<jsp:include page="/jspf/footer.jsp" />
</body>
</html>