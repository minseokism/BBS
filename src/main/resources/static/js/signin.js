function checkSubmit(event){
	var submit = document.getElementById("submit");
	var id = document.getElementById("id").value;
	var pwd = document.getElementById("pwd").value;
	
	submit.type = "button";
	
	if (id === "") {
		document.getElementById("id").focus();
		return false;
	}

	if (pwd === "") {
		document.getElementById("pwd").focus();
		return false;
	}
	
	submit.type = "submit";
	return true;
}