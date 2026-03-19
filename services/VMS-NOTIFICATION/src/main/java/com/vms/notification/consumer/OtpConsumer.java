package com.vms.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.vms.notification.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpConsumer {

	private final EmailService emailService;
	
	@KafkaListener(topics="otp-topic", groupId="notification-group")
	public void consumerOtp(String message) {
		
		System.out.println("Recieved message: "+ message);
		
		String[] parts = message.split(":");
		
		String email = parts[0];
		String otp = parts[1];
		
		emailService.sendOtpEmail(email, otp);
	}
}
