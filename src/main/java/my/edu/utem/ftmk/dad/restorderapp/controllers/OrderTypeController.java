package my.edu.utem.ftmk.dad.restorderapp.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import my.edu.utem.ftmk.dad.restorderapp.models.OrderType;
import my.edu.utem.ftmk.dad.restorderapp.repos.OrderTypeRepository;

@RestController
@RequestMapping("api/ordertypes")
public class OrderTypeController{
	@Autowired
	private OrderTypeRepository repo;	
	
	public OrderTypeController(OrderTypeRepository repo)
	{
		this.repo = repo;
	}
	
	@GetMapping
	public List<OrderType> getOrderTypes()
	{
		return repo.findAll();
	}
	
	@GetMapping("{orderTypeId}")
	public OrderType getOrderTypeById(@PathVariable long orderTypeId)
	{
		return repo.findById(orderTypeId).get();
	}
	
	@PostMapping()
	public OrderType storeOrderType(@RequestBody OrderType orderType)
	{
		return repo.save(orderType);
	}
	
	@PutMapping("{orderTypeId}")
	public OrderType updateOrderType(@PathVariable long orderTypeId, @RequestBody OrderType orderType)
	{
		try
		{
			var ot = repo.findById(orderTypeId).get();
			ot.setCode(orderType.getCode());
			ot.setName(orderType.getName());
			
			return repo.save(ot);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("{orderTypeId}")
	public ResponseEntity<HttpStatus> deleteOrderType(@PathVariable long orderTypeId) 
	{
		repo.deleteById(orderTypeId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
