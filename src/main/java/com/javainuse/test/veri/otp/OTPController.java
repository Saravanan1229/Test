package com.javainuse.test.veri.otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public String sendOTP(@RequestBody String toEmail) {
        String otp = OTPGenerator.generateOTP(6); // Change length as needed
        emailService.sendOTP(toEmail, otp);
        return "OTP sent successfully!";
    }
}
