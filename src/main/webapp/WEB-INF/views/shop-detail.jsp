
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<img class="product__details__pic__item--large"
							src="/images/${product.imagePath}" alt="${product.imagePath}">
					</div>
					<div class="product__details__pic__slider owl-carousel">
						<c:forEach items="${productImages}" var="item">
							<img data-imgbigurl="/images/${item.path}"
								src="/images/${item.path}" alt="${item.path}">


						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${product.name}</h3>
					<div class="product__details__rating">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star-half-o"></i> <span>(18 reviews)</span>
					</div>
					<div class="product__details__price">$${product.price}</div>
					<p>${product.content}</p>
					
					<a href="#" data-url="/home/add-cart?id=${product.id}"
					  style="border-radius: 45px"
						class="primary-btn add-to-cart">Add To Cart</a> 
						<a
						class="btn btn-danger btn-lg"
						style="border-radius: 45px; width: 150px"
						href="/home/checkout?id=${product.id}">Purchase</a>



					<ul>
						<li><b>Availability</b> <span>In Stock</span></li>
						<li><b>Shipping</b> <span>01 day shipping. <samp>Free
									pickup today</samp></span></li>
						<li><b>Weight</b> <span>10 kg</span></li>

					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">PRODUCT
								INFORMATION</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-2" role="tab" aria-selected="false">PRODUCT
								DESCRIPTION</a></li>

					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc">

								<p>
									<b>Producer</b> GIANT (KUNSHAN) CO., LTD. No. 889.HONGHU RD,
									KUNSHAN ECO&TEC DEVELOPMENT ZONE, JIANGSU, China
								</p>
								<p>
									<b>Exporter</b> GIANT (KUNSHAN) CO., LTD. No. 889.HONGHU RD,
									KUNSHAN ECO&TEC DEVELOPMENT ZONE, JIANGSU, China (+86) 512
									50179576
								</p>

							</div>
						</div>
						<div class="tab-pane" id="tabs-2" role="tabpanel">
							<div class="product__details__tab__desc">

								<p>The aluminum frame of the ${product.name} is quite
									slender but equally durable with many advantages such as
									lightness, high load resistance and good impact resistance. In
									addition, the chassis is also covered with a gray electrostatic
									powder coating, which effectively resists corrosion and rust
									under the negative influence of hot and humid weather. Another
									plus of this model is the spacious frame that allows the user
									to equip many hanging bags or carry a lot of luggage</p>

								<p>Braking is considered an important element of street
									bikes when it helps the vehicle to slow down when encountering
									unexpected dangerous situations on the road. This
									${product.name} model uses Linear Pull gum brakes with
									high-precision Shimano ST-EF41 handbrake and light squeeze
									force, providing safety when moving. In addition, with this
									type of rim brake, maintenance and replacement is quite easy
									when the brake part is in an easy-to-remove position.</p>
								<p>the ${product.name} has many outstanding advantages such
									as 700c wheels that glide smoothly, grip the road well, Shimano
									transmission helps to optimize speed, ... This model will be
									the choice. Great choice for those who love long-distance trips
									or regularly exercise on the street or mountain or fitness.</p>

							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<section class="related-product">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related__product__title">
					<h2>Related Product</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${relateProducts}" var="items">
				<div class="col-lg-3 col-md-4 col-sm-6 showborder">
					<div class="product__item ">
						<div class="product__item__pic set-bg"
							data-setbg="/images/${items.imagePath}">
							<ul class="product__item__pic__hover">
								<li><a href="/home/love?id=${items.id}"><i
										class="fa fa-heart"></i></a></li>
								<li><a href="/home/shop-detail?id=${items.id}"><i
										class="fa fa-retweet"></i></a></li>
								<li class="add-to-cart" data-url="/home/add-cart?id=${items.id}"><a
									href="#"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="product__item__text ">
							<h6>
								<a href="#">${items.name}</a>
							</h6>
							<h5>$${items.price}</h5>
							<h5>
								<a class="btn btn-danger btn-lg "
								style="border-radius: 45px; width: 150px"
									href="/home/checkout?id=${items.id}"> Purchase </a>
							</h5>

						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
</section>
