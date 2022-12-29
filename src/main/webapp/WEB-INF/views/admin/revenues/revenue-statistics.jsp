<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
  <div class="content">
    <div class="container-fuild">
      <form
        action="${pageContext.request.contextPath }/admin/find-statistics"
        method="GET"
      >
        <p>
          From Date
          <input
            type="text"
            class="form-controll"
            name="from_date"
            id="datepicker"
            value="${startDate}"
          />
          To Date:
          <input
            type="text"
            class="form-controll"
            name="to_date"
            id="datepicker2"
            value="${endDate}"
          />
        </p>
      </form>
    </div>
    <div>
      <div id="myfirstchart" style="height: 250px"></div>
    </div>
    <label><strong>Report Table</strong></label
    ><br />
    <div id="reportTable" style="height: 250px">
      <div class="card shadow mb-4">
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable">
              <thead style="background-color: #385ece; color: white">
                <tr>
                  <th>Order Code</th>
                  <th>Date</th>
                  <th>Order Total</th>
                  <th>Sales $</th>
                  <th>Profit $</th>
                </tr>
              </thead>
              <tbody id="search-table-revenues"></tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
