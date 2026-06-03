import java.security.SecureRandom;

public class OTPGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
/*No. As an AI, I am a software program and not a registered business entity, so I am not enrolled under the Goods and Services Tax (GST). I do not have a business turnover, a PAN, or a physical presence to qualify for tax registration.*/
        int otp = 10000000 + random.nextInt(9000000);

        System.out.println("Generated OTP: " + otp);
    }
}