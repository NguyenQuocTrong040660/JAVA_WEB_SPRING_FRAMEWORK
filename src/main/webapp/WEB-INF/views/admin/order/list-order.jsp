<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
	<div class="content">

		<div class="container-fuild">
			<!-- Page Heading -->


			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">List Order</h6>
					<form action="/admin/filed-orders" method="GET">
						<div class="row">
						
							<div class="col-sm-4">
							 <label for="name" class="col-form-label"><b>Fill
						      </b>
						      </label>
								<select  onchange="this.form.submit()" id="orderStatus"  name="orderStatus">
									<option >Choose</option>
									<option value="1">Confirmed</option>
									<option value="0">New Order</option>
									

								</select>
							</div>

						</div>



						</select>


					</form>
				</div>
				<div class="card-body">

					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">

							<thead style="background-color: #385ece;color: white">
								<tr>
									<th>STT</th>
									<th>Order No</th>
									<th>Order Status</th>
									<th>Order Date</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<c:set var = "i" scope = "session" value = "${orders.number*5}"/>
								
								
								<c:forEach items="${orders.content}" var="item">
									<c:set var="i" value="${i+1}"></c:set>
									<tr>
										<td> <c:out value = "${i}"/></td>
										<td>#BC${item.id}</td>
										<td><c:choose>
												<c:when test="${item.orderStatus == '0'}">
													<span style="color: red"> New Order</span>
												</c:when>
												<c:otherwise>
													<span style="color: green"> Order confirmed</span>
												</c:otherwise>
											</c:choose></td>
										<td>${item.createTime}</td>
										<td><a href="/admin/detail-order?id=${item.id}"  style="border-radius: 45px;"
											class="btn  btn-primary">Order Detail</a></td>
									</tr>
                                    
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>

			<c:if test="${orders.content.size()>0}">
				<nav aria-label="Page navigation example d-flex">
					<ul class="pagination ">
						<li class="page-item"><a class="page-link"
							href="?orderStatus=${orderStatus}&field=${field}&page=${orders.number-1<=0?0:page.number-1}">Previous</a></li>
						<c:forEach var="p" begin="0" end="${orders.totalPages-1}">
							<li class="page-item ${p==orders.number?'active':''}"><a
								class="page-link" href="?orderStatus=${orderStatus}&page=${p}&field=${field}">${p+1}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="?orderStatus=${orderStatus}&page=${orders.number+1>orders.totalPages-1?orders.totalPages-1:orders.number+1}&field=${field}">Next</a></li>
					</ul>
				</nav>
			</c:if>
		</div>


	</div>
</div>