package com.vms.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vms.dto.LoginResponse;
import com.vms.dto.ForgotPasswordRequest;
import com.vms.dto.LoginRequest;
import com.vms.dto.RegisterRequest;
import com.vms.dto.ResetPasswordRequest;
import com.vms.dto.VerifyOtpRequest;
import com.vms.entity.OtpEntity;
import com.vms.entity.Role;
import com.vms.entity.UserEntity;

import com.vms.repository.OtpRepository;
import com.vms.repository.UserRepository;
import com.vms.util.JwtUtil;
import com.vms.util.OtpUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OtpRepository otpRepository;

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final OtpUtility utility;
	private final EmailService emailService;


	
	public void register(RegisterRequest req) {
		if(userRepository.existsByUsername(req.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		if(userRepository.existsByEmail(req.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		UserEntity user = modelMapper.map(req, UserEntity.class);
		
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		
		user.setRole(Role.VENDOR);
		
		userRepository.save(user);
	}
	
	public LoginResponse login(LoginRequest request) {
		UserEntity user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(),request.getUsernameOrEmail())
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}
		
		String accessToken = jwtUtil.generateAccessToken(user.getUsername(),user.getRole().name());
		String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
		return new LoginResponse(accessToken,refreshToken, user.getRole().name());
		
	}
	
	
	
    public void forgotPassword(ForgotPasswordRequest request){
		
    	if (!userRepository.existsByEmail(request.getEmail())) {
    	    throw new RuntimeException("User not found");
    	}
		
		otpRepository.findByEmail(request.getEmail()).ifPresent(otpRepository::delete);;
		
		String otp = utility.generateOtp();
		
		OtpEntity otpEntity = new OtpEntity();
		otpEntity.setEmail(request.getEmail());
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		otpRepository.save(otpEntity);
		
		emailService.sendOtpEmail(request.getEmail(), otp);
		
	}
    
    public String verifyOtp(VerifyOtpRequest request) {

        OtpEntity otpData = otpRepository
                .findByEmailAndOtp(request.getEmail(), request.getOtp())
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        // ✅ Generate token AFTER validation
        String resetToken = UUID.randomUUID().toString();

        otpData.setResetToken(resetToken);
        otpRepository.save(otpData);

        return resetToken; // 🔥 IMPORTANT
    }
    
    public void resetPassword(ResetPasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        OtpEntity otpData = otpRepository
                .findByResetToken(request.getResetToken())
                .orElseThrow(() -> new RuntimeException("Invalid reset token"));

        if (otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reset token expired");
        }

        UserEntity user = userRepository.findByEmail(otpData.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        otpRepository.delete(otpData); // 🔥 invalidate token
    }
}
