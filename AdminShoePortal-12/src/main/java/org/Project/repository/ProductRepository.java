package org.Project.repository;

import java.util.List;

import org.Project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long>{

	List<Product> findAllByCategory_Id(long id);

	

}
