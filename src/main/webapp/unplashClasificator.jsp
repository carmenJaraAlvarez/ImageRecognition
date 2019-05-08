<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>


<div class="container">
<h1>Unsplash Clasificator</h1>
    <p class="message">${message}</p>
${id}
    <c:forEach items="${collectionsForSelect}" var="t">
            <c:out value="${t}"/>
            <br>
            </c:forEach>
</div>

<form action="/add" method="post">
<input type="hidden" name="id" value="${img.id}">
<input type="hidden" name="translated" value="${translated}">  
  <select name="collection" class="browser-default" >
    <option value="" disabled selected>Colección</option>
    <c:forEach items="${requestScope.collectionsForSelect}" var="c">
     <option value="c">c</option>
    </c:forEach>

    
  </select>
  <input type="submit" value=" ">
</form>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>
