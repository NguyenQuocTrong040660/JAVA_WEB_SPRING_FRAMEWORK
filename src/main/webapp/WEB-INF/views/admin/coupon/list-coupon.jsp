<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
  <br />
  <div class="container">
    <h4>
      <b>Add Coupon</b>
    </h4>
    <form class="container" action="/admin/coupons" method="POST">
      <div class="form-group row">
        <label for="name" class="col-md-3 col-form-label"
          ><b>Use value </b></label
        >
        <div class="col-sm-9">
          <input
            type="text"
            class="form-control mb-2 mr-sm-2"
            required="required"
            name="useValue"
            placeholder="Enter 10% or 20%"
            id="name"
          />
        </div>
      </div>

      <div class="form-group row">
        <label for="name" class="col-md-3 col-form-label"
          ><b>Condition </b></label
        >
        <div class="form-row col-sm-9">
          <div class="col-md-9">
            <input
              type="text"
              class="form-control mb-2 mr-sm-2"
              required="required"
              name="desCription"
              placeholder="Enter Condition"
              id="name"
            />
          </div>

          <div class="col-md-3">
            <input
              type="text"
              class="form-control mb-1 mr-sm-1"
              required="required"
              name="total"
              placeholder="Total : 300$ - 500$ - 800$"
              id="name"
            />
          </div>
        </div>
      </div>

      <div class="form-group row">
        <div class="col-md-3"></div>
        <div class="col-sm-9 form-group">
          <button
            style="border-radius: 45px; width: 100px"
            type="submit"
            class="btn btn-primary"
          >
            Save
          </button>
        </div>
      </div>
    </form>
  </div>

  <br />
  <br />

  <div class="container">
    <h4>
      <b>List Coupon</b>
    </h4>

    <table class="table table-bordered">
      <thead style="background-color: #385ece; color: white">
        <tr>
          <th>No</th>
          <th>Use value %</th>

          <th>Condition</th>

          <th>Total Order</th>

          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <% int i = 1; %>
        <c:forEach items="${coupons}" var="item">
          <tr style="color: black">
            <td><%=i++%></td>
            <td>${item.useValue}</td>
            <td>${item.desCription}</td>
            <td>${item.total}</td>

            <td>
              <a
                href="/admin/delete-coupon?id=${item.id}"
                style="border-radius: 45px; width: 100px"
                onclick="return confirm('Do you want delete coupon ${item.useValue}?')"
                class="btn btn-danger"
              >
                delete
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
