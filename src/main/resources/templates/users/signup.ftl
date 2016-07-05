<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign up</title>
	<link rel="stylesheet"
	href="/css/bootstrap.min.css"/>
	<link rel="stylesheet"
	href="/css/signup.css"/>
</head>	
<body>
<#include "../header.ftl">
<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
	<form class="form-horizontal" method="post">
	  <fieldset>
	    <legend>Sign Up</legend>
  	 	<div class="form-group">
	      <label for="id" class="col-lg-2 control-label">ID</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="id" placeholder="ID">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="name" class="col-lg-2 control-label">Name</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="name" placeholder="Name">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="email" class="col-lg-2 control-label">Email</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="email" placeholder="Email">
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="inputPassword" class="col-lg-2 control-label">Password</label>
	      <div class="col-lg-5">
	        <input type="password" class="form-control" id="pwd" placeholder="Password">
	      </div>
     	  <div class="col-lg-5">
	        <input type="password" class="form-control" id="pwd_confirmation" placeholder="Confirm Password">
	      </div>
	    </div>
	  
	    <div class="form-group">
	      <div class="col-lg-10 col-lg-offset-2">
	        <button type="reset" class="btn btn-default">Cancel</button>
	        <button type="submit" class="btn btn-primary">Submit</button>
	      </div>
	    </div>
	  </fieldset>
	</form>
</div>
<script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="/js/signup.js"></script>
</body>
</html>