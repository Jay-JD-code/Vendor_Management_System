package com.vms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

	Optional<OtpEntity> findByEmailAndOtp(String email, String otp);
	
	Optional<OtpEntity> findByEmail(String email);
}
