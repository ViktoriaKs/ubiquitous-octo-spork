<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Role"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<link rel="stylesheet" href="/person/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Должности</title>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/person/css/bootstrap.min.css">
<title>Roles</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<div id="main">
	<section>
		<aside class="leftAside">
		<h3>Список должностей</h3>
		<table class="table table-sm" id="table-info">
		<thead>
		<tr>
			<th scope="col">Код</th>
			<th scope="col">Должность</th>
			<th scope="col">Редактировать</th>
			<th scope="col">Удалить</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="role" items="${roles}">
		<tr>
			<td>${role.getId()}</td>
			<td>${role.getNamerole()}</td>
			
		<td width="20" id="td-edit">
		<a href='<c:url value="/editrole?id=${role.getId()}" />'
		role="button" class="btn btn-outline-primary">
		<img alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20"><a
		href='<c:url value="/deleterole?id=${role.getId()}"/>'
		role="button" class="btn btn-outline-primary"> 
		<img alt="Удалить" src="images/icon-delete.png"></a>
		</td>
		
		</tr>
		</c:forEach>
		</tbody>
		</table>
		</aside>
	</section>
<section>
<article>
<h3>Наименование должности</h3>
<div class="text-article">
<form method="POST" action="">

<div class="mb-3 row">
<label for="namerole" class="col-sm-3 col-form-label">Должность</label>
<div class="col-sm-6">
<input type="text" name="namerole" class="form-control" id="staticRole" />
</div>
</div>
<p>
<button type="submit" class="btn btn-primary">Добавить</button>
</p>
</form>
</div>
</article>
</section>
</div>
<jsp:include page="/jspf/footer.jsp" />
</body>
</html>