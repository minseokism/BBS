<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>Sign up</title>
	<link rel="stylesheet"
	href="/css/bootstrap.min.css"/>
	<link rel="stylesheet"
	href="/css/signup.css"/>
</head>	
<body>
<#include "../header.ftl">
<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
	<form class="form-horizontal" method="post" action="/users/signin">
	 <fieldset>
		    <legend>Sign In</legend>
		    
	  	 	<div class="form-group">
		      <label for="id" class="col-lg-2 control-label">ID</label>
		      <div class="col-lg-8">
		        <input type="text" class="form-control" name="id" id="id" placeholder="ID" 
		        	maxlength="30">
		      </div>	      
		    </div>	    
	 
		    <div class="form-group">
		      <label for="pwd" class="col-lg-2 control-label">Password</label>
		      <div class="col-lg-8">
		        <input type="password" class="form-control form-margin" name="pwd" id="pwd" placeholder="Password" 
		        	maxlength="20">
	        	<div class="checkbox">
		          <label>
		            <input type="checkbox">Stay signed in
		          </label>	
        		</div>
		      </div>
		    </div>
		    
    	    <div class="form-group">
		      <div class="col-lg-8 col-lg-offset-2">
		        <button type="submit" id="submit" class="btn btn-primary">Sign In</button>
		      </div>
		    </div>
   	 </fieldset>
	</form>
</div>
<#include "../footer.ftl">
<script src="/js/signin.js"></script>
</body>
</html>