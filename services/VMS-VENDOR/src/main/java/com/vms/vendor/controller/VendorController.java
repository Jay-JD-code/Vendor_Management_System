package com.vms.vendor.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vms.vendor.dto.request.VendorRequest;
import com.vms.vendor.dto.response.VendorResponse;
import com.vms.vendor.service.VendorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/vendors")
@RequiredArgsConstructor
public class VendorController {

	
	private final VendorService service;
	

	@PostMapping
	public ResponseEntity<VendorResponse> createVendor(@RequestBody VendorRequest request){
		
		return ResponseEntity.ok(service.createVendor(request));
	}
	
	@GetMapping
	public ResponseEntity<List<VendorResponse>> getAllVendors(){
		return ResponseEntity.ok(service.getAllVendors());
	}
	
	@PutMapping("/{id}/approve")
	public ResponseEntity<VendorResponse> approveVendor(@PathVariable UUID id){
		
		return ResponseEntity.ok(service.approveVendor(id));
	}
	public static void main(String[] args){
		System.out.println("HIT VENDOR API");
	}
}
