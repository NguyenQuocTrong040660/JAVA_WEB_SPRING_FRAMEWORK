<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">

	<br />
	<div class="container">
		<h4>
			<b>Update Category</b>
		</h4>
		<form class="container" action="/admin/update-category" method="POST">

			<div class="form-group row">
				<label for="name" class="col-md-3 col-form-label"><b>Name
						category </b></label>
				<div class="col-sm-9">
					<input type="hidden" name="id" id="id" value="${category.id}">
					<input type="text" class="form-control mb-2 mr-sm-2 "
						required="required" name="name" placeholder="Enter category"
						id="name" value="${category.name}">
				</div>

			</div>

			<div class="form-group row">
				<div class="col-md-3 "></div>
				<div class="col-sm-9 form-group">
					<button type="submit" class="btn btn-primary">update</button>
				</div>

			</div>


		</form>

	</div>

	<br /> <br />

	<div class="container">
		<h4>
			<b>List Category</b>
		</h4>

		<table class="table table-bordered">
			<thead class="thead-dark">


				<tr>
					<th>ID</th>
					<th>Name category</th>


					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				int i = 1;
				%>
				<c:forEach items="${categories}" var="item">
					<tr style="color: black">
						<td><%=i++%></td>
						<td>${item.name}</td>


						<td><a href="/admin/update-category?id=${item.id}">
								update </a> || <a href="/admin/delete-category?id=${item.id}"
							onclick="return confirm('Do you want delete accessory ${item.name}?')">
								delete </a></td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>

</div>


