function validateRegister() {
  var check = false;

  var pwd = document.getElementById("exampleInputPassword");
  var messageP = document.getElementById("messageP");
  if (pwd.value == "") {
    pwd.style.borderColor = "red";
    messageP.innerHTML = "Vui long nhap Password";
    check = false;
  } else if (firstName.value != "") {
    pwd.style.borderColor = "green";
    messageP.innerHTML = "";
    check = true;
  }

  var confirmpwd = document.getElementById("exampleRepeatPassword");
  var messageCP = document.getElementById("messageCP");
  if (confirmpwd.value == "") {
    confirmpwd.style.borderColor = "red";
    messageCP.innerHTML = "Vui long nhap confirn password";
    check = false;
  } else if (pwd.value != confirmpwd.value) {
    confirmpwd.style.borderColor = "red";
    messageCP.innerHTML = "Confirm password not currency";
    check = false;
  } else {
    confirmpwd.style.borderColor = "green";
    messageCP.innerHTML = "";
    check = true;
  }

  return check;
}
