<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="container">
		<h4>
			<b>Add product</b>
		</h4>
		<br />
		<form class="container" name="myform" action="/admin/update-product"
			method="POST" enctype="multipart/form-data">

			<div class="form-group row">
				<label for="name" class="col-md-3 col-form-label"><b>Name
						product</b></label>
				<div class="col-sm-9">
					<input type="hidden" name="id" value="${product.id}"> <input
						type="text" class="form-control mb-2 mr-sm-2 " required="required"
						name="name" placeholder="Enter name product" id="name"
						value="${product.name}">
				</div>
				<p id="message"></p>
			</div>



			<div class="form-group row">
				<label for="price" class="col-md-3 col-form-label"><b>Price
				</b></label>
				<div class="col-sm-9">
					<input required="required" type="text" class="form-control"
						placeholder="Enter 10.000.000" id="price" name="price"
						value="${product.price}">
				</div>

			</div>



			<div class="form-group row">
				<label for="imagePath" class="col-md-3 col-form-label"><b>Image
						product</b></label>
				<div class="col-md-9">
					<input type="file" class="form-control mb-2 mr-sm-2 " name="file"
						id="imagePath">
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-9">
					<image src="/images/${product.imagePath}" width="100" height="100">
				</div>

			</div>

			<div class="form-group row">

				<label for="imagePath" class="col-md-3 col-form-label"><b>Image
						Detail product</b></label>
				<div class="col-sm-9">
					<input type="file" multiple="multiple"
						class="form-control mb-2 mr-sm-2 " name="files" id="imagePath">
				</div>
				<div class="col-md-3"></div>

				<div class="col-md-9">
					<c:forEach items="${imagesDetail}" var="item">

						<image src="/images/${item.path}" width="100" height="100">
					</c:forEach>
				</div>

			</div>



			<div class="form-group row">
				<label for="category" class="col-md-3 col-form-label"><b>Category</b></label>
				<div class="col-sm-9">


					<select id="category" class="form-control" name="category">
						<c:forEach items="${categories}" var="category">

							<option value="${category.id}"
								${category.id == product.category.id? 'selected' : ''}>
								${category.name}</option>

						</c:forEach>

					</select>
				</div>

			</div>


			<div class="form-group row">
				<label for="content" class="col-md-3 col-form-label"><b>Content</b></label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control mb-2 mr-sm-2 "
						name="content" placeholder="Enter content" id="content" rows="4">${product.content}
							</textarea>
				</div>

			</div>




			<div class="row ">
				<div class="col-md-3"></div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Save Product</button>
				</div>


			</div>
		</form>
	</div>

</div>


