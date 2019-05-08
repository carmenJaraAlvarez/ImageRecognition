<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  

<div class="container">
<h1>Imagen clasificada</h1>
<img  src=" ${img.urls.small}"><br>

    <c:forEach items="${requestScope.tags}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>

</div>

<%@include file="includes/footer.jsp"%>

