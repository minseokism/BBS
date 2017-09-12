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
<#include "../unsigninCheck.ftl">

<div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
    <div class="well bs-component">
    <form id="updateForm" class="form-horizontal" method="post" action="/users/update">
        <fieldset>
            <legend>My Account</legend>

            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">Id</label>
                <div class="col-lg-9">
                    <div style="padding-top: 8px;">
                        <strong>${updateUser.id}</strong>
                    </div>
                    <input type="hidden" name="id" id="id" value="${updateUser.id}"/>
                </div>
            </div>


            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">Name</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control form-margin" name="name" id="name" placeholder="Name" maxlength="20"
                           onblur="checkName('check')" value="${updateUser.name}">
                    <span class="errormsg" id="nameMsg" style="display:none">필수 정보입니다.</span>
                </div>
            </div>

            <div class="form-group">
                <label for="email" class="col-lg-2 control-label">Email</label>
                <div class="col-lg-9">
                    <input type="email" class="form-control form-margin" name="email" id="email" placeholder="Email" maxlength="40"
                           onblur="checkEmail('check')" value="${updateUser.email}">
                    <span class="errormsg" id="emailMsg" style="display:none">필수 정보입니다.</span>
                </div>
            </div>

            <div class="form-group">
                <label for="currentPwd" class="col-lg-2 control-label" id="currentPwdLabel">Current Password</label>
                <div class="col-lg-9">
                    <input type="password" class="form-control form-margin" name="currentPwd" id="currentPwd" placeholder="Current Password" maxlength="40"
                           onblur="checkCurrentPwd('check');" onkeypress="checkCapslk(event, 'currentPwdMsg');"
                           onkeyup="checkShiftUp(event);" onKeydown="checkShiftDown(event);">
                    <span class="errormsg" id="currentPwdMsg" style="display:none">회원정보 수정을 위한 필수 정보입니다.</span>
                </div>
            </div>

            <div class="form-group">
                <label for="pwd" class="col-lg-2 control-label label-padding-top-zero">New Password</label>
                <div class="col-lg-9">
                    <input type="password" class="form-control form-margin" name="pwd" id="pwd" placeholder="Change Password" maxlength="40"
                           onblur="checkPwd1('check');" onkeypress="checkCapslk(event, 'pwdMsg1');"
                           onkeyup="checkShiftUp(event);" onKeydown="checkShiftDown(event);">
                    <span class="errormsg" id="pwdMsg1" style="display:none"></span>
                    <input type="password" class="form-control form-margin form-margin-top" name="pwd2" id="pwd2" maxlength="20"
                           placeholder="Confirm Password" onblur="checkPwd2(event)" onkeypress="checkCapslk(event, 'pwdMsg2')"
                           onkeyup="checkShiftUp(event);" onKeydown="checkShiftDown(event);">
                    <span class="errormsg" id="pwdMsg2" style="display:none"></span>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-9 col-lg-offset-2">
                    <button type="button" id="submit" class="btn btn-primary" onclick="checkSubmit(event)">Update</button>
                </div>
            </div>
        </fieldset>
    </form>
    </div>
    <div id="deletelink"><a href="/users/delete">회원탈퇴</a></div>
</div>
<#include "../footer.ftl">
<script src="/js/update.js"></script>
</body>
</html>