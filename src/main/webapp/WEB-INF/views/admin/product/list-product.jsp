<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
<div class="content">

	<div class="container-fuild">
		<!-- Page Heading -->
		

		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">List Product / <a href="/admin/add-product">Add new Product</a>
				
				</h6>
				<p style="color:red">${message}</p>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">

						<thead style="background-color: #385ece;color: white">
							<tr>
								<th>ID</th>
								<th>Product name</th>
								<th>Price</th>
								<th>Content</th>
								<th>Image</th>

								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var = "i" value = "${products.number*5}"/>
	
                           

							<c:forEach items="${products.content}" var="item">
								<c:set var="i" value="${i+1}"></c:set>
								<tr>
									<td> <c:out value = "${i}"/></td>
									<td>${item.name}</td>
									<td>${item.price}</td>
									<td>${item.content}</td>
									<td><image src="/images/${item.imagePath}" alt="no image"
											with="100px" height="100px"></td>
									<td> <span><a href="/admin/update-product?id=${item.id}"
										style="border-radius: 45px;width: 100px"
										class="btn btn-primary">Update</a>
										<a
										href="/admin/delete-product?id=${item.id}"
										onclick="return confirm('Do you want delete product ${item.name}?')"
										style="border-radius: 45px;width: 100px"
										class="btn btn-danger">Delete</a></span> </td>
								</tr>

							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<c:if test="${products.content.size()>0}">
			<nav aria-label="Page navigation example d-flex">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						href="?field=${field}&page=${products.number-1<=0?0:page.number-1}">Previous</a></li>
					<c:forEach var="p" begin="0" end="${products.totalPages-1}">
						<li class="page-item ${p==products.number?'active':''}"><a
							class="page-link" href="?page=${p}&field=${field}">${p+1}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link"
						href="?page=${products.number+1>products.totalPages-1?products.totalPages-1:products.number+1}&field=${field}">Next</a></li>
				</ul>
			</nav>
		</c:if>
	</div>


</div>
</div>