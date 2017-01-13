package se.jola.HashTest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class App {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		SecureRandom random = new SecureRandom();

		byte[] salt = new byte[32];
		
		random.nextBytes(salt);

		String password = "pass";
		
		byte[] test1 = createHash(password, salt);
		
		byte[] test2 = createHash(password, salt);
		
		System.out.println(Base64.getEncoder().encodeToString(test1).toString());
		System.out.println(Base64.getEncoder().encodeToString(test2).toString());
		
		System.out.println(Arrays.equals(test1, test2));
	}

	private static byte[] createHash(String stringToBeHashed, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

		PBEKeySpec spec = new PBEKeySpec(stringToBeHashed.toCharArray(), salt, 5, 256);

		SecretKey secretKey = skf.generateSecret(spec);

		return secretKey.getEncoded();
	}

}
