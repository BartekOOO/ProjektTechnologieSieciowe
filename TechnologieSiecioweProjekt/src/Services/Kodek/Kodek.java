package Services.Kodek;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
public class Kodek {
    private static String Key = "WANDUIEWBLWDRFKUWODFKWOD";
    public static String Encrypt(String text) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(Key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encryptedText = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encryptedText);
    }

    public static String Decrypt(String encryptedText) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(Key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decodedText = Base64.getDecoder().decode(encryptedText);

        byte[] decryptedText = cipher.doFinal(decodedText);

        return new String(decryptedText);
    }

}