package my.edu.utem.ftmk.dad.restorderapp.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.restorderapp.models.ProductType;

@Controller
public class ProductTypeMenuController {
	
	private String defaultURI = "http://localhost:8080/orderapp/api/producttypes";
	
	@GetMapping("/producttype/list")
	public String getProductType(Model model)
	{
		// The URI for GET product types
		String uri = "http://localhost:8080/orderapp/api/producttypes";
		
		// Get a list product types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductType[]> response = restTemplate.getForEntity(uri, ProductType[].class);
		
		// Parse JSON data to array of object
		ProductType productTypes[] = response.getBody();
		
		// Parse an array to a list objects
		List<ProductType> productTypeList = Arrays.asList(productTypes);
		
		// Attach list to model as attribute
		model.addAttribute("productTypes", productTypeList);
		
		return "producttype/index";
	}
	
	/**
	 * This method will update or add an product type.
	 * 
	 * @param productType
	 * @return
	 */
	@RequestMapping("/producttype/save")
	public String updateProductType(@ModelAttribute ProductType productType)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<ProductType> request = new HttpEntity<ProductType>(productType);
		
		String productTypeResponse = "";
		
		if (productType.getProductTypeId() > 0)
		{
			restTemplate.put(defaultURI + "/" + productType.getProductTypeId(), request, ProductType.class);
		}
		else
		{
			productTypeResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}
		
		System.out.println(productTypeResponse);
		
		return "redirect:/producttype/list";
	}
	
	/**
	 * This method will get an product type.
	 * 
	 * @param productTypeId
	 * @param model
	 * @return
	 */
	@GetMapping("/producttype/{productTypeId}")
	public String getProductType(@PathVariable Integer productTypeId, Model model)
	{
		String pageTitle= "New Product Type";
		ProductType productType = new ProductType();
			
		if (productTypeId > 0)
		{
			String uri = defaultURI + "/" + productTypeId;
			
			RestTemplate restTemplate = new RestTemplate();
			productType = restTemplate.getForObject(uri, ProductType.class);
			
			pageTitle = "Edit Product Type";
		}
		
		model.addAttribute("productType", productType);
		model.addAttribute("pageTitle", pageTitle);
		
		return "producttype/producttypeinfo";
	}
	
	/**
	 * This method will deletes an product type.
	 * 
	 * @param productTypeId
	 * @return
	 */
	@RequestMapping("/producttype/delete/{productTypeId}")
	public String deleteProductType(@PathVariable Integer productTypeId)
	{
		String uri = defaultURI + "/{productTypeId}";
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(uri, Map.of("productTypeId", Integer.toString(productTypeId)));
		
		return "redirect:/producttype/list";
	}
}
