<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<h1>Microsoft translator</h1>

<div class="container">
<img  src=" ${img.urls.small}"><br>
    <c:forEach items="${requestScope.translated}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>
            <form action="/unplashClasificator" Method="post">
            <input type=hidden name="id" value="${img.id}">
             <input type=hidden name="translated" value="${translated}">
             <input type=submit value=" ">
            </form>

</div>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>

