<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Citizen" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
<meta charset="UTF-8">
<title>Редактирование физического лица</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/person/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<div id="main">
<section>
<aside class="leftAside">
<h3>Список физических лиц</h3>
<table class="table table-sm" id="table-info">
<thead>
<tr>
				<th scope="col">ID</th>
                <th scope="col">ID документа</th>
                <th scope="col">ID клиента</th>
                <th scope="col">Имя</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Отчество</th>
                <th scope="col">Номер документа</th>
</tr>

</thead>
<tbody>
<c:forEach var="citizen" items="${citizens}">
<tr>
					<td>${citizen.getId()}</td>
                    <td>${citizen.getDocumentId()}</td>
                    <td>${citizen.getPersonId()}</td>
                    <td>${citizen.getFirstName()}</td>
                    <td>${citizen.getSecondName()}</td>
                    <td>${citizen.getLastName()}</td>
                    <td>${citizen.getNumber()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
</section>
<section>
<article>
<h3>Редактирование</h3>
<div class="text-article">
<form method="POST" action="">

	<div class="mb-3 row">
	<label for="id" class="col-sm-4 col-form-label">ID</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" readonly
	value="${citizensEdit[0].getId()}" />
	</div>
</div>

<div class="mb-3 row">
	<label for="document_id" class="col-sm-4 col-formlabel">ID документа</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="document_id" 
	value="${citizensEdit[0].getDocumentId()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="person_id" class="col-sm-4 col-formlabel">ID клиента</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="person_id" 
	value="${citizensEdit[0].getPersonId()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="first_name" class="col-sm-4 col-formlabel">Имя</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="first_name" 
	value="${citizensEdit[0].getFirstName()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="second_name" class="col-sm-4 col-formlabel">Фамилия</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="second_name" 
	value="${citizensEdit[0].getSecondName()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="last_name" class="col-sm-4 col-formlabel">Отчество</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="last_name" 
	value="${citizensEdit[0].getLastName()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="number" class="col-sm-4 col-formlabel">Номер документа</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="number" 
	value="${citizensEdit[0].getNumber()}" />
	</div>
	</div>

	<p>
	<button type="submit" class="btn btn-primary">Редактировать</button>
<a href='<c:url value="/citizens" />'
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