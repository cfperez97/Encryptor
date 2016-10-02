import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtils {
	private static SecretKey generateKey(String key) throws Exception {
		byte[] keyAsBytes = key.getBytes("UTF8");
        KeySpec myKeySpec = new DESKeySpec(keyAsBytes);
        SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance("DES");
        return mySecretKeyFactory.generateSecret(myKeySpec);
	}
	public static String encrypt(String str, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		String encryptedString = null;
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
        byte[] plainText = str.getBytes("UTF8");
        byte[] encryptedText = cipher.doFinal(plainText);
        BASE64Encoder base64encoder = new BASE64Encoder();
        encryptedString = base64encoder.encode(encryptedText);
        return encryptedString;
	}
	public static String decrypt(String str, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		String decryptedText = null;
		cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
        BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] encryptedText = base64decoder.decodeBuffer(str);
        byte[] plainText = cipher.doFinal(encryptedText);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < plainText.length; i++) {
            stringBuffer.append((char) plainText[i]);
        }
        decryptedText = stringBuffer.toString();
        return decryptedText;
	}
}