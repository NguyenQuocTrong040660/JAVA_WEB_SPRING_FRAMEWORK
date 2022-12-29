<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
	<div class="content">

		<div class="container-fuild">
			<!-- Page Heading -->


			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">List User</h6>
					<p style="color:red">${message}</p>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">

							<thead style="background-color: #385ece;color: white">
								<tr>
									<th>STT</th>
									<th>UserName</th>
									<th>Email</th>
									<th>Role</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<%
								int i = 1;
								%>
								<c:forEach items="${users.content}" var="item">
									<tr>
										<td><%=i++%></td>
										<td>${item.username}</td>
										<td>${item.email}</td>
										<c:forEach items="${item.roles}" var="itemRole">
											<td>${itemRole.name}</td>
										</c:forEach>
										<!--  <td><a href="" data-toggle="modal" data-target="#role${item.id}">Role</a></td>-->
										<td><a href="" style="border-radius: 45px;width: 100px;"  data-toggle="modal" class="btn  btn-danger" data-target="#deleteUser${item.id}">Delete</a></td>
									</tr>
									<!-- Modal -->
									<div class="modal fade" id="deleteUser${item.id}"" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											</div>
											<div class="modal-body">
											Do you want to delete "<span id="productName" style="color:blue">${item.username}</span>"?
											</div>
											<div class="modal-footer">
											<a href="/admin/user/delete?id=${item.id}" type="button" class="btn btn-danger">Yes</a>
											<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
											</div>
										</div>
										</div>
									</div>
									<!--end modal-->
									
									<!-- End Modal -->
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<c:if test="${users.content.size()>0}">
				<nav aria-label="Page navigation example d-flex">
					<ul class="pagination ">
						<li class="page-item"><a class="page-link"
							href="?page=${users.number-1<=0?0:page.number-1}">Previous</a></li>
						<c:forEach var="p" begin="0" end="${users.totalPages-1}">
							<li class="page-item ${p==users.number?'active':''}"><a
								class="page-link" href="?page=${p}">${p+1}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="?page=${users.number+1>users.totalPages-1?users.totalPages-1:users.number+1}">Next</a></li>
					</ul>
				</nav>
			</c:if>
		</div>


	</div>
</div>