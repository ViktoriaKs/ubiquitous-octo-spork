<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.PersonMe"%>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" type="text/css" href="/person/css/style.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
	<meta charset="UTF-8">
	<title>Удаление клиента</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="/person/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/jspf/header.jsp" />
	<div id="main">
		<section>
		<aside class="leftAside">
		<h3>Список клиентов</h3>
		<table class="table table-sm" id="table-info">
		<thead>
			<tr>
				<th scope="col">ID</th>
                <th scope="col">Шифр</th>
                <th scope="col">ИНН</th>
                <th scope="col">Тип</th>
                <th scope="col">Дата регистрации</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="person" items="${persons}">
		<tr>
					<td>${person.getId()}</td>
                    <td>${person.getShifer()}</td>
                    <td>${person.getInn()}</td>
                    <td>${person.getType()}</td>
                    <td>${person.getData()}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</aside>
</section>

	<section>
	<article>
	<h3>Удаление клиента</h3>
	<div class="text-article">
	<form method="POST" action="">
	
	<div class="mb-3 row">
		<label for="id" class="col-sm-4 col-form-label">ID</label>
		<div class="col-sm-6">
		<input type="text" class="form-control" readonly
		value="${personsDelete[0].getId()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="shifer" class="col-sm-4 col-formlabel">Шифр</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="shifer" readonly
	value="${personsDelete[0].getShifer()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="inn" class="col-sm-4 col-formlabel">ИНН</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="inn" readonly
	value="${personsDelete[0].getInn()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="type" class="col-sm-4 col-formlabel">Тип</label>
	<div class="col-sm-6">
	<input type="text" class="form-control" name="type" readonly
	value="${personsDelete[0].getType()}" />
	</div>
	</div>
	
	<div class="mb-3 row">
	<label for="data" class="col-sm-4 col-formlabel">Дата регистрации</label>
	<div class="col-sm-6">
	<input type="date" class="form-control" name="data" readonly
	value="${personsDelete[0].getData()}" />
	</div>
	</div>
	
	
	<button type="submit" class="btn btn-primary">Удалить</button>
	<a href='<c:url value="/personsme" />' role="button"
	class="btn btn-secondary">Отменить/Возврат</a>
	</form>
	</div>
	</article>
	<jsp:include page="/jspf/footer.jsp" />
	</section>
	</div>
	</body>
</html>