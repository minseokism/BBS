// 제출할때 모두 true일때만 회원가입 가능
var currentPwdFlag = false;
var nameFlag = true;
var emailFlag = true;
var pwdFlag = true;
var pwdFlag2 = true;

/*
$(function () {
    $("form").submit(function event) {

    }
})
*/

function checkName(event){
    var name = document.getElementById("name").value;
    var nameMsg = document.getElementById("nameMsg");
    nameFlag = false;

    if(name === "") {
        nameMsg.className = "errormsg";
        nameMsg.style.display = "block";
        nameMsg.innerHTML = "필수 정보입니다."
        return false;
    }

    //id 체크 정규식
    var isName = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]/gi;

    //id 유효성 체크
    if(isName.test(name)) {
        nameMsg.className = "errormsg";
        nameMsg.style.display = "block";
        nameMsg.innerHTML = "이름에는 한글, 영문 대소문자를 이용해 주세요.";
        return false;
    }

    if(true) {
        nameMsg.style.display = "none";
        nameFlag = true;
        return true;
    }

    return true;
}

function checkEmail(event){
    var id = document.getElementById("id").value;
    var email = document.getElementById("email").value;
    var emailMsg = document.getElementById("emailMsg");
    emailFlag = false;

    console.log(email);

    //숨겨둔 메세지 보이게
    if(email === "") {
        emailMsg.className = "errormsg";
        emailMsg.style.display = "block";
        emailMsg.innerHTML = "필수 정보입니다."
        return false;
    }

    //email 체크 정규식
    var isEmail =  /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var isHan = /[ㄱ-ㅎ가-힣]/g;

    //email 유효성 체크
    if(!isEmail.test(email) || isHan.test(email)) {
        emailMsg.className = "errormsg";
        emailMsg.style.display = "block";
        emailMsg.innerHTML = "올바른 email 주소를 작성해주세요";
        return false;
    }

    //email 중복 체크
    $.ajax({
        type:"POST",
        url:"/users/checkInUpdate",
        data:{email:email, id:id},
        success:function(result){
            if (result === false) {
                emailMsg.style.display = "block";
                emailMsg.className = "errormsg gr";
                emailMsg.innerHTML = "가능한 Email 입니다.";
                emailFlag = true;
                return true;
            } else {
                emailMsg.className = "errormsg";
                emailMsg.style.display = "block";
                emailMsg.innerHTML = "중복된 Email 입니다.";
                return false;
            }
        },
        error:function(e){
            alert(e.responseText);
        }
    });


    return true;
}

function checkCurrentPwd(event) {
    var pwd = document.getElementById("currentPwd").value;
    var pwdMsg = document.getElementById("currentPwdMsg");
    currentPwdFlag = false;

    if (pwd === "") {
        pwdMsg.className = "errormsg";
        pwdMsg.style.display = "block";
        pwdMsg.innerHTML = "회원정보 수정을 위한 필수 정보입니다."
        return false;
    } else {
        pwdMsg.className = "errormsg";
        pwdMsg.style.display = "none";
        currentPwdFlag = true;
        return true
    }
}

function checkPwd1(event){
    checkPwd2("check");
    pwdFlag = false;

    var pwd1 = document.getElementById("pwd").value;
    var pwdMsg1 = document.getElementById("pwdMsg1");

    //숨겨둔 메세지 보이게
    if (pwd1 === "") {
        pwdFlag = true;
        pwdFlag2 = true;
        pwdMsg1.style.display = "none";
        pwdMsg2.style.display = "none";
        return false;
    } else {
        pwdMsg2.className = "errormsg"
        pwdMsg2.style.display = "block";
        pwdMsg2.innerHTML = "필수 정보입니다."
    }

    if (!isValidPwd(pwd1)){
        pwdMsg1.style.display = "block";
        pwdMsg1.className = "errormsg";
        pwdMsg1.innerHTML = "6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        return false;
    } else {
        pwdMsg1.className = "errormsg gr"
        pwdMsg1.style.display = "block";
        pwdMsg1.innerHTML = "가능합니다."

        pwdFlag=true;
        return true;
    }

    return true;
}

