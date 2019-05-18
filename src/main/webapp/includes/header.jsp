<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <body>
    <head>
    <style>
 body {
    display: flex;
    min-height: 100vh;
    flex-direction: column;
  }

  main {
    flex: 1 0 auto;
  }
</style>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>tecnogad</title>
    </head>
	<main>
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