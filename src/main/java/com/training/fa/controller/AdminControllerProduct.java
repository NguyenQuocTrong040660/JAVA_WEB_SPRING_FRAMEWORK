package com.training.fa.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.activation.FileDataSource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.fa.model.Category;
import com.training.fa.model.OrderDetail;
import com.training.fa.model.Product;
import com.training.fa.model.ProductImage;
import com.training.fa.repository.ProductRepository;
import com.training.fa.service.CategoryService;
import com.training.fa.service.OrderDetailService;
import com.training.fa.service.ProductImageService;
import com.training.fa.service.ProductService;
import com.training.fa.uitils.FileUtill;

@RequestMapping("/admin")
@CrossOrigin("*")
@Controller
public class AdminControllerProduct {
	// private static final Path CURRENT_FOLDER =
	// Paths.get(System.getProperty("user.dir"));
	Path currentDir = Paths.get("src/main/resources/"); // currentDir = "."
	private Path CURRENT_FOLDER = currentDir.toAbsolutePath();

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductImageService productImageService;
    
	@Autowired
	private  OrderDetailService  orderDetailService;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/add-product")
	public String addProduct(Model model) {
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		model.addAttribute("body", getBody("./product/add-product"));
		return "admin/index";
	}
	

	@GetMapping("/products")
	public String products(Model model, @RequestParam("field") Optional<String> field,
			@RequestParam("page") Optional<Integer> page) {

		Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
		int p = page.orElse(0) < 0 ? 0 : page.orElse(0);
		Pageable pageable = PageRequest.of(p, 5, sort);
		model.addAttribute("field", field.orElse("price"));

		Page<Product> products = productRepository.findProducts(pageable);
		model.addAttribute("products", products);

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);

		model.addAttribute("body", getBody("./product/list-product"));
		return "admin/index";
	}

	@Autowired
	private FileUtill fileUtill;

	@PostMapping("/products")
	public String saveProduct(Model model, HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("price") Integer price, @RequestParam("content") String content,
			@RequestParam("category_id") Integer category_id, @RequestParam("file") MultipartFile image,
			@RequestParam("files") MultipartFile[] files) throws IOException {

		String path = fileUtill.saveFile(CURRENT_FOLDER, image);

		Product product = new Product();
		Category category = categoryService.getCategory(category_id);

		product.setName(name);
		product.setPrice(price);
		product.setContent(content);
		product.setCategory(category);

		product.setImagePath(path);

		Product productSave = productService.saveProduct(product);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);

		List<String> photos = new ArrayList<String>();
		for (MultipartFile file : files) {
			String paths = fileUtill.saveFile(CURRENT_FOLDER, file);
			photos.add(paths);
		}

		for (String photo : photos) {
			ProductImage productImage = new ProductImage();
			productImage.setPath(photo);
			productImage.setProduct(productSave);
			productImageService.save(productImage);

		}

		model.addAttribute("body", getBody("./product/list-product"));

		return "redirect:/admin/products";
	}

	@GetMapping("/delete-product")
	public String deleteProduct(Model model,RedirectAttributes redirectAttributes, @RequestParam("id") Integer id) {
     
		
		List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetail();
		if(orderDetails.size()>0) {
			for(OrderDetail item:orderDetails) {
				if(item.getProduct().getId()== id) {
					redirectAttributes.addFlashAttribute("message","Product exitst in Order!");
					return "redirect:/admin/products";
				}
			}
		}
		productService.deleteProduct(id);
		redirectAttributes.addFlashAttribute("message","Delete success!");
		return "redirect:/admin/products";

	}

	@GetMapping("/update-product")
	public String updateProduct(Model model, @RequestParam("id") Integer id) {

		Product product = productService.getProduct(id);

		model.addAttribute("product", product);

		// List<ProductImage> productImages =
		// productImageService.getImageByProduct(product);

		model.addAttribute("imagesDetail", product.getProductImage());

		

		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories);

		model.addAttribute("body", getBody("./product/update-product"));
		return "admin/index";

	}

	@PostMapping("/update-product")
	public String updateSaveProduct(Model model, @RequestParam("id") Integer id,
			@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile image,
			@RequestParam("files") MultipartFile[] files

	) throws IOException {

		Product productbyId = productService.getProduct(id);

		if (!image.isEmpty()) {
			String path = fileUtill.saveFile(CURRENT_FOLDER, image);

			product.setImagePath(path);
		} else {
			product.setImagePath(productbyId.getImagePath());
		}

		
		if (null != files && files.length > 0) {
			List<String> photos = new ArrayList<String>();
			for (MultipartFile file : files) {
				String paths = fileUtill.saveFile(CURRENT_FOLDER, file);
				if(paths.equals("null")) {
					break;
				}else {
				photos.add(paths);
				}
			}
			if(photos.size()>0) {
			
			List<ProductImage> images = productImageService.getImageByProduct(id);
			
			images.forEach((image2) -> {
				productImageService.deleteImagesDetailByProductID(image2.getId());
			});
			}
			for (String photo : photos) {
				ProductImage productImage = new ProductImage();
				productImage.setPath(photo);
				productImage.setProduct(product);
				productImageService.save(productImage);

			 }
			
		}
       
		productService.updateProduct(product);

		return "redirect:/admin/products";

	}

	public static String getBody(String body) {
		return body + ".jsp";
	}

}
