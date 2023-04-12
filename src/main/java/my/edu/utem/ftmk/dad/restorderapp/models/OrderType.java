package my.edu.utem.ftmk.dad.restorderapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ordertype")
public class OrderType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderTypeId")
	private long orderTypeId;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	public long getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(long orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
