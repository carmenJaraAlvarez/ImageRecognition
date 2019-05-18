<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>


<div class="container">
<h1>Clasificador</h1>
    <p class="message">${message}</p>

<img src="${img.urls.small}">


<form action="/unplashAddPhoto" method="post">
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="translated" value="${translated}">  
  <select name="collection" class="browser-default" >
    <option value="" disabled selected>A�adir a collecci�n</option>
    <c:forEach items="${requestScope.collectionsForSelect}" var="c">
     <option value="${c}">${c}</option>
    </c:forEach>

    
  </select><br>
  <input class="btn btn-default" type="submit" value="Continuar">
</form>
</div>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>
