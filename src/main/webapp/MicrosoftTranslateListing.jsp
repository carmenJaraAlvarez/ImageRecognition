<%@include file="includes/header.jsp"%>

<h1>Microsoft translator</h1>

<div class="container">
    <c:forEach items="${requestScope.translated}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>
</div>

<%@include file="includes/footer.jsp"%>

