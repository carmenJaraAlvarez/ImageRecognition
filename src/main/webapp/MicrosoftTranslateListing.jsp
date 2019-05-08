<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<h1>Microsoft translator</h1>

<div class="container">
    <c:forEach items="${requestScope.translated}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>
</div>

<%@include file="includes/footer.jsp"%>

