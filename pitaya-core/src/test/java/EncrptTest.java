import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import play.exceptions.UnexpectedException;
import play.libs.Codec;
import play.libs.Crypto;

public class EncrptTest {
	@Test
    public void testEncrypt() throws InterruptedException {
		try {
//			String ecstring = "U2FsdGVkX186EIvCLDEW5kR/1orCY/5sc13DxFk1u6EkQkw+JR9Ys/H0fs8POmYS";
			String hash = Crypto.encryptAES("email|123@meican.com|qweqw4123", "g2mygwur82a67asj");
//			System.out.println(hash);
			String decrypted = Crypto.decryptAES("f8186d28b893050d45deece0bde74ff9115a26e7cd06b8a7bb7d154b01801217", "g2mygwur82a67asj");
			System.out.println(decrypted);
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public static String encryptAES(String value, String privateKey) {
        try {
            byte[] raw = privateKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return Codec.byteToHexString(cipher.doFinal(value.getBytes()));
        } catch (Exception ex) {
            throw new UnexpectedException(ex);
        }
    }
	
	public static String decryptAES(String value, String privateKey) {
        try {
            byte[] raw = privateKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            return new String(cipher.doFinal(Codec.hexStringToByte(value)));
        } catch (Exception ex) {
            throw new UnexpectedException(ex);
        }
    }
}


