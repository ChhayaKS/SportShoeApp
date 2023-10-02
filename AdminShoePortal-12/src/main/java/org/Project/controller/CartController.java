package org.Project.controller;

import java.time.LocalDate;

import org.Project.GlobalData.GlobalData;
import org.Project.entities.Product;
import org.Project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
	
	private ProductService productService;
	
	public CartController(ProductService productService) {
		super();
		this.productService = productService;
	}


	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable Long id)
	{
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	
	@GetMapping("/cart")
	public String cartGet(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(org.Project.entities.Product::getPrice).sum());
		return "checkout";
	}
	
	// payNow 
	@PostMapping("/payNow")
	public String payNow(Model model) {
		
		model.addAttribute("result","congratulations..!!,your order is now booked");
		LocalDate localDate = LocalDate.now();
		model.addAttribute("localDate",localDate);
		model.addAttribute("parameters", GlobalData.cart);
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "orderPlaced";
		
		
	}
	
	 

}
