package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
//@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	/* public List<Product> listAll(){
		return repo.findAll();
	} */
	
	public List<Product> listAll(String keyword){
		
		if (keyword != null) {
			return repo.search(keyword);
		}
	    
		//if keyowrd is null invoke findAll() method
		return repo.findAll();
	}
	
	public Product get(long id) {
		
		return repo.findById(id).get();
		
	}
	
    public void save(Product product) {
    	
    	repo.save(product);
		
	}
    
	public void delete(long id) {
		
		repo.deleteById(id);
		
	}

}
