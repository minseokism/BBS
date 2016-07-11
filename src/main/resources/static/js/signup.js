// 제출할때 모두 true일때만 회원가입 가능
var idFlag = false;
var pwdFlag = false;
var emailFlag = false;

function checkId(){
	var id = document.getElementById("id").value;
	var idMsg = document.getElementById("idMsg");
	
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
	
	idFlag = false;
	
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

function checkName(){
	var name = document.getElementById("name").value;
	var nameMsg = document.getElementById("nameMsg");
	
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
		return true;
	}
	
	return true;
}
function checkEmail(){
	var email = document.getElementById("email").value;
	var idEmail = document.getElementById("emailMsg");
	
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
	
	emailFlag = false;
	
	//id 중복 체크
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
function checkPwd1(){
	
}

function checkPwd2(){
	
}

function checkSubmit(){
	
}