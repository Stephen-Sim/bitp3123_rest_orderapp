package my.edu.utem.ftmk.dad.restorderapp.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import my.edu.utem.ftmk.dad.restorderapp.models.ProductType;
import my.edu.utem.ftmk.dad.restorderapp.repos.ProductTypeRepository;

@RestController
@RequestMapping("api/producttypes")
public class ProductTypeController {
	@Autowired
	private ProductTypeRepository repo;
	
	public ProductTypeController(ProductTypeRepository repo)
	{
		this.repo = repo;
	}
	
	@GetMapping
	public List<ProductType> getProductTypes()
	{
		return repo.findAll();
	}
	
	@GetMapping("{productTypeId}")
	public ProductType getProductTypeById(@PathVariable long productTypeId)
	{
		return repo.findById(productTypeId).get();
	}
	
	@PostMapping()
	public ProductType storeProductType(@RequestBody ProductType productType)
	{
		return repo.save(productType);
	}
	
	@PutMapping("{productTypeId}")
	public ProductType updateProductType(@PathVariable long productTypeId, @RequestBody ProductType orderType)
	{
		try
		{
			var pt = repo.findById(productTypeId).get();
			pt.setName(orderType.getName());
			
			return repo.save(pt);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("{productTypeId}")
	public ResponseEntity<HttpStatus> deleteProductType(@PathVariable long productTypeId) 
	{
		repo.deleteById(productTypeId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
