<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>SB Admin 2 - Home</title>

    <!-- Custom fonts for this template-->
    <link
      href="/vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet" />

    <link
      rel="stylesheet"
      href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css"
    />
    <link
      rel="stylesheet"
      href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"
    />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

    <style>
      #page-top {
        color: black;
      }
      /* .ui-datepicker {
        background: white none;
        border: 1px solid black;
        color: black;
      } */
    </style>
  </head>

  <body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Sidebar -->
      <ul
        class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
        id="accordionSidebar"
      >
        <jsp:include page="layout_admin/slider.jsp" />
      </ul>
      <!-- End of Sidebar -->

      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
          <!-- Topbar -->
          <nav
            class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
          >
            <!-- Sidebar Toggle (Topbar) -->
            <button
              id="sidebarToggleTop"
              class="btn btn-link d-md-none rounded-circle mr-3"
            >
              <i class="fa fa-bars"></i>
            </button>

            <!-- Topbar Search -->
            <form
              class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
            >
              <div class="input-group">
                <input
                  type="text"
                  class="form-control bg-light border-0 small"
                  placeholder="Search for..."
                  aria-label="Search"
                  aria-describedby="basic-addon2"
                />
                <div class="input-group-append">
                  <button class="btn btn-primary" type="button">
                    <i class="fas fa-search fa-sm"></i>
                  </button>
                </div>
              </div>
            </form>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
              <li class="nav-item dropdown no-arrow mx-1">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="alertsDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <i class="fas fa-bell fa-fw"></i>
                  <!-- Counter - Alerts -->
                  <span class="badge badge-danger badge-counter"
                    >${new_order}</span
                  >
                </a>
                <!-- Dropdown - Alerts -->
                <div
                  class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="alertsDropdown"
                >
                  <h6 class="dropdown-header">Alerts Center</h6>
                  <c:choose>
                    <c:when test="${new_order > '0'}">
                      <c:forEach items="${news_orders}" var="item">
                        <a
                          class="dropdown-item d-flex align-items-center"
                          href="/admin/detail-order?id=${item.id}"
                        >
                          <div class="mr-3">
                            <div class="icon-circle bg-success">
                              <i class="fas fa-donate text-white"></i>
                            </div>
                          </div>
                          <div>
                            <div class="small text-gray-500">
                              ${item.createTime}
                            </div>
                            Do you confirm new order #BC ${item.id} => Click Me!
                          </div>
                        </a>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <a
                        class="dropdown-item text-center small text-gray-500"
                        href="#"
                        >No Alert</a
                      >
                    </c:otherwise>
                  </c:choose>
                </div>
              </li>

              <div class="topbar-divider d-none d-sm-block"></div>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="userDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small"
                    >${userDetail.username}
                  </span>
                  <img
                    class="img-profile rounded-circle"
                    src="/img/user-profile.jpg"
                  />
                </a>
                <!-- Dropdown - User Information -->
                <div
                  class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="userDropdown"
                >
                  <a
                    class="dropdown-item"
                    href="#"
                    data-toggle="modal"
                    data-target="#profile"
                  >
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                  </a>
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Settings
                  </a>
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                    Activity Log
                  </a>
                  <div class="dropdown-divider"></div>
                  <a
                    class="dropdown-item"
                    href="/logout"
                    data-toggle="modal"
                    data-target="#logoutModal"
                  >
                    <i
                      class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"
                    ></i>
                    Logout
                  </a>
                </div>
              </li>
            </ul>
          </nav>
          <!-- End of Topbar -->
          <!-- Modal -->
          <div
            class="modal fade"
            id="profile"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
          >
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h4
                    class="modal-title"
                    id="exampleModalLabel"
                    style="color: blue"
                  >
                    Profile
                  </h4>
                  <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <form action="/update-password" method="post">
                  <div class="modal-body">
                    <lable><b>UserName:</b></lable>
                    <input
                      type="text"
                      class="form-control"
                      value="${userDetail.username}"
                      disabled
                    /><br />
                    <lable><b>Email:</b></lable>
                    <input
                      type="text"
                      class="form-control"
                      value="${userDetail.email}"
                      disabled
                    /><br />
                    <lable><b>Role:</b></lable>
                    <input
                      type="text"
                      class="form-control"
                      value="${userDetail.role}"
                      disabled
                    /><br />
                    <lable><b>Password:</b></lable>
                    <input
                      type="password"
                      name="password"
                      class="form-control"
                      value="${userDetail.password}"
                    /><br />
                  </div>
                  <div class="modal-footer">
                    <input
                      type="hidden"
                      name="id"
                      class="form-control"
                      value="${userDetail.id}"
                    /><br />
                    <button type="submit" class="btn btn-primary">
                      Save changes
                    </button>
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-dismiss="modal"
                    >
                      Close
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <!-- End Modal -->
          <!-- Begin Page Content -->
          <div class="container-fluid">
            <!-- Content Row -->
            <div class="row">
              <jsp:include page="${body}" />
            </div>
          </div>
          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <jsp:include page="layout_admin/footer.jsp" />
        <!-- End of Footer -->
      </div>
      <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div
      class="modal fade"
      id="logoutModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button
              class="close"
              type="button"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">
            Select "Logout" below if you are ready to end your current session.
          </div>
          <div class="modal-footer">
            <button
              class="btn btn-secondary"
              type="button"
              data-dismiss="modal"
            >
              Cancel
            </button>
            <a class="btn btn-primary" href="/logout">Logout</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

    <script>
      $(function () {
        $("#datepicker").datepicker({ dateFormat: "dd-mm-yy" });
        $("#datepicker2").datepicker({ dateFormat: "dd-mm-yy" });
      });
    </script>

    <script type="text/javascript">
      $("#datepicker2").ready(function () {
        var from_date = document.getElementById("datepicker").value;

        var to_date = document.getElementById("datepicker2").value;

        $.ajax({
          url:
            "/admin/statistics?from_date=" + from_date + "&to_date=" + to_date,
          method: "GET",
          success: function (data) {
            chart.setData(data);

            $.each(data, function (key, value) {
              $("#search-table-revenues").append(
                "<tr>\
                                    <td>" +
                  "#BC" +
                  value.id +
                  "</td>\
                                    <td>" +
                  value.orderDate +
                  "</td>\
                                    <td>" +
                  value.totalOrder +
                  "</td>\
                                    <td>" +
                  value.sales +
                  "</td>\
                                    <td>" +
                  value.profit +
                  "</td>\
                                    </tr>"
              );
            });
          },
        });
      });
    </script>

    <script type="text/javascript">
      $("#datepicker2").change(function () {
        // var _token = $('input[name="_token"]').val();

        var from_date = document.getElementById("datepicker").value;

        var to_date = document.getElementById("datepicker2").value;

        /// console.log(from_date+"==="+to_date);
        $("#dataTable tbody").empty();
        $.ajax({
          url:
            "/admin/statistics?from_date=" + from_date + "&to_date=" + to_date,
          method: "GET",
          success: function (data) {
            chart.setData(data);

            $.each(data, function (key, value) {
              $("#search-table-revenues").append(
                "<tr>\
                                    <td>" +
                  "#BC" +
                  value.id +
                  "</td>\
                                    <td>" +
                  value.orderDate +
                  "</td>\
                                    <td>" +
                  value.totalOrder +
                  "</td>\
                                    <td>" +
                  value.sales +
                  "</td>\
                                    <td>" +
                  value.profit +
                  "</td>\
                                    </tr>"
              );
            });
          },
        });
      });
    </script>

    <script>
      var chart = new Morris.Bar({
        // ID of the element in which to draw the chart.
        element: "myfirstchart",
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.

        // The name of the data record attribute that contains x-values.
        xkey: "orderDate",
        // A list of names of data record attributes that contain y-values.
        ykeys: ["totalOrder", "sales", "profit"],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        labels: ["totalOrder", "sales", "profit"],
      });
    </script>

    <script>
      var chart_myAreaChart = new Morris.Area({
        // ID of the element in which to draw the chart.
        element: "myAreaChart",

        xkey: "orderDate",
        // A list of names of data record attributes that contain y-values.
        ykeys: ["totalOrder", "sales"],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        labels: ["totalOrder", "sales"],
        parseTime: false,
        xLabels: "year",
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["gray", "blue"],
        preUnits: "$",
      });
    </script>

    <script>
      //set total revenues in monthLy
      window.onload = (event) => {
        $.ajax({
          url: "/admin/chart_myAreaChart",
          method: "GET",
          success: function (data) {
            chart_myAreaChart.setData(data);
          },
        });

        $.ajax({
          url: "/admin/total-revenues",
          method: "GET",
          success: function (data) {
            $(".sale-monthly").html(data);
          },
        });
      };
      $.ajax({
        url: "/admin/total-profit",
        method: "GET",
        success: function (data) {
          $(".profit-monthly").html(data);
        },
      });
    </script>
  </body>
</html>
