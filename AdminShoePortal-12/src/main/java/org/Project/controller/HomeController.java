package org.Project.controller;

import java.util.Optional;

import org.Project.GlobalData.GlobalData;
import org.Project.entities.Product;
import org.Project.service.CategoryService;
import org.Project.service.ProductService;
import org.Project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
	private CategoryService categoryService;
	private ProductService productService;
	@SuppressWarnings("unused")
	private UserService userService;

	public HomeController(CategoryService categoryService, ProductService productService, UserService userService) {
		super();
		this.categoryService = categoryService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping({"/home","/"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "welcome";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable long id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable long id) {
		Optional<Product> product=productService.getProductById(id);
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("product", product.get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}

}
