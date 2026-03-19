package com.vms.document.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vms.document.entity.DocumentEntity;
import com.vms.document.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

	public final DocumentService documentService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(
			@RequestParam("file") MultipartFile file,
			@RequestParam("vendorId") String vendorId,
			@RequestParam("documentType") String documentType) throws IOException{
		
		
		DocumentEntity doc =documentService.uploadFile(file, vendorId, documentType);
		return ResponseEntity.ok(doc);
	}
	
	@GetMapping("vendor/{vendorId}")
	public ResponseEntity<?> getDocuments(@PathVariable String vendorId){
		return ResponseEntity.ok(documentService.getVendorDocument(vendorId));
	}
	
}
