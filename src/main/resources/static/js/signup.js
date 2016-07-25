// 제출할때 모두 true일때만 회원가입 가능
var idFlag = false;
var pwdFlag = false;
var nameFlag = false;
var emailFlag = false;

function checkId(event){
	var id = document.getElementById("id").value;
	var idMsg = document.getElementById("idMsg");
	idFlag = false;
	
	//숨겨둔 메세지 보이게
	if(id == "") {
		idMsg.className = "errormsg";
		idMsg.style.display = "block";
		idMsg.innerHTML = "필수 정보입니다."
		return false;
	}
	
	//id 체크 정규식
	var isID = /^[a-z0-9][a-z0-9_\-]{4,29}$/;
	
	//id 유효성 체크
	if(!isID.test(id)) {
		idMsg.className = "errormsg";
		idMsg.style.display = "block";
		idMsg.innerHTML = "5~30자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		return false;
	}
	
	//id 중복 체크
	try {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "/users/check?id="+id);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText;
				if (result == 'false') {
					idMsg.style.display = "block";
					idMsg.className = "errormsg gr";
					idMsg.innerHTML = "가능한 ID 입니다.";
					idFlag = true;
					return true;
				} else {
					idMsg.className = "errormsg";
					idMsg.style.display = "block";
					idMsg.innerHTML = "중복된 ID 입니다.";
					return false;
				}
			} 
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}
	
	return true;
}

function checkName(event){
	var name = document.getElementById("name").value;
	var nameMsg = document.getElementById("nameMsg");
	nameFlag = false;
	
	if(name == "") {
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
	var email = document.getElementById("email").value;
	var idEmail = document.getElementById("emailMsg");
	emailFlag = false;
	
	//숨겨둔 메세지 보이게
	if(email == "") {
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
	try {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "/users/check?email="+email);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var result = xmlhttp.responseText;
				if (result == 'false') {
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
			} 
		};
		xmlhttp.send(null);
	} catch (e) {
		if (window.bridgeGotTime) {
			throw e;
		}
	}
	
	return true;
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

function checkCapslk(e){
	var myKeyCode = 0;
	var myShiftKey = false;
	
	if (window.event) { // IE
		myKeyCode = e.keyCode;
		myShiftKey = e.shiftKey;
	} else if (e.which) { // netscape ff opera
		myKeyCode = e.which;
		myShiftKey = isShift;
	}
	
	var pwdMsg = document.getElementById("pwdMsg1");

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
	if (str == "") {
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
	if (cnt == str.length) {
		return false;
	}

	var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{6,16}$/;
	if (!isPW.test(str)) {
		return false;
	}

	return true;
}

function checkCapslk2(e){
	var myKeyCode = 0;
	var myShiftKey = false;
	
	if (window.event) { // IE
		myKeyCode = e.keyCode;
		myShiftKey = e.shiftKey;
	} else if (e.which) { // netscape ff opera
		myKeyCode = e.which;
		myShiftKey = isShift;
	}
	
	var pwdMsg = document.getElementById("pwdMsg2");

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

function checkPwd1(event){
	checkPwd2("check");
	pwdFlag = false;
	
	var pwd1 = document.getElementById("pwd").value;
	var pwdMsg1 = document.getElementById("pwdMsg1");
	
	//숨겨둔 메세지 보이게
	if(pwd1 == "") {
		pwdMsg1.className = "errormsg";
		pwdMsg1.style.display = "block";
		pwdMsg1.innerHTML = "필수 정보입니다."
		return false;
	}
	
	if(!isValidPwd(pwd1)){
		pwdMsg1.style.display = "block";
		pwdMsg1.className = "errormsg";
		pwdMsg1.innerHTML = "6~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		return false;
	}else {
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
	
	if (pwd2 == "") {
		pwdMsg2.className = "errormsg";
		pwdMsg2.style.display = "block";
		pwdMsg2.innerHTML = "필수 정보입니다.";	
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
		return true;
	}

	return true;
}

function checkSubmit(event){
	var submit = document.getElementById("submit");
	submit.type = "button";
	
	if (!idFlag) {
		document.getElementById("id").focus();
		return false;
	}
	
	if (!nameFlag) {
		document.getElementById("name").focus();
		return false;
	}
	
	if (!emailFlag) {
		document.getElementById("email").focus();
		return false;
	}
	
	if (!pwdFlag) {
		document.getElementById("pwd1").focus();
		return false;
	}
	
	submit.type = "submit";
	return true;
}

function resetForm(event){
	var lis = document.getElementsByClassName('errormsg');
    for(var i=0; i < lis.length; i++){
        lis[i].style.display='none';   
    }
}