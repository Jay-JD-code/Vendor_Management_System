package com.vms.orders.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="purchase_orders")
@Data
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Long id;
	
	private String vendorId;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private LocalDateTime createdAt;

	
}
