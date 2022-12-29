
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Product Section Begin -->
<section class="product spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-5">
				<div class="sidebar">
					<div class="sidebar__item">
						<h4>Product Category</h4>
						
						<ul>
							<c:forEach items="${categories}" var="item">
								<li class="category-hover"><a style="color:white" href="/home/filter-product?id=${item.id}">${item.name}</a></li>

							</c:forEach>
						</ul>
					</div>

					<div class="sidebar__item sidebar__item__color--option">
						<h4>Colors</h4>
						<div class="sidebar__item__color sidebar__item__color--white">
							<label for="white"> White <input type="radio" id="white">
							</label>
						</div>
						<div class="sidebar__item__color sidebar__item__color--gray">
							<label for="gray"> Gray <input type="radio" id="gray">
							</label>
						</div>
						<div class="sidebar__item__color sidebar__item__color--red">
							<label for="red"> Red <input type="radio" id="red">
							</label>
						</div>
						<div class="sidebar__item__color sidebar__item__color--black">
							<label for="black"> Black <input type="radio" id="black">
							</label>
						</div>
						<div class="sidebar__item__color sidebar__item__color--blue">
							<label for="blue"> Blue <input type="radio" id="blue">
							</label>
						</div>
						<div class="sidebar__item__color sidebar__item__color--green">
							<label for="green"> Green <input type="radio" id="green">
							</label>
						</div>
					</div>


				</div>
			</div>
			<div class="col-lg-9 col-md-7">

				<div class="filter__item">
					<div class="row">
						<div class="col-lg-4 col-md-5">
							<div class="filter__sort">
								<span>Sort By</span> <select>
									<option value="0">Default</option>
									<option value="0">Default</option>
								</select>
							</div>
						</div>
						
						<div class="col-lg-4 col-md-3">
							<div class="filter__option">
								<span class="icon_grid-2x2"></span> <span class="icon_ul"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<c:forEach items="${products.content}" var="item">
					
						<div class="col-lg-4 col-md-6 col-sm-6 showborder" >
							<div class="product__item showborder1" >
							
								<div class="product__item__pic set-bg"
									data-setbg="/images/${item.imagePath}">
									<ul class="product__item__pic__hover">
										<li><a href="/home/love?id=${item.id}"><i class="fa fa-heart"></i></a></li>
										<li><a href="/home/shop-detail?id=${item.id}"><i class="fa fa-retweet"></i></a></li>
										<li class="add-to-cart" data-url="/home/add-cart?id=${item.id}" >
										<a href="#"
										><i class="fa fa-shopping-cart"></i></a></li>
									</ul>
									
								</div>
								<div class="product__item__text">
									<h6>
										<a href="#">${item.name}</a>
									</h6>
									<h5>${item.price}$</h5>
									<h5><a style="border-radius: 45px;width: 150px;"
										class="btn btn-danger"  href="/home/purechuse?id=${item.id}">Purchase</a></h5>
									
								</div>
								
							</div>
						</div>
						
						
					</c:forEach>

				</div>
				<br>
				<c:if test="${products.content.size()>0}">
					<nav aria-label="Page navigation example d-flex">
						<ul class="pagination justify-content-left">
							<li class="page-item"><a class="page-link"
								href="?field=${field}&page=${products.number-1<=0?0:page.number-1}">previous</a></li>
							<c:forEach var="p" begin="0" end="${products.totalPages-1}">
								<li class="page-item ${p==products.number?'active':''}"><a
									class="page-link" href="?page=${p}&field=${field}">${p+1}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link"
								href="?page=${products.number+1>products.totalPages-1?products.totalPages-1:products.number+1}&field=${field}">next</a></li>
						</ul>
					</nav>
				</c:if>
			</div>
		</div>
</section>
<!-- Product Section End -->