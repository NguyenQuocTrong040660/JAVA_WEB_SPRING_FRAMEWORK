<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="shoping-cart spad">
  <div class="container">
    <div class="checkout__form">
      <h4>Billing Details</h4>
      <form action="/home/checkout" method="POST">
        <div class="row">
          <div class="col-lg-6 col-md-4 form-group">
            <div class="form-group">
              <p>Name<span>*</span></p>
              <input
                type="text"
                placeholder="Name"
                name="shippingName"
                required="required"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <p>Address<span>*</span></p>
              <input
                type="text"
                placeholder="Street Address"
                name="shippingAddress"
                required="required"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <p>Phone<span>*</span></p>
              <input
                class="form-control"
                type="number"
                name="shippingPhone"
                required="required"
              />
            </div>

            <div class="form-group">
              <p>Order notes<span>*</span></p>
              <input
                type="text"
                class="form-control"
                name="shippingNote"
                placeholder="Notes about your order, e.g. special notes for delivery."
              />
            </div>
          </div>
          <div class="col-lg-6 col-md-8">
            <c:choose>
              <c:when test="${cartPurchuse != null }">
                <div class="checkout__order">
                  <h4>Your Order</h4>
                  <div class="checkout__order__products">
                    Products <span>Total</span>
                  </div>
                  <ul>
                    <li>
                      ${cartPurchuse.name}<span>
                        ${cartPurchuse.quantity} x ${cartPurchuse.price}</span
                      >
                    </li>

                    <div class="checkout__order__subtotal">
                      Subtotal
                      <span>${cartPurchuse.quantity * cartPurchuse.price}</span>
                    </div>
                    <div class="checkout__order__subtotal">
                      Discount <span> $0</span>
                    </div>
                    <div class="checkout__order__total">
                      Total
                      <span>
                        $ ${cartPurchuse.quantity * cartPurchuse.price}
                      </span>
                    </div>
                  </ul>

                  <p></p>

                  <button
                    type="submit"
                    class="site-btn"
                    style="
                      border-radius: 45px;
                      background-color: #7fad39;
                      color: white;
                    "
                  >
                    PLACE ORDER
                  </button>
                </div>
              </c:when>
              <c:otherwise>
                <div class="checkout__order">
                  <h4>Your Order</h4>
                  <div class="checkout__order__products">
                    Products <span>Total</span>
                  </div>
                  <ul>
                    <c:forEach items="${cartProduct}" var="item">
                      <li>
                        ${item.name}<span>
                          ${item.quantity} x ${item.price}</span
                        >
                      </li>
                    </c:forEach>
                  </ul>
                  <div class="checkout__order__subtotal">
                    Subtotal <span>${sum}</span>
                  </div>
                  <div class="checkout__order__subtotal">
                    Discount <span> ${counpon.useValue} %</span>
                  </div>
                  <div class="checkout__order__total">
                    Total <span> $ ${total_order} </span>
                  </div>

                  <p></p>

                  <button type="submit" class="site-btn">PLACE ORDER</button>
                </div>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="row"></div>
</section>
