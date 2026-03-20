package com.vms.orders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="order_items")
@Data
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long orderId;
	
	private String productName;
	
	private Integer quantity;
	
	private Double price;
}
