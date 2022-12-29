<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link
      href="/vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet" />

    <style type="text/css">
      #messageP,
      #messageCP {
        color: red;
      }
    </style>
  </head>

  <body class="bg-gradient-primary">
    <div class="container">
      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->
          <div class="row">
            <div class="col-lg-5 d-none d-lg-block bg-register-image">
              <img
                src="/img/image-login-register.jpg"
                alt="img"
                width="465px"
                height="465.98px"
              />
            </div>
            <div class="col-lg-7">
              <div class="p-5">
                <div class="text-center">
                  <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                  <h6 style="color: red">${message}</h6>
                </div>
                <form
                  class="user"
                  action="/register"
                  method="POST"
                  onsubmit="return validateRegister()"
                >
                  <div class="form-group">
                    <input
                      type="text"
                      name="username"
                      class="form-control form-control-user"
                      id="exampleFirstName"
                      placeholder="User Name"
                      required="required"
                    />
                  </div>
                  <div class="form-group">
                    <input
                      type="email"
                      name="email"
                      class="form-control form-control-user"
                      id="exampleInputEmail"
                      placeholder="Email"
                      required="required"
                    />
                  </div>
                  <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                      <input
                        type="password"
                        class="form-control form-control-user"
                        id="exampleInputPassword"
                        name="password"
                        placeholder="Password"
                        required="required"
                      />
                    </div>
                    <div class="col-sm-6">
                      <input
                        type="password"
                        class="form-control form-control-user"
                        id="exampleRepeatPassword"
                        placeholder="Repeat Password"
                        required="required"
                      />
                    </div>
                    <div id="messageCP"></div>
                  </div>
                  <button
                    type="submit"
                    class="btn btn-primary btn-user btn-block"
                  >
                    Register Account
                  </button>
                </form>
                <hr />

                <div class="text-center">
                  <a class="small" href="/login"
                    >Already have an account? Login!</a
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <script type="text/javascript">
      function validateRegister() {
        var check = false;

        var pwd = document.document.getElementById("exampleInputPassword");
        var messageP = document.getElementById("messageP");

        console.log("hello" + pwd);
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
          messageCP.innerHTML = "Please enter confirn password";
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
    </script>
  </body>
</html>
