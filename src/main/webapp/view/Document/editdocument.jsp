<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Document" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/person/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
<meta charset="UTF-8">
<title>Редактирование документа</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/person/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<div id="main">
<section>
<aside class="leftAside">
<h3>Список документов</h3>
<table class="table table-sm" id="table-info">
<thead>
<tr>
				<th scope="col">ID</th>
                <th scope="col">Наименование</th>
                <th scope="col">Серия</th>
                <th scope="col">Орган выдачи</th>
                <th scope="col">Дата выдачи</th>
</tr>

</thead>
<tbody>
<c:forEach var="document" items="${documents}">
<tr>
					<td>${document.getId()}</td>
                    <td>${document.getName()}</td>
                    <td>${document.getSeriy()}</td>
                    <td>${document.getOrgan()}</td>
                    <td>${document.getData()}</td>
</tr>
</c:forEach>
</tbody>
</table>
</aside>
</section>
<section>
<article>
<h3>Редактирование документа</h3>
<div class="text-article">
<form method="POST" action="">

	<div class="mb-3 row">
		<label for="id" class="col-sm-4 col-form-label">ID</label>
		<div class="col-sm-6">
		<input type="text" class="form-control" readonly
		value="${documentsEdit[0].getId()}" />
		</div>
	</div>

	<div class="mb-3 row">
        <label for="name" class="col-sm-4 col-form-label">Наименование</label>
        <div class="col-sm-6">
        <input type="text" class="form-control" name="name"
        value="${documentsEdit[0].getName()}" />
    	</div>
    </div>
    
    <div class="mb-3 row">
        <label for="seriy" class="col-sm-4 col-form-label">Серия</label>
        <div class="col-sm-6">
        <input type="text" name="seriy" class="form-control" 
        value="${documentsEdit[0].getSeriy()}" />
      </div>
    </div>
    
    <div class="mb-3 row">
        <label for="organ" class="col-sm-4 col-form-label">Орган выдачи</label>
        <div class="col-sm-6">
        <input type="text"  name="organ" class="form-control" 
         value="${documentsEdit[0].getOrgan()}" />
     </div>
    </div>
    
    <div class="mb-3 row">
        <label for="data" class="col-sm-4 col-form-label">Дата выдачи:</label>
        <div class="col-sm-6">
        <input type="date"  name="data" class="form-control" 
        value="${documentsEdit[0].getData()}" />
       </div>
    </div>  

<p>
<button type="submit"
class="btn btn-primary">Редактировать</button>
<a href='<c:url value="/documents" />'
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