<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
	<div class="content">

		<div class="container">
			<!-- Page Heading -->


			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Filter</h6>
					<form action="/home/filter-order" method="GET">
						<div class="row">



							<div class="">
								<label for="name" class="col-form-label"></label> <select
									onchange="this.form.submit()" id="orderStatus"
									name="orderStatus">
									<option value="1">Choose</option>
									<option value="1">Confirmed</option>
									<option value="0">New Order</option>

								</select>

							</div>



						</div>

						</select>

					</form>
				</div>
				<div class="card-body">
					<p style="text-align:center;font-weight: bold;font-size:24pt;">List Order</p>
					<div class="table-responsive" style="height: 450px">
						<table class="table table-bordered table-hover" id="dataTable"
							width="100%" cellspacing="0">

							<thead style="background-color: #7fad39;color: white" >
								<tr>
									<th>STT</th>
									<th>Order No</th>
									<th>Order Status</th>
									<th>Order Date</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<%
								int i = 1;
								%>
								<c:forEach items="${orders.content}" var="item">
									<tr>
										<td><%=i++%></td>
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
										<td><a href="/home/detail-order?id=${item.id}"
											style="border-radius: 45px;width: 150px;"
											class="btn btn-primary">Order Detail</a>
											 <c:choose>
												<c:when test="${item.orderStatus == '0'}">
													<a href="/home/delete-order?id=${item.id}"

													style="border-radius: 45px;width: 150px"
	 													onclick="return confirm('Do you want order Delete ?')"
														type="button" class="btn btn-danger">Cancel
														Order</a>
												</c:when>
												<c:otherwise>
													<a
											href="#"
											onclick="return confirm('Unremovabale-Confirmed Order!')"
											type="button"
											
						                    style="border-radius: 45px;width: 150px"
											class="btn btn-danger">Cancel Order</a>
												</c:otherwise>
											</c:choose> 
										</td>
									</tr>

								</c:forEach>

							</tbody>
						</table>

						<c:if test="${orders.content.size()>0}">
							<nav aria-label="Page navigation example d-flex">
								<ul class="pagination ">
									<li class="page-item"><a class="page-link"
										href="?orderStatus=${orderStatus}&field=${field}&page=${orders.number-1<=0?0:page.number-1}">Previous</a></li>
									<c:forEach var="p" begin="0" end="${orders.totalPages-1}">
										<li class="page-item ${p==orders.number?'active':''}"><a
											class="page-link"
											href="?orderStatus=${orderStatus}&page=${p}&field=${field}">${p+1}</a></li>
									</c:forEach>
									<li class="page-item"><a class="page-link"
										href="?orderStatus=${orderStatus}&page=${orders.number+1>orders.totalPages-1?orders.totalPages-1:orders.number+1}&field=${field}">Next</a></li>
								</ul>
							</nav>
						</c:if>

					</div>
				</div>
			</div>


		</div>


	</div>
</div>