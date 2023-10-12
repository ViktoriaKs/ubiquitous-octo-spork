<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.PersonMe" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
	<link rel="stylesheet" type="text/css" href="/person/css/style.css">
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title>Клиенты</title>
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
    <h3>Список клиентов</h3>
	<table class="table table-sm" id="table-info">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Шифр</th>
                <th scope="col">ИНН</th>
                <th scope="col">Тип</th>
                <th scope="col">Дата регистрации</th>
                <th scope="col">Рудактировать</th>
                <th scope="col">Удалить</th>
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
                    
        <td width="20" id="td-edit">
		<a href='<c:url value="/editpersonsme?id=${person.getId()}" />'
		role="button" class="btn btn-outline-primary">
		<img alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20"><a
		href='<c:url value="/deletepersonsme?id=${person.getId()}"/>'
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
    <h3>Добавить нового клиента</h3>
     <div class="text-article">
    <form method="POST" action="">
    
    <div class="mb-3 row">
        <label for="shifer" class="col-sm-3 col-form-label">Шифр:</label>
        <div class="col-sm-6">
        <input type="text" name="shifer" class="form-control" id="staticshifer"/>
    	</div>
    </div>
    <div class="mb-3 row">
        <label for="inn" class="col-sm-3 col-form-label">ИНН:</label>
        <div class="col-sm-6">
        <input type="text" id="staticinn" name="inn" class="form-control" />
      </div>
    </div>
    <div class="mb-3 row">
        <label for="type" class="col-sm-3 col-form-label">Тип:</label>
        <div class="col-sm-6">
        <input type="text"  name="type" class="form-control" id="statictype"/>
     </div>
    </div>
     <div class="mb-3 row">
        <label for="data" class="col-sm-3 col-form-label">Дата регистрации:</label>
        <div class="col-sm-6">
        <input type="date"  name="data" class="form-control" id="staticdata" />
       </div>
    </div>   
        <p>
        <button type="submit" class="btn btn-primary">Добавить клиента</button>
        </p>
        </form>
     </div>
     </article>
     <jsp:include page="/jspf/footer.jsp" /> 
     </section>
     </div>
</body>
</html>