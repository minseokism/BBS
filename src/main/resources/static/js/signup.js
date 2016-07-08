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
				if (result == 'Y') {
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