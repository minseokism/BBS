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
	<form class="form-horizontal" method="post" action="/users/signup">
	  <fieldset>
	    <legend>Sign Up</legend>
	    
  	 	<div class="form-group">
	      <label for="id" class="col-lg-2 control-label">ID</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control form-margin" name="id" id="id" placeholder="ID" maxlength="30"
				 	onblur="checkId('first')">
	        <span class="errormsg" id="idMsg" style="display:none">필수 정보입니다.</span>
	      </div>	      
	    </div>	    
 
	    <div class="form-group">
	      <label for="name" class="col-lg-2 control-label">Name</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control form-margin" name="name" id="name" placeholder="Name" maxlength="40"
	        		onblur="checkName('check')">
            <span class="errormsg" id="nameMsg" style="display:none">필수 정보입니다.</span>
	      </div>
	    </div>
	    
	    <div class="form-group">
	      <label for="email" class="col-lg-2 control-label">Email</label>
	      <div class="col-lg-10">
	        <input type="email" class="form-control form-margin" name="email" id="email" placeholder="Email" maxlength="40"
	        	onblur="checkEmail('check')">
            <span class="errormsg" id="emailMsg" style="display:none">필수 정보입니다.</span>
	      </div>
	    </div>
	    
	    <div class="form-group">
	      <label for="pwd" class="col-lg-2 control-label">Password</label>
	      <div class="col-lg-10">
	        <input type="password" class="form-control form-margin" name="pwd" id="pwd" placeholder="Password" maxlength="40"
	        		onblur="checkPwd1('check');" onkeypress="checkCapslk(event);" 
	        		onkeyup="checkShiftUp(event);" onKeydown="checkShiftDown(event);">	
       		<span class="errormsg" id="pwdMsg1" style="display:none">필수 정보입니다.</span>
	        <input type="password" class="form-control form-margin form-margin-top" name="pwd2" id="pwd2" maxlength="20"
	        	   placeholder="Confirm Password" onblur="checkPwd2(event)" onkeypress="checkCapslk2(event)" 
	        	   onkeyup="checkShiftUp(event);" onKeydown="checkShiftDown(event);">		
	        <span class="errormsg" id="pwdMsg2" style="display:none">필수 정보입니다.</span> 
	      </div>
	    </div>
	    
	    <div class="form-group">
	      <div class="col-lg-10 col-lg-offset-2">
	        <button type="reset" class="btn btn-default" onclick="resetForm(event)">Cancel</button>
	        <button type="button" id="submit" class="btn btn-primary" onclick="checkSubmit(event)">Submit</button>
	      </div>
	    </div>
	  </fieldset>
	</form>
</div>
<script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="/js/signup.js"></script>
</body>
</html>