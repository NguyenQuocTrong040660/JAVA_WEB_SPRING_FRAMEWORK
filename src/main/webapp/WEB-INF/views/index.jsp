<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Home</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />

    <!-- Css Styles -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="/css/elegant-icons.css" type="text/css" />
    <link rel="stylesheet" href="/css/nice-select.css" type="text/css" />
    <link rel="stylesheet" href="/css/jquery-ui.min.css" type="text/css" />
    <link rel="stylesheet" href="/css/owl.carousel.min.css" type="text/css" />
    <link rel="stylesheet" href="/css/slicknav.min.css" type="text/css" />
    <link rel="stylesheet" href="/css/style.css" type="text/css" />
    <link rel="stylesheet" href="/css/custom.css" type="text/css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />
  </head>

  <body>
    <div id="preloder">
      <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
      <div class="humberger__menu__logo">
        <a href="#"><img src="img/logo.png" alt="" /></a>
      </div>
      <div class="humberger__menu__cart">
        <ul>
          <li>
            <a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
          </li>
          <li>
            <a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a>
          </li>
        </ul>
        <div class="header__cart__price">item: <span>$150.00</span></div>
      </div>
      <div class="humberger__menu__widget">
        <div class="header__top__right__language">
          <img src="img/language.png" alt="" />
          <div>English</div>
          <span class="arrow_carrot-down"></span>
          <ul>
            <li><a href="#">Spanis</a></li>
            <li><a href="#">English</a></li>
          </ul>
        </div>
        <div class="header__top__right__auth">
          <a href="#"><i class="fa fa-user"></i> Login</a>
        </div>
      </div>
      <nav class="humberger__menu__nav mobile-menu">
        <ul>
          <li class="active"><a href="./index.html">Home</a></li>
          <li><a href="./shop-grid.html">Shop</a></li>
          <li>
            <a href="#">Pages</a>
            <ul class="header__menu__dropdown">
              <li><a href="./shop-details.html">Shop Details</a></li>
              <li><a href="./shoping-cart.html">Shoping Cart</a></li>
              <li><a href="./checkout.html">Check Out</a></li>
              <li><a href="./blog-details.html">Blog Details</a></li>
            </ul>
          </li>
          <li><a href="./blog.html">Blog</a></li>
          <li><a href="./contact.html">Contact</a></li>
        </ul>
      </nav>
      <div id="mobile-menu-wrap"></div>
      <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
      </div>
      <div class="humberger__menu__contact">
        <ul>
          <li><i class="fa fa-envelope"></i> trongb1809534@gmail.com</li>
          <li>Free Shipping for all Order of $99</li>
        </ul>
      </div>
    </div>
    <!-- Humberger End

    <!-- Header Section Begin -->
    <jsp:include page="playout_frontend/header.jsp" />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero hero-normal">
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="hero__categories">
              <div class="hero__categories__all">
                <i class="fa fa-bars"></i> <span>Product Category</span>
              </div>
              <ul>
                <c:forEach items="${categories}" var="item">
                  <li>
                    <a href="/home/filter-product?id=${item.id}"
                      >${item.name}</a
                    >
                  </li>
                </c:forEach>
              </ul>
            </div>
          </div>
          <div class="col-lg-9">
            <div class="hero__search">
              <div class="hero__search__form">
                <form action="/home/search-product">
                  <!-- <div class="hero__search__categories">
                    All Categories <span class="arrow_carrot-down"></span>
                  </div> -->
                  <input
                    type="text"
                    onchange="this.form.submit()"
                    placeholder="Search product by name...."
                    name="name"
                    id="searchProduct"
                  />
                  <button type="submit" class="site-btn">SEARCH</button>
                </form>
              </div>
              <div class="hero__search__phone">
                <div class="hero__search__phone__icon">
                  <i class="fa fa-phone"></i>
                </div>
                <div class="hero__search__phone__text">
                  <h5>+65 11.188.888</h5>
                  <span>support 24/7 time</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section
      class="breadcrumb-section set-bg"
      data-setbg="/images/Bike_Banner.jpg"
    >
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="breadcrumb__text">
              <h2>TOP CYCLE</h2>
              <div class="breadcrumb__option" style="height: 100px"></div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Body -->

    <jsp:include page="${body}" />

    <!-- Body End -->

    <!-- Footer Section Begin -->
    <jsp:include page="playout_frontend/footer.jsp" />
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.nice-select.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/jquery.slicknav.js"></script>
    <script src="/js/mixitup.min.js"></script>
    <script src="/js/owl.carousel.min.js"></script>
    <script src="/js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
      //

      var toastMixin = Swal.mixin({
        toast: true,
        icon: "success",
        title: "General Title",
        animation: true,
        position: "top-right",
        showConfirmButton: false,
        timer: 5000,
        timerProgressBar: false,
        didOpen: (toast) => {
          toast.addEventListener("mouseenter", Swal.stopTimer);
          toast.addEventListener("mouseleave", Swal.resumeTimer);
        },
      });

      //

      //Cap nhat gio hang
      $(".pro-qty").click(function handel() {
        var qty = document.getElementById("txtnumber").value;
        let key = $(this).data("url");

        updatecart(key, qty);
      });

      //Cap nhat gio hang
      function updatecart(key, qty) {
        $.ajax({
          url: "/home/update-cart?id=" + key + "&quantity=" + qty,
          success: function (data) {
            if (data.code == 200) {
              toastMixin.fire({
                animation: true,
                title: "Update quantity Successfully",
              });
              setTimeout(function () {
                window.location.reload(1);
              }, 2000);
            } else {
              toastMixin.fire({
                title: "Update quantity failed",
                icon: "error",
              });
            }
          },
        });
      }

      //End Cap nhat gio hang

      //Delete Cart

      function deleteCart(event) {
        event.preventDefault();
        let key = $(this).data("url");

        $.ajax({
          url: "/home/delete-cart?id=" + key,
          success: function (data) {
            if (data.code == 200) {
              toastMixin.fire({
                animation: true,
                title: "Delete cart Successfully",
              });

              setTimeout(function () {
                window.location.reload(true);
              }, 1000);
            } else {
              toastMixin.fire({
                title: "Delete cart failed",
                icon: "error",
              });
            }
          },
        });
      }

      $(function () {
        $(".delete-cart").on("click", deleteCart);
      });

      //End Detele Cart

      //Add to Cart
      slg_Adtocart();
      function slg_Adtocart() {
        $.ajax({
          url: "/home/number-cart",
          method: "GET",
          success: function (data) {
            $("#number_cart").html(data);
          },
        });
      }

      function addTocart(event) {
        event.preventDefault();

        let urlCart = $(this).data("url");

        $.ajax({
          type: "GET",
          url: urlCart,
          dataType: "json",
          success: function (data) {
            console.log(data);
            if (data.code === 200) {
              toastMixin.fire({
                animation: true,
                title: "Add cart Successfully",
              });
            } else {
              toastMixin.fire({
                title: "Cart exist!",
                icon: "error",
              });
            }
            slg_Adtocart();
          },
          error: function () {},
        });
      }
      $(function () {
        $(".add-to-cart").on("click", addTocart);
      });

      //End Add to Cart

      //Check Coupon reload page

      function functionLooking() {
        location.reload(true);
      }

      //OnChange
      function checkoutCoupon() {
        var id = document.getElementById("coupon_id").value;

        checkCoupon(id);
      }

      //when add counpon for shopping cart
      function checkCoupon(key) {
        $.ajax({
          url: "/home/check-coupon?id=" + key,
          method: "GET",
          success: function (data) {
            if (data.code === 200) {
              toastMixin.fire({
                animation: true,
                title: "Add coupon successfull",
              });
            } else {
              toastMixin.fire({
                title: "Add coupon failed",
                icon: "error",
              });
            }
          },
        });
      }

      //when click url shoppoing cart=> alert message
      function checkShopping() {
        $.ajax({
          url: "/home/checkShopping",
          method: "GET",
          success: function (data) {
            if (data.code === 200) {
              toastMixin.fire({
                animation: true,
                title: "No Shopping Cart",
                icon: "error",
              });
            } else {
              toastMixin.fire({
                title: "Go Shopping Cart",
              });
            }
          },
        });
      }
    </script>
  </body>
</html>
