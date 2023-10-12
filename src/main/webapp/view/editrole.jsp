<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Role"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
<meta charset="UTF-8">
<title>Редактирование должности</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/person/css/bootstrap.min.css">
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
</tr>

</thead>
<tbody>
<c:forEach var="role" items="${roles}">
<tr>
<td>${role.getId()}</td>
<td>${role.getNamerole()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
</section>
<section>
<article>
<h3>Редактирование должности</h3>
<div class="text-article">
<form method="POST" action="">
<div class="mb-3 row">
<label for="idrole" class="col-sm-4 col-form-label">
Код должности</label>
<div class="col-sm-6">
<input type="text" class="form-control" readonly
value="${rolesEdit[0].getId()}" />
</div>
</div>
<div class="mb-3 row">
<label for="namerole"
class="col-sm-4 col-form-label">Должность</label>
<div class="col-sm-6">
<input type="text" class="form-control" name="namerole"
value="${rolesEdit[0].getNamerole()}" />
</div>
</div>
<p>
<button type="submit"
class="btn btn-primary">Редактировать</button>
<a href='<c:url value="/roles" />'
role="button"
class="btn btn-secondary">Отменить/Возврат</a>
</p>
</form>
</div>
</article>
<jsp:include page="/jspf/footer.jsp" />
</section>
</div>
</body>
</html>