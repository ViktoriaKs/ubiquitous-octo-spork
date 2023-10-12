<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.PersonMe" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
<meta charset="UTF-8">
<title>Редактирование клиента</title>
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
<h3>Редактирование клиента</h3>
<div class="text-article">
<form method="POST" action="">

<div class="mb-3 row">
<label for="id" class="col-sm-4 col-form-label">ID</label>
<div class="col-sm-6">
<input type="text" class="form-control" readonly
value="${personsEdit[0].getId()}" />
</div>
</div>

<div class="mb-3 row">
        <label for="shifer" class="col-sm-4 col-form-label">Шифр:</label>
        <div class="col-sm-6">
        <input type="text" class="form-control" name="shifer"
        value="${personsEdit[0].getShifer()}" />
    	</div>
    </div>
    
    <div class="mb-3 row">
        <label for="inn" class="col-sm-4 col-form-label">ИНН:</label>
        <div class="col-sm-6">
        <input type="text" name="inn" class="form-control" 
        value="${personsEdit[0].getInn()}" />
      </div>
    </div>
    
    <div class="mb-3 row">
        <label for="type" class="col-sm-4 col-form-label">Тип:</label>
        <div class="col-sm-6">
        <input type="text"  name="type" class="form-control" 
         value="${personsEdit[0].getType()}" />
     </div>
    </div>
    
    <div class="mb-3 row">
        <label for="data" class="col-sm-4 col-form-label">Дата регистрации:</label>
        <div class="col-sm-6">
        <input type="date"  name="data" class="form-control" 
        value="${personsEdit[0].getData()}" />
       </div>
    </div>  

<p>
<button type="submit"
class="btn btn-primary">Редактировать</button>
<a href='<c:url value="/personsme" />'
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