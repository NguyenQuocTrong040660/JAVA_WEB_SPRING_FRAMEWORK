<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<header class="header">
  <div class="header__top">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 col-md-6">
          <div class="header__top__left">
            <ul>
              <li><i class="fa fa-envelope"></i> trongb1809534@gmail.com</li>
              <li>Free Shipping for all Order</li>
            </ul>
          </div>
        </div>
        <div class="col-lg-6 col-md-6">
          <div class="header__top__right">
            <div class="header__top__right__social">
              <a href="#"><i class="fa fa-facebook"></i></a>
              <a href="#"><i class="fa fa-twitter"></i></a>
              <a href="#"><i class="fa fa-linkedin"></i></a>
              <a href="#"><i class="fa fa-pinterest-p"></i></a>
            </div>
            <div class="header__top__right__language">
              <c:choose>
                <c:when test="${userDetail == null}">
                  <div>
                    <i class="fa fa-user"></i
                    ><a class="login-header" href="/login">Login</a>
                  </div>
                </c:when>
                <c:otherwise>
                  <li class="fa fa-user">
                    <a class="login-header" href="#">${userDetail.username} </a>
                    <ul class="header__menu__dropdown category-hover">
                      <li><a href="/logout">Logout</a></li>
                    </ul>
                  </li>
                </c:otherwise>
              </c:choose>
            </div>
            <div class="header__top__right__auth"></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <div class="header__logo">
          <a href="<%=request.getContextPath()%>/home"
            ><img src="/img/logo.png" alt="logo" width="250" height="50"
          /></a>
        </div>
      </div>
      <div class="col-lg-6">
        <nav class="header__menu">
          <ul>
            <li><a href="<%=request.getContextPath()%>/home">Home</a></li>

            <li>
              <a
                href="/home/shopping-cart"
                id="checkShopping"
                onClick="checkShopping()"
                >Shoping Cart</a
              >
            </li>
            <li><a href="/home/order">Order</a></li>

            <li><a href="/home/contact">Contact</a></li>
          </ul>
        </nav>
      </div>

      <div class="col-lg-3">
        <div class="header__cart">
          <ul>
            <li>
              <a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
            </li>
            <li>
              <a href="/home/shopping-cart"
                ><i class="fa fa-shopping-bag"></i>
                <span id="number_cart"> </span
              ></a>
            </li>
          </ul>

          <div class="header__cart__price"></div>
        </div>
      </div>
    </div>
    <div class="humberger__open">
      <i class="fa fa-bars"></i>
    </div>
  </div>
</header>
