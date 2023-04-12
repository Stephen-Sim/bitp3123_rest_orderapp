package my.edu.utem.ftmk.dad.restorderapp.models;

import org.hibernate.annotations.*;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid")
	private long productId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
    @JoinColumn(name="producttype", nullable=false)
	private ProductType ProductType;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return ProductType;
	}

	public void setProductType(ProductType productType) {
		ProductType = productType;
	}	
}
