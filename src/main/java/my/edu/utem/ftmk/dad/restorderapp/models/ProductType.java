package my.edu.utem.ftmk.dad.restorderapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "producttype")
public class ProductType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producttypeid")
	private long productTypeId;
	
	@Column(name = "name")
	private String name;
	
	public long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
