package com.javainuse.test.veri.otp.pho;

import java.util.Random;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/otp")
    public ResponseEntity<String> registerUser(@RequestParam String phoneNumber) {
        // Generate OTP
        String otp = generateOtp();

        // Save user
        Phone user = new Phone();
        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        userRepository.save(user);

        return ResponseEntity.ok("Registration successful.");
    }

	public String generateOtp() {
	    Random random = new Random();
	    int otp = 100000 + random.nextInt(900000);
	    return String.valueOf(otp);
	}
	
}
