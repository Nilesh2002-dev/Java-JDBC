import java.security.SecureRandom;

public class OTPGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        int otp = 10000000 + random.nextInt(9000000);

        System.out.println("Generated OTP: " + otp);
    }
}