package com.vms.document.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="documents")
@Data
public class DocumentEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String vendorId;
	
	private String fileName;
	
	private String fileType;
	
	private String documentType;
	
	private String filePath;
	
	private LocalDateTime uploadedAt;
}
