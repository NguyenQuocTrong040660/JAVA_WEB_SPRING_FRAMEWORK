<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4>Add Category</h4>
<br />
<form class="container" name="myform" action="#" method="POST">

	<div class="form-group row">
		<label for="name" class="col-md-3 col-form-label"><b>Name
				category </b></label>
		<div class="col-sm-9">
			<input type="text" class="form-control mb-2 mr-sm-2 "
				required="required" name="name" placeholder="Enter category"
				id="name">
		</div>

	</div>

	<div class="row ">
		<div class="form-group">
			<button type="submit" class="btn btn-primary">Save Product</button>
		</div>

	</div>
</form>





<table class="table table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name category</th>


			<th>Action</th>
		</tr>
	</thead>
	<tbody>

		<tr>
			<td>1</td>
			<td>Chickend</td>


			<td>  <a href="#"> update </a> || <a href="#"> delete </a></td>
		</tr>



	</tbody>
</table>


