package com.training.fa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.fa.DTO.response.JsonCode;
import com.training.fa.model.Category;
import com.training.fa.model.Product;
import com.training.fa.repository.ProductRepository;
import com.training.fa.service.CategoryService;
import com.training.fa.service.ProductService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@GetMapping(value = { "", "index" })
	public String home(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page) {

		Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
		int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
		Pageable pageable = PageRequest.of(p, 6, sort);
		model.addAttribute("field", field.orElse("price"));

		Page<Product> products = productRepository.findProducts(pageable);
		model.addAttribute("products", products);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		/*
		 * List<Product> products1 = productService.getAllProduct(); List<Product>
		 * listss =
		 * products1.stream().filter(c->c.getName().equals("Mountain Bikes F1 PhiLip")).
		 * toList(); listss.forEach(a->{ System.out.println(a); });
		 */

		model.addAttribute("body", getBody("home"));
		return "index";
	}

	@GetMapping("/shop-detail")
	public String shopDtail(Model model, @RequestParam("id") Integer id) {

		Product product = productService.getProduct(id);

		List<Product> relateProducts = productRepository.findByCategory(product.getCategory());

		if (product != null) {

			model.addAttribute("relateProducts", relateProducts);
			model.addAttribute("productImages", product.getProductImage());
			model.addAttribute("product", product);
			model.addAttribute("body", getBody("shop-detail"));
			return "index";
		}

		return "redirect:/home";
	}

	@GetMapping("shopping-car")
	public String shoppingCar(Model model) {

		model.addAttribute("body", getBody("shopping-car"));
		return "index";
	}

	// Filter Product By Id
	@GetMapping("/filter-product")
	public String filterProduct(Model model, @RequestParam("id") Integer id,
			@RequestParam("field") Optional<String> field, @RequestParam("page") Optional<Integer> page) {

		Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
		int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
		Pageable pageable = PageRequest.of(p, 6, sort);
		model.addAttribute("field", field.orElse("price"));

		Page<Product> products = productRepository.findByCategoryId(id, pageable);
		model.addAttribute("products", products);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);

		model.addAttribute("body", getBody("home"));
		return "index";

	}

	@GetMapping("/search-product")
	public String searchProduct(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page, @RequestParam("name") String name) {
		try {
			

			Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
			int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
			Pageable pageable = PageRequest.of(p, 6, sort);
			model.addAttribute("field", field.orElse("price"));

			Page<Product> products = productRepository.findByNameContaining(name, pageable);
			
			int count = products.getSize();
			model.addAttribute("products", products);
			model.addAttribute("count", count);

			List<Category> categories = categoryService.getAllCategory();
			model.addAttribute("categories", categories);
			

			model.addAttribute("body", getBody("home"));
			return "index";
			
		}catch(Exception e) {
			System.out.println(e);
			return "redirect:/home";
			
		}
		
	}

	

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
