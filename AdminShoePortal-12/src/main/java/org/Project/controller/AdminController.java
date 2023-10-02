package org.Project.controller;

import java.io.IOException;

import org.Project.entities.Category;
import org.Project.entities.Product;
import org.Project.service.CategoryService;
import org.Project.service.ProductService;
import org.Project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImage";

	private ProductService productService;
	private UserService userService;
	private CategoryService categoryService;

	public AdminController(ProductService productService, UserService userService, CategoryService categoryService) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.categoryService = categoryService;
	}

	@GetMapping("/admin")
	public String showHome() {
		return "index";

	}

	// categoryOperation

	@GetMapping("/admin/categories")
	public String getCategory(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/new")
	public String createCategotyForm(Model model) {
		model.addAttribute("category", new Category());
		return "createCategory";
	}

	@PostMapping("/admin/categories")
	public String saveCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/edit/{id}")
	public String editCategoryForm(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.getCategoryById(id));
		return "editCategory";
	}

	@PostMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable("id") Long id, @ModelAttribute("category") Category category,
			Model model) {
		Category ex1 = categoryService.getCategoryById(id);
		ex1.setId(id);
		ex1.setName(ex1.getName());
		categoryService.updateCategory(ex1);
		return "redirect:/admin/categories";

	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	// Product Operation
	@GetMapping("/admin/products")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/new")
	public String createProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "createProduct";
	}

	@PostMapping("/admin/products")
	public String saveProduct(@ModelAttribute("product") Product product,
			@RequestParam("productImage") MultipartFile file, Model model) throws IOException {
		Product products = new Product();
		products.setId(product.getId());
		products.setName(product.getName());
		products.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
		products.setPrice(product.getPrice());
		String imageUID = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

		if (imageUID.contains("..")) {
			System.out.print(" Not valid available");
		}
		try {
			// products.setImageName(Base64.getEncoder().encodeToString(file.getBytes()));
			products.setImageName(imageUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("product",productService.saveProduct(products));
		//productService.saveProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/products/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("categories", categoryService.getAllCategory());
		return "editProduct";
	}

	@PostMapping("/admin/products/update/{id}")
	public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product, Model model) {
		Product ex = productService.getProductById(id).get();
		ex.setId(id);
		ex.setName(product.getName());
		ex.setCategory(product.getCategory());
		ex.setPrice(product.getPrice());
		ex.setImageName(product.getImageName());
		productService.updateProduct(ex);
		return "redirect:/admin/products";

	}

	@GetMapping("/admin/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	// user Data
	@GetMapping("/admin/users")
	public String users(Model model) {
		model.addAttribute("users", userService.FindAllUser());
		return "users";
	}

	@GetMapping("/admin/users/delete/{id}")
	public String deleteUSer(@PathVariable Long id) {
		userService.deteteUserById(id);
		return "redirect:/admin/users";
	}
}
