window.onload = function () {
    document.getElementById("password").onchange = validatePassword;
    document.getElementById("password_confirmation").onchange = validatePassword;
}
function validatePassword(){
var pass2=document.getElementById("password_confirmation").value;
var pass1=document.getElementById("password").value;
if(pass1!=pass2)
    document.getElementById("password_confirmation").setCustomValidity("Пароли не совпадают");
else
    document.getElementById("password_confirmation").setCustomValidity('');
}

var checkform = function(){
	if ($('input[name=email]').val()==null) {
		return false;
	}
	$.ajax({
		  url: "/Task2/app/email",
		  data: {
			 email:$('input[name=email]').val()
		  }
		}).done(function(data) {
			if (data==0) {
				swal("Такая почта уже занята");
				return false;
			} else {
				return true;
			}
		});
}