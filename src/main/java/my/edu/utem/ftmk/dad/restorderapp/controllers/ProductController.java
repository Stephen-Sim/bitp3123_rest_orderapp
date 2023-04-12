package my.edu.utem.ftmk.dad.restorderapp.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import my.edu.utem.ftmk.dad.restorderapp.models.OrderType;
import my.edu.utem.ftmk.dad.restorderapp.models.Product;
import my.edu.utem.ftmk.dad.restorderapp.repos.ProductRepository;

@RestController
@RequestMapping("api/products")
public class ProductController {
	@Autowired
	private ProductRepository repo;	
	
	public ProductController(ProductRepository repo)
	{
		this.repo = repo;
	}
	
	@GetMapping
	public List<Product> getProduct()
	{
		return repo.findAll();
	}

	
	@GetMapping("{productid}")
	public Product getProductById(@PathVariable long productid)
	{
		return repo.findById(productid).get();
	}
	
	@PostMapping()
	public Product storeProduct(@RequestBody Product product)
	{
		return repo.save(product);
	}
	
	@PutMapping("{productid}")
	public Product updateProduct(@PathVariable long productid, @RequestBody Product product)
	{
		try
		{
			var p = repo.findById(productid).get();
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setProductType(product.getProductType());
			
			return repo.save(p);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("{productid}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long productid) 
	{
		repo.deleteById(productid);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
