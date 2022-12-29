<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-wrapper" class="d-flex flex-column">
  <div class="content">
    <!-- Begin Page Content -->
    <div class="container-fluid">
      <!-- Page Heading -->
      <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <a
          href="#"
          class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
          ><i class="fas fa-download fa-sm text-white-50"></i> Generate
          Report</a
        >
      </div>

      <!-- Content Row -->
      <div class="row">
        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <a href="/admin/today-statistics">
          <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
              <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                  <div
                    class="text-xs font-weight-bold text-primary text-uppercase mb-1"
                  >
                    Sales (Monthly)
                  </div>
                  <div class="h5 mb-0 font-weight-bold text-gray-800 sale-monthly">
                  
                  </div>
                 
                </div>
                <div class="col-auto">
                  <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div>


 <!-- Earnings (Monthly) Card Example -->
 <div class="col-xl-3 col-md-6 mb-4">
    <a href="/admin/today-statistics">
  <div class="card border-left-info shadow h-100 py-2">
    <div class="card-body">
      <div class="row no-gutters align-items-center">
        <div class="col mr-2">
          <div
            class="text-xs font-weight-bold text-info text-uppercase mb-1"
          >
            Profit (Monthly)
          </div>
          <div class="row no-gutters align-items-center">
            <div class="col-auto">
              <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800 profit-monthly">
                
              </div>
            </div>
            
          </div>
        </div>
        <div class="col-auto">
          <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
        </div>
      </div>
    </div>
  </div>
  </a>
</div>

        <!-- Earnings (Monthly) Card Example -->

        <div class="col-xl-3 col-md-6 mb-4">
          <a href="/admin/users">
            <div class="card border-left-success shadow h-100 py-2">
              <div class="card-body">
                <div class="row no-gutters align-items-center">
                  <div class="col mr-2">
                    <div
                      class="text-xs font-weight-bold text-success text-uppercase mb-1"
                    >
                      User (Annual)
                    </div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                      ${count_user}
                    </div>
                  </div>
                  <div class="col-auto">
                    <i class="fas fa-user fa-2x text-gray-300"></i>
                  </div>
                </div>
              </div>
            </div>
          </a>
        </div>

       

        <!-- Pending Requests Card Example -->

        <div class="col-xl-3 col-md-6 mb-4">
            <a href="/admin/filed-orders?orderStatus=0">
          <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
              <div class="row no-gutters align-items-center">
               
                  <div class="col mr-2">
                    <div
                      class="text-xs font-weight-bold text-warning text-uppercase mb-1"
                    >
                      New Cart Requests Confirm
                    </div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                      ${new_order}
                    </div>
                  </div>
                  <div class="col-auto">
                    <i class="fas fa-shopping-cart fa-2x text-gray-300"></i>
                  </div>
               
              </div>
            </div>
          </div>
        </div>
    </a>
      </div>

      <!-- Content Row -->

      <div class="row">
        <!-- Area Chart -->
        <div class=" col-md-12">
          <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            
            <!-- Card Body -->
            <div class="card-body">
              <div class="chart-area">
                <div id="myAreaChart" style="height: 250px;text-align:Center;font-weight: bold;">
                 <span >Revenues in month</span> 
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Pie Chart -->
        
      </div>
    </div>
  </div>
</div>
