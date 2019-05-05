<%@include file="includes/header.jsp"%>

<h1>Google Vision</h1>

<div class="container">
    <c:forEach items="${requestScope.tags}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>
</div>

<%@include file="includes/footer.jsp"%>

