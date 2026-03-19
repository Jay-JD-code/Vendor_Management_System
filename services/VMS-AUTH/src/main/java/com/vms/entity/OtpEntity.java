package com.vms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="otp_store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	
	private String otp;
	
	private LocalDateTime expiryTime;
	
}
