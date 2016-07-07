var idFlag = false;
var pwdFlag = false;
var emailFlag = false;

function checkId(){
	var id = document.getElementById("id").value;
	var idMsg = document.getElementById("idMsg");
	
	if(id == "") {
		idMsg.style.display = "block";
		return false;
	}
	
	var isID = /^[a-z0-9][a-z0-9_\-]{4,29}$/;
	
	if(!isID.test(id)) {
		idMsg.style.display = "block";
		idMsg.innerHTML = "5~30자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		return false;
	}
	
	idFlag = false;
	
}