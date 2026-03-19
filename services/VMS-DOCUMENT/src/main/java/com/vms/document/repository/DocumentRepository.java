package com.vms.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vms.document.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

	List<DocumentEntity> findByVendorId(String vendorId);
}
