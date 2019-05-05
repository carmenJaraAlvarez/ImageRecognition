<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  


<div class="container">
<h1>Unsplash Collections</h1>
    <p class="message">${message}</p>
  <table><tbody>
  <tr><th><c:out value="Nombre"></c:out></th><th><c:out value="Publicar"></c:out></th><th><c:out value="Eliminar"></c:out></th></tr>
    <c:forEach items="${unplashcollections}" var="item">

    <tr><td>
   ${item.title}
   </td><td>
   <c:if test="${not item.additionalProperties.published}"><b>
   <a href="/unplashPublishCollection?id=${item.id}">publicar</a></b>

   </c:if>
      <c:if test="${item.additionalProperties.published}">
   <c:out value="publicada"></c:out>

   </c:if>
   </td><td>
      <a href="/unplashDeleteCollection?id=${item.id}">
      <i class="material-icons white">delete</i></a></td>
      </tr>
   
 
  </c:forEach>
   </tbody>
   </table>
</div>
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>
