package org.Project.service;

import java.util.List;

import org.Project.entities.Category;
import org.Project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService 
{
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public Category updateCategory(Category category)
	{
		return categoryRepository.save(category);
	}
	
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}
	

}
