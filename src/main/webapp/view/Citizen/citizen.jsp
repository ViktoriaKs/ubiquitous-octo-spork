<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Citizen" %>

<!DOCTYPE html>
<html>
	<link rel="stylesheet" type="text/css" href="/person/css/citizenscss.css">
	<link rel="stylesheet" href="/person/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	  <title>Физические лица</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
                <th scope="col">Редактировать</th>
				<th scope="col">Удалить</th>
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
                    
        <td width="20" id="td-edit">
		<a href='<c:url value="/editcitizens?id=${citizen.getId()}" />'
		role="button" class="btn btn-outline-primary">
		<img alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20"><a
		href='<c:url value="/deletecitizens?id=${citizen.getId()}"/>'
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
    <h3>Добавить новое физ. лицо</h3>
    <div class="text-article">
    <form method="POST" action="">
    
    <div class="mb-3 row">
        <label for="document_id" class="col-sm-3 col-form-label">ID документа</label>
        <div class="col-sm-6">
        <input type="text" name="document_id" class="form-control" id="staticdocument_id"/>
    	</div>
    </div>
    
        <div class="mb-3 row">
        <label for="person_id" class="col-sm-3 col-form-label">ID клиента</label>
        <div class="col-sm-6">
        <input type="text" name="person_id" class="form-control" id="staticperson_id"/>
    	</div>
    </div>
    
        <div class="mb-3 row">
        <label for="first_name" class="col-sm-3 col-form-label">Имя</label>
        <div class="col-sm-6">
        <input type="text" name="first_name" class="form-control" id="staticfirst_name"/>
    	</div>
    </div>
    
        <div class="mb-3 row">
        <label for="second_name" class="col-sm-3 col-form-label">Фамилия</label>
        <div class="col-sm-6">
        <input type="text" name="second_name" class="form-control" id="staticsecond_name"/>
    	</div>
    </div>
    
        <div class="mb-3 row">
        <label for="last_name" class="col-sm-3 col-form-label">Отчество</label>
        <div class="col-sm-6">
        <input type="text" name="last_name" class="form-control" id="staticlast_name"/>
    	</div>
    </div>
    
        <div class="mb-3 row">
        <label for="number" class="col-sm-3 col-form-label">Номер документа</label>
        <div class="col-sm-6">
        <input type="text" name="number" class="form-control" id="staticnumber"/>
    	</div>
    </div>
    	<p>
      	  <button type="submit" class="btn btn-primary">Добавить физ. лицо</button>
        </p>
    </form>
    </div>
     </article>
     </section>
  </div>
  <jsp:include page="/jspf/footer.jsp" />   
</body>
</html>