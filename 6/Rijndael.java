import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Rijndael {
    private static String asHex(byte[] buf) {
        StringBuilder sb = new StringBuilder(buf.length * 2);
        for (byte b : buf) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] out = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            out[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        String plaintext = "Two One Nine Two"; // 16 bytes
        String keyText = "Thats my Kung Fu";   // 16 bytes

        SecretKeySpec key = new SecretKeySpec(keyText.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));
        String cipherHex = asHex(cipherText);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainOut = cipher.doFinal(hexToBytes(cipherHex));

        System.out.println("Plaintext : " + plaintext);
        System.out.println("Key       : " + keyText);
        System.out.println("CipherHex : " + cipherHex);
        System.out.println("Decrypted : " + new String(plainOut, "UTF-8"));
    }
}