function checkPwd2(event){
    var pwd1 = document.getElementById("pwd").value;
    var pwd2 = document.getElementById("pwd2").value;
    var pwdMsg2 = document.getElementById("pwdMsg2");
    pwdFlag2 = false;

    if (pwd2 === "") {
        pwdFlag2 = true;
        return false;
    }

    if (pwd1 != pwd2) {
        pwdMsg2.className = "errormsg";
        pwdMsg2.style.display = "block";
        pwdMsg2.innerHTML = "비밀번호가 일치하지 않습니다.";
        document.getElementById("pwd2").value = "";
        return false;
    } else {
        pwdMsg2.className = "errormsg gr";
        pwdMsg2.style.display = "block";
        pwdMsg2.innerHTML = "일치합니다."
        pwdFlag2 = true;
        return true;
    }

    return true;
}

function checkSubmit(event){
    var submit = document.getElementById("submit");
    submit.type = "button";

    var id = document.getElementById("id").value;
    var pwd = document.getElementById("currentPwd").value;
    var pwdMsg = document.getElementById("currentPwdMsg");

    if (!nameFlag) {
        document.getElementById("name").focus();
        return false;
    }

    if (!emailFlag) {
        document.getElementById("email").focus();
        return false;
    }

    if (!currentPwdFlag) {
        pwdMsg.className = "errormsg";
        pwdMsg.style.display = "block";
        pwdMsg.innerHTML = "회원정보 수정을 위한 필수 정보입니다."
        document.getElementById("currentPwd").focus();
        return false;
    }

    if (!pwdFlag) {
        document.getElementById("pwd").focus();
        return false;
    }

    if (!pwdFlag2) {
        document.getElementById("pwd2").focus();
        return false;
    }

    $.ajax({
        type:"POST",
        url:"/users/checkInUpdate",
        data:{pwd:pwd, id:id},
        async:false,
        success:function(result){
            if (result === false) {
                pwdMsg.className = "errormsg";
                pwdMsg.style.display = "block";
                pwdMsg.innerHTML = "비밀번호가 틀렸습니다.";
                document.getElementById("currentPwd").focus();
                checkPwd = false;
                return false;
            } else {
                submit.type = "submit";
                return true;
            }
        },
        error:function(e){
            alert(e.responseText);
        }
    });
}

var isShift = false;
function checkShiftUp(e) {
    if (e.which && e.which == 16) {
        isShift = false;
    }
}

function checkShiftDown(e) {
    if (e.which && e.which == 16) {
        isShift = true;
    }
}

function checkCapslk(e, location){
    var myKeyCode = 0;
    var myShiftKey = false;

    if (window.event) { // IE
        myKeyCode = e.keyCode;
        myShiftKey = e.shiftKey;
    } else if (e.which) { // netscape ff opera
        myKeyCode = e.which;
        myShiftKey = isShift;
    }

    var pwdMsg = document.getElementById(location);

    if ((myKeyCode >= 65 && myKeyCode <= 90) && !myShiftKey) {
        pwdMsg.style.display = "block";
        pwdMsg.className = "errormsg";
        pwdMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
    } else if ((myKeyCode >= 97 && myKeyCode <= 122) && myShiftKey) {
        pwdMsg.style.display = "block";
        pwdMsg.className = "errormsg";
        pwdMsg.innerHTML = "Caps Lock이 켜져 있습니다.";
    } else {
        pwdMsg.style.display = "none";
    }
}

function checkSpace(str) {
    if (str.search(/\s/) != -1) {
        return true;
    } else {
        return false;
    }
}

function isValidPwd(str) {
    var cnt = 0;
    if (str === "") {
        return false;
    }

    var retVal = checkSpace(str);
    if (retVal) {
        return false;
    }
    if (str.length < 6) {
        return false;
    }
    for (var i = 0; i < str.length; ++i) {
        if (str.charAt(0) == str.substring(i, i + 1))
            ++cnt;
    }
    if (cnt === str.length) {
        return false;
    }

    var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{6,16}$/;
    if (!isPW.test(str)) {
        return false;
    }

    return true;
}