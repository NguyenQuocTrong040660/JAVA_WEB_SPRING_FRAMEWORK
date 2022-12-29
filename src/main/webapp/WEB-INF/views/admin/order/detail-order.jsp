<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
	<div class="content ">

		<div class="container-fuild">
			<!-- Page Heading -->
			<h5>
				<b>Order Number--<c:forEach items="${orderDetails}" var="item" end="0">#BC${item.order.id}</c:forEach></b>
			</h5>

		</div>

		<div class="container-fuild">
			<div class="row">
				<div class="col-md-8 col-sm-6">
					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">
								<table class="table bg-info text-white" id="dataTable" width="100%" cellspacing="0">

									<thead>
										<tr>
											<th>Items Summary</th>
											<th>Price</th>
											<th>Quantity</th>
											<th>Total price</th>

										</tr>
									</thead>
									<tbody class="text-gray-900">

										<c:forEach items="${orderDetails}" var="item">
											<tr>
												<td><image src="/images/${item.product.imagePath}"
														width="50px" height="50px" /> ${item.product.name}</td>
												<td>$ ${item.price}</td>
												<td>x ${item.quantity}</td>
												<td>$ ${item.quantity * item.price}</td>

											</tr>

										</c:forEach>



									</tbody>
								</table>
							</div>
						</div>
					</div>



					<div class="row">
						<div class="col">
							<div class="card shadow mb-4">

								<div class="card-body">
									<div class="table-responsive">
										<table class="table" id="dataTable" width="100%"
											cellspacing="0">
											<thead style="">

												<h6 class="m-0 font-weight-bold text-primary">Customer
													And Order Detail</h6>
											</thead>
											<tbody class="text-gray-900">
												<c:forEach items="${orderDetails}" var="item" end="0">
													<tr>
														<td>Customer Name</td>
														<td>${item.order.user.username}</td>
													</tr>

													<tr>
														<td>Phone Number</td>
														<td>${item.order.shipping.shippingPhone}</td>
													</tr>
													<tr>
														<td>Email</td>
														<td>${item.order.user.email}</td>
													</tr>

													<tr>
														<td>Note</td>
														<td>${item.order.shipping.shippingNote}</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-4 col-sm-6">

					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">
								<table class="table" id="dataTable" width="100%" cellspacing="0">
									<thead style="">
										<h6 class="m-0 font-weight-bold text-lg text-danger">
										
										Order Summary--<c:forEach items="${orderDetails}" var="item" end="0">#BC${item.order.id}</c:forEach></h6>
									</thead>
									<tbody class="text-gray-900">
										<c:forEach items="${orderDetails}" var="item" end="0">
											<tr>
												<td>Order Create</td>
												<td>${item.order.createTime}</td>
											</tr>

											<tr>
												<td>Subtotal</td>
												<td>$ ${subtotal}</td>
											</tr>

											<tr>
												<td>Discount</td>
												<td>${item.order.coupon.useValue}%</td>


											</tr>

											<tr>
												<td>Fee Shipping</td>
												<td>$ 0</td>


											</tr>

											<tr>
												<td class="font-weight-bold ">Total</td>
												<td><b>$ ${total}</b></td>

											</tr>
									</tbody>
								</table>


								<table class="table" id="dataTable" width="100%" cellspacing="0">
									<thead style="">

										<h6 class="m-0 font-weight-bold text-primary">Delivery
											Address</h6>
									</thead>
									<tbody class="text-gray-900">
									<tr>
											<td>Receiver Name </td>
											<td>${item.order.shipping.shippingName}</td>
										</tr>
										<tr>
											<td>Address Line</td>
											<td>${item.order.shipping.shippingAddress}</td>
										</tr>
										<tr>
											<td>Phone Number</td>
											<td>${item.order.shipping.shippingPhone}</td>


										</tr>

										</c:forEach>
									</tbody>
								</table>

							</div>

						</div>

					</div>
					<div>
					<c:forEach items="${orderDetails}" var="item" end="0">
						<c:choose>
												<c:when test="${item.order.orderStatus == '0'}">
                                                   <a href="/admin/status-order?id=${item.order.id}"
						 onclick="return confirm('Do you want order confirmation ${item.order.id}?')"
						 type="button" style="width: 100%;text-align: center;text-decoration: none" 
						 class="btn-success btn-lg">Order confirmation-Sent Mail</a>
                                                 
												</c:when>
												<c:otherwise>
                                                 <a href="#"
						 
						 type="button" style="width: 100%;text-align: center;text-decoration: none" 
						 class="btn-danger btn-lg">Order confirmed</a>
												</c:otherwise>
											</c:choose>
						
					
					</c:forEach>
					</div>
				</div>
			</div>



		</div>


	</div>
</div>