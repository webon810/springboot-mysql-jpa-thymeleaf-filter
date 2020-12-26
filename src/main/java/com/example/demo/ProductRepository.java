package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//JPQL 
	/*@Query("select p from Product p where p.name like %?1% "
			+ "or p.brand like %?1% "
			+ "or p.madein like %?1% "
			+ "or concat(p.price, '') like %?1%" )
			*/
	
	// concatenate each field with a whitespace 
	// to avoid undesired results from the string concatenation
	@Query("select p from Product p where concat(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) like %?1%")
	public List<Product> search(String keyword);

}
