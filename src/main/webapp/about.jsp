<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>Tecnogad</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link rel="shortcut icon" href="images/favicon.png">
</head>
<body>
  <nav class="grey" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo">tecno<span class='amber-text'>gad!</span></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="/">Home</a></li>
         <li><a href="/about">About us</a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav  grey lighten-3">
        <li><a href="/">Home</a></li>
         <li><a href="/about">About us</a></li>
      </ul>
      <a href="/" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
   
      <h2 class="header center orange-text">About us</h2>
       

    </div>
    
    <div class="container">
				
		<table id="agenda">
			<c:forEach items="${requestScope.members}" var="entry">
				
					<td>
					
					<div class="card">
					  <img src="${entry.value.photo}" alt="Avatar" style="width:100%">
					  <div class="container" style="height:70px">
					  <p style="color:grey"><c:out value="${entry.value.name}" /><p>					    
					  </div>
					</div>
					
					</td>					

			</c:forEach>
		</table>
		
		
	</div>
    
    
    
    
  </div>


 
  <footer class="page-footer orange">
    <div class="container">

        <div class="col l6 s12">
       
      


        </div>
</div>
    <div class="footer-copyright">
      <div class="container">
       <a class="orange-text text-lighten-3" href="/">Tecnogad!</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>

  </body>
</html>
