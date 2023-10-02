package org.Project.service;

import java.util.List;
import java.util.Optional;

import org.Project.entities.Product;
import org.Project.repository.CategoryRepository;
import org.Project.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
	}

	// getAllProduct
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}
 	// public Product addProduct(ProductModel )

//  //Add New Product
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	// Get Product By ID
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	// upadte Product data
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	// delete Product By id
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> getAllProductByCategoryId(long id)
	{
		return productRepository.findAllByCategory_Id(id);
		
	}

}
