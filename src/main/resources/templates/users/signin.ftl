<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>Sign up</title>
	<link rel="stylesheet"
	href="/css/bootstrap.min.css"/>
	<link rel="stylesheet"
	href="/css/users.css"/>
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
		        	maxlength="30" value="${tryId?if_exists}">
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
		        <button type="button" id="submit" onclick="checkSubmit(event)" class="btn btn-primary">Sign In</button>
		      </div>
		    </div>
		    <#assign error = state?if_exists>
   		    <div class="form-group">
	    	  <label class="col-lg-2 control-label"></label>
		      <div class="col-lg-10">
		      <#if error?string == "0">
       		    <span class="errormsg" id="signInMsg" style="display:display"><strong>아이디 또는 비밀번호를 다시 확인하세요.<br>
       		    	등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.<strong></span>
		      </#if>
			  <#if error?string == "1">
       		    <span class="errormsg" id="signInMsg" style="display:display"><strong>비밀번호를 잘못 입력하셨습니다.</strong></span>
		      </#if>
		      </div>	      
		    </div>	    
		   
   	 </fieldset>
	</form>
</div>
<#include "../footer.ftl">
<script src="/js/signin.js"></script>
</body>
</html>