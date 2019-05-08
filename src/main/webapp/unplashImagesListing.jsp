<%@include file="includes/header.jsp"%>
 <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>


<div class="container">
<h1>Unsplash Images</h1>
    <p class="message">${message}</p>
    <c:forEach items="${images}" var="item">
  <a href="/unplashAddPhoto?id=${item.id}" ><img src=" ${item.urls.small}"><br></a>
   <a class="btn btn-default" href="/googleVisionList?id=${item.id}">clasificar</a>
<!--     <a class="btn btn-default" href="/unplashAddPhoto?id=${item.id}">to collection</a>
   <c:out value="${item.urls.raw}"></c:out><br>
    <c:out value="${item.urls.full}"></c:out>
    <br>--> 
    <br>
	</c:forEach>
	<Br>
<!--              <a class="btn btn-default amber darken-4" href="/unplashCreateCollection">
              Create collection
            </a>-->
            <br/></br>
               <a class="btn btn-default amber darken-4" href="/unplashCollectionsList">
              My Collections
            </a>
            <br/>

</div>
  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
<%@include file="includes/footer.jsp"%>
