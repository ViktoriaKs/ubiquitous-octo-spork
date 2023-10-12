<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Document"%>

<!DOCTYPE html>
<html>
	<link rel="stylesheet" type="text/css" href="/person/css/style.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Документы</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
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
                <th scope="col">Рудактировать</th>
                <th scope="col">Удалить</th>
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
                    
        <td width="20" id="td-edit">
		<a href='<c:url value="/editdocuments?id=${document.getId()}" />'
		role="button" class="btn btn-outline-primary">
		<img alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20"><a
		href='<c:url value="/deletedocuments?id=${document.getId()}"/>'
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
    <h3>Добавить новый документ</h3>
    <div class="text-article">
    <form method="POST" action="">
    
    <div class="mb-3 row">
        <label for="name" class="col-sm-3 col-form-label">Наименование</label>
        <div class="col-sm-6">
        <input type="text" name="name" class="form-control" id="staticname"/>
    	</div>
    </div>
    
    <div class="mb-3 row">
        <label for="seriy" class="col-sm-3 col-form-label">Серия</label>
        <div class="col-sm-6">
        <input type="text" name="shifer" class="form-control" id="staticseriy"/>
    	</div>
    </div>
    
    <div class="mb-3 row">
        <label for="organ" class="col-sm-3 col-form-label">Орган выдачи</label>
        <div class="col-sm-6">
        <input type="text" name="organ" class="form-control" id="staticorgan"/>
    	</div>
    </div>
    
    <div class="mb-3 row">
        <label for="data" class="col-sm-3 col-form-label">Дата выдачи</label>
        <div class="col-sm-6">
        <input type="date" name="data" class="form-control" id="staticdata"/>
    	</div>
    </div>
    
     <p>
        <button type="submit" class="btn btn-primary">Добавить документ</button>
     </p>
     </form>
     </div>
     </article>
     </section>
     </div>
<jsp:include page="/jspf/footer.jsp" />    
</body>
</html>
