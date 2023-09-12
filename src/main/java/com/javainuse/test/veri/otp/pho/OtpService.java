package com.javainuse.test.veri.otp.pho;

import java.util.Random;

public class OtpService {
	public String generateOtp() {
	    Random random = new Random();
	    int otp = 100000 + random.nextInt(900000);
	    return String.valueOf(otp);
	}

}
