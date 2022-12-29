
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="shoping-cart spad">
	<div class="container">
		<div class="row">
			

				

			<div class="card-body">
				<p style="text-align:center;font-weight: bold;font-size:24pt;">My Shopping Cart</p>
							<div class="shoping__cart__table">
					<table class="table table-responsive table-bordered table-hover" id="dataTable"
					width="100%" cellspacing="0">
						<thead style="background-color: #7fad39;color: white" class="color-shopping-cart">
							<tr style="color: white">
								<th>No</th>
								<th class="shoping__product">Products</th>

								<th>Price</th>
								<th>Quantity</th>
								<th>Total</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<% int i=1;%>
							<c:forEach items="${cartProduct}" var="item">
								<tr>
									<td><%=i++%></td>
									<td class="shoping__cart__item"><img
										src="/images/${item.imagePath}" alt="${item.imagePath}"
										width="100px" hieght="100px">
										<h5>${item.name}</h5></td>
									<td class="shoping__cart__price">${item.price}</td>
									<td class="shoping__cart__quantity">
										<div class="quantity">
											<div class="pro-qty" data-url="${item.id}">
												<input type="number" min="1" max="5" step="1" onblur="handel()"
													id="txtnumber" name="quantity" value="${item.quantity}">
											</div>
										</div>
									</td>
									<td class="shoping__cart__total">${item.totalPrice}</td>
									<td class="shoping__cart__item__close"><a href="#"
										style="border-radius: 45px;width: 150px;"
										class="btn btn-danger delete-cart"
										data-url="${item.id}" >Delete</a></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="shoping__cart__btns">
					<a href="/home" class="primary-btn btn " style="border-radius: 45px;background-color: #7fad39;color: white">CONTINUE SHOPPING</a>
					
				</div>
			</div>
			<div class="col-lg-6">
				<div class="shoping__continue">
					<div class="shoping__discount">
						<select id="coupon_id" class="form-control checkout-coupon"
							onchange="checkoutCoupon()">
							<option value="##">Choosing counpon for shopping cart</option>
							<c:forEach items="${coupons}" var="item">
								<option value="${item.id}" data-url="${item.id}">${item.useValue}%
									${item.desCription} ${item.total}</option>
							</c:forEach>



						</select>

						<div>

							<button type="submit" class="site-btn btn-success"
								onClick="functionLooking()">APPLY</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="shoping__checkout">
					<h5>Cart Total</h5>
					<ul>
						<li>SubTotal <span> $ ${sum} </span></li>
						<li>DisCount <span> ${counpon.useValue} % </span></li>
						<li>Total <span> $ ${total_order} </span></li>
					</ul>
					<a href="/home/checkout" class="primary-btn"  style="border-radius: 45px;background-color: #7fad39;color: white">PROCEED TO
						CHECKOUT</a>
				</div>
			</div>
		</div>
	</div>
</section>