function checkSubmit(event){
    var submit = document.getElementById("submit");
    var pwd = document.getElementById("pwd").value;

    submit.type = "button";

    if (pwd === "") {
        document.getElementById("pwd").focus();
        return false;
    }

    submit.type = "submit";
    return true;
}
