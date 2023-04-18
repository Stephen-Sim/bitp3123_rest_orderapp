package my.edu.utem.ftmk.dad.restorderapp.controllers;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.metal.MetalBorders.ToolBarBorder;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.restorderapp.models.OrderType;

@Controller
public class OrderTypeMenuController {
	
	private String defaultURI = "http://localhost:8080/orderapp/api/ordertypes";
	
	@GetMapping("/ordertype/list")
	public String getOrderTypes(Model model)
	{
		// The URI for GET order types
		String uri = "http://localhost:8080/orderapp/api/ordertypes";
		
		// Get a list order types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<OrderType[]> response = restTemplate.getForEntity(uri, OrderType[].class);
		
		// Parse JSON data to array of object
		OrderType orderTypes[] = response.getBody();
		
		// Parse an array to a list objects
		List<OrderType> orderTypeList = Arrays.asList(orderTypes);
		
		// Attach list to model as attribute
		model.addAttribute("orderTypes", orderTypeList);
		
		return "ordertype/index";
	}
	
	/**
	 * This method will update or add an order type.
	 * 
	 * @param orderType
	 * @return
	 */
	@RequestMapping("/ordertype/save")
	public String updateOrderType(@ModelAttribute OrderType ordetType)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<OrderType> request = new HttpEntity<OrderType>(ordetType);
		
		String orderTypeResponse = "";
		
		if (ordetType.getOrderTypeId() > 0)
		{
			restTemplate.put(defaultURI + "/" + ordetType.getOrderTypeId(), request, OrderType.class);
		}
		else
		{
			orderTypeResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}
		
		System.out.println(orderTypeResponse);
		
		return "redirect:/ordertype/list";
	}
	
	/**
	 * This method will get an order type.
	 * 
	 * @param orderTypeId
	 * @param model
	 * @return
	 */
	@GetMapping("/ordertype/{orderTypeId}")
	public String getOrderType(@PathVariable Integer orderTypeId, Model model)
	{
		String pageTitle= "New Order Type";
		OrderType orderType = new OrderType();
			
		if (orderTypeId > 0)
		{
			String uri = defaultURI + "/" + orderTypeId;
			
			RestTemplate restTemplate = new RestTemplate();
			orderType = restTemplate.getForObject(uri, OrderType.class);
			
			pageTitle = "Edit Order Type";
		}
		
		model.addAttribute("orderType", orderType);
		model.addAttribute("pageTitle", pageTitle);
		
		return "ordertype/ordertypeinfo";
	}
	
	/**
	 * This method will deletes an order type.
	 * 
	 * @param orderTypeId
	 * @return
	 */
	@RequestMapping("/ordertype/delete/{orderTypeId}")
	public String deleteOrderType(@PathVariable Integer orderTypeId)
	{
		String uri = defaultURI + "/{orderTypeId}";
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(uri, Map.of("orderTypeId", Integer.toString(orderTypeId)));
		
		return "redirect:/ordertype/list";
	}
}
