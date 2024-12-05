package util;
import java.util.UUID;
public class RandomStringGenerator {
	public static String generateRandomString(int length) {
		String generatedString = UUID.randomUUID().toString().replace("-","");
		return generatedString.substring(0, length);
	}
}
