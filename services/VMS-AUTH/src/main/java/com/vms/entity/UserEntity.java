package com.vms.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
