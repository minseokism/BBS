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
    <form class="form-horizontal" method="post" action="/users/delete">
        <div class="well bs-component">
            <fieldset>
                <legend>Unregister</legend>
                <div><h4 class="text-danger">회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해 주세요.</h4><br/>
                    <p><strong><span class="glyphicon-minus"></span> 사용하고 계신 "${id}" 은 탈퇴할 경우 재사용 및 복구가 불가능합니다.</strong>
                        <ui><li>탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가 하오니 신중하게 선택하시기 바랍니다.</li></ui></p>
                    <p><strong><span class="glyphicon-minus"></span> 탈퇴 후 회원정보 및 서비스 이용 기록은 모두 삭제 됩니다.</strong>
                        <ui><li>탈퇴 후 회원 정보는 개인정보 보호법에 의해 탈퇴후 30일 동안만 기록되며 30일이 지나면 회원님의 개인정보가 자동 삭제 됩니다.</li>
                            <li>회원정보 및 쪽지 이용 기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.</li>
                            <li>삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업해 주세요.</li></p>
                    <p><strong><span class="glyphicon-minus"></span> 탈퇴 후에도 등록한 게시물은 그대로 남아 있습니다.</strong>
                        <ui><li>회원님이 올린 게시글 및 댓글은 탈퇴 시 자동 삭제 되지 않고 그대로 남아 있습니다.</li>
                            <li>삭제를 원하는 게시글이 있다면 반드시 탈퇴 전에 삭제하시기 바랍니다.</li>
                            <li>탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 관리자가 임의로 삭제해 드릴 수 없습니다.</li></ui></p>
                    <p><strong><span class="glyphicon-minus"></span> 게시판에 남아 있는 게시글은 탈퇴 후 삭제할 수 없습니다.</strong></p>
                    <p><strong><span class="glyphicon-minus"></span> 탈퇴 후에는 "${id}" 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</strong></p>
                </div><br/>
                <input type="hidden" name="id" id="id" value="${id}"/>

                <div class="form-group">
                    <label for="pwd" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-9">
                        <input type="password" class="form-control form-margin" name="pwd" id="pwd" placeholder="Password" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-8 col-lg-offset-2">
                        <button type="button" id="submit" onclick="checkSubmit(event)" class="btn btn-primary">회원 탈퇴하기</button>
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
</div>
</div>
<#include "../footer.ftl">
<script src="/js/updateGate.js"></script>
</body>
</html>