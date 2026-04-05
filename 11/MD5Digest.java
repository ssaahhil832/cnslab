import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Digest {
    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String text = args.length > 0 ? args[0] : "Hello world";

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(text.getBytes(StandardCharsets.UTF_8));

        System.out.println("Text  : " + text);
        System.out.println("MD5   : " + toHex(digest));
    }
}
