<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>My Account</title>
    <link rel="stylesheet"
		  href="/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="/css/users.css"/>
</head>
<body>
<#include "../header.ftl">
<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
	<form class="form-horizontal" method="post" action="/users/updateForm">
		<fieldset>
			<legend>My Account</legend>

			<input type="hidden" name="id" id="id" value="${id}"/>

			<div class="form-group">
				<label for="pwd" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-8">
					<input type="password" class="form-control form-margin" name="pwd" id="pwd" placeholder="Password" maxlength="20">
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-8 col-lg-offset-2">
					<button type="button" id="submit" onclick="checkSubmit(event)" class="btn btn-primary">Submit</button>
				</div>
			</div>

			<#assign error = error?if_exists>
			<#if error="error">
			<div class="form-group">
				<label class="col-lg-2 control-label"></label>
				<div class="col-lg-10">
					<span class="errormsg" id="signInMsg" style="display:display"><strong>비밀번호를 잘못 입력하셨습니다.</strong></span>
				</div>
			</div>
			</#if>
   	   </fieldset>
	</form>
<#include "../footer.ftl">
<script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="/js/updateGate.js"></script>
</body>
</html>