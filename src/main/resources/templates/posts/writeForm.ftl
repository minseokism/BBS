<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Board</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/posts.css"/>
    <link rel="stylesheet" href="/css/summernote.css">
    <link rel="stylesheet" href="/css/write.css">
</head>
<body>
<#include "../header.ftl">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
        <form class="form-horizontal" method="post" action="/posts/write">
            <input type="text" class="form-control form-margin-bottom" name="subject" id="subject" placeholder="제목을 입력해주세요">

            <select class="form-control form-margin-bottom" id="select">
                <option>카테고리 선택</option>
                <option>스포츠</option>
                <option>게임</option>
                <option>방송</option>
                <option>정치</option>
                <option>뻘글</option>
            </select>

            <textarea name="content" id="summernote" value=""></textarea>

            <div class="form-button-group">
                <button type="reset" class="btn btn-default" onclick="resetForm(event)">Reset</button>
                <button type="submit" id="submit" class="btn btn-primary">Write</button>
            </div>
        </form>
    </div>
<#include "../footer.ftl">
<script src="/js/summernote.min.js"></script>
<script src="/js/summernote-ko-KR.min.js"></script>
<script src="/js/write.js"></script>
</body>
</html>