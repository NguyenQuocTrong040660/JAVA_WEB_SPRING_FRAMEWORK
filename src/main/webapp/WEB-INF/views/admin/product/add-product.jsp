<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <div class="container">
    <h4>
      <b>Add product</b>
    </h4>
    <br />
    <form
      class="container"
      name="myform"
      action="/admin/products"
      method="POST"
      enctype="multipart/form-data"
    >
      <div class="form-group row">
        <label for="name" class="col-md-3 col-form-label"
          ><b>Name product</b></label
        >
        <div class="col-sm-9">
          <input
            type="text"
            class="form-control mb-2 mr-sm-2"
            required="required"
            name="name"
            placeholder="Enter name product"
            id="name"
          />
        </div>
        <p id="message"></p>
      </div>

      <div class="form-group row">
        <label for="price" class="col-md-3 col-form-label"><b>Price </b></label>
        <div class="col-sm-9">
          <input
            required="required"
            type="text"
            class="form-control"
            placeholder="Enter 10.000.000"
            id="price"
            name="price"
          />
        </div>
      </div>

      <div class="form-group row">
        <label for="imagePath" class="col-md-3 col-form-label"
          ><b>Image product</b></label
        >
        <div class="col-sm-9">
          <input
            type="file"
            class="form-control mb-2 mr-sm-2"
            required="required"
            name="file"
            id="imagePath"
          />
        </div>
      </div>

      <div class="form-group row">
        <label for="imagePath" class="col-md-3 col-form-label"
          ><b>Image Detail product</b></label
        >
        <div class="col-sm-9">
          <input
            type="file"
            multiple="multiple"
            class="form-control mb-2 mr-sm-2"
            required="required"
            name="files"
            id="imagePath"
          />
        </div>
      </div>

      <div class="form-group row">
        <label for="category" class="col-md-3 col-form-label"
          ><b>Category</b></label
        >
        <div class="col-sm-9">
          <select id="category" class="form-control" name="category_id">
            <c:forEach items="${categories}" var="category">
              <option value="${category.id}">${category.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>

      <div class="form-group row">
        <label for="content" class="col-md-3 col-form-label"
          ><b>Content</b></label
        >
        <div class="col-sm-9">
          <textarea
            type="text"
            class="form-control mb-2 mr-sm-2"
            required="required"
            name="content"
            placeholder="Enter content"
            id="content"
            rows="4"
          >
          </textarea>
        </div>
      </div>

      <div class="row">
        <div class="col-md-3"></div>
        <div class="form-group">
          <button
            type="submit"
            style="border-radius: 45px; width: 150px"
            class="btn btn-primary"
          >
            Save Product
          </button>
        </div>
      </div>
    </form>
  </div>
</div>
