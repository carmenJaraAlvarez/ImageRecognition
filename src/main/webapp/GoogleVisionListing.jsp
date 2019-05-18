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
            
<form action="/translate" method="post">
<input type="hidden" name="id" value="${img.id}">
<input type="hidden" name="tags" value="${tags}">  
  <select name="lang" class="browser-default" >
    <option value="" disabled selected>Idioma</option>
    <option value="es">Espa�ol</option>
    <option value="de">Alem�n</option>
    <option value="fr">Franc�s</option>
    
  </select>
  <input type="submit" class="btn btn-default" value="Continuar ">
</form>

</div>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>

