import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RC4_BlowFish {
	private static String asHex(byte[] buf) {
		StringBuilder sb = new StringBuilder(buf.length * 2);
		for (byte b : buf) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}

	private static void runRc4Demo() throws Exception {
		String rc4Text = "RC4 demo";
		String rc4KeyText = "simplekey";

		SecretKeySpec rc4Key = new SecretKeySpec(rc4KeyText.getBytes(StandardCharsets.UTF_8), "RC4");
		Cipher rc4 = Cipher.getInstance("RC4");

		rc4.init(Cipher.ENCRYPT_MODE, rc4Key);
		byte[] rc4Cipher = rc4.doFinal(rc4Text.getBytes(StandardCharsets.UTF_8));

		rc4.init(Cipher.DECRYPT_MODE, rc4Key);
		byte[] rc4Plain = rc4.doFinal(rc4Cipher);

		System.out.println("RC4 Plaintext : " + rc4Text);
		System.out.println("RC4 Key       : " + rc4KeyText);
		System.out.println("RC4 CipherHex : " + asHex(rc4Cipher));
		System.out.println("RC4 Decrypted : " + new String(rc4Plain, StandardCharsets.UTF_8));
	}

	private static SecretKey loadBlowfishKey(String keystorePath, char[] storePass, String alias, char[] keyPass)
			throws Exception {
		KeyStore ks = KeyStore.getInstance("JCEKS");
		try (FileInputStream fis = new FileInputStream(keystorePath)) {
			ks.load(fis, storePass);
		}
		return (SecretKey) ks.getKey(alias, keyPass);
	}

	private static void runBlowfishDemo(String keystorePath, char[] storePass, String alias, char[] keyPass)
			throws Exception {
		String text = "Hello world";
		SecretKey blowfishKey = loadBlowfishKey(keystorePath, storePass, alias, keyPass);

		Cipher bf = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		bf.init(Cipher.ENCRYPT_MODE, blowfishKey);
		byte[] cipherBytes = bf.doFinal(text.getBytes(StandardCharsets.UTF_8));

		bf.init(Cipher.DECRYPT_MODE, blowfishKey);
		byte[] plainBytes = bf.doFinal(cipherBytes);

		System.out.println("Blowfish Plaintext : " + text);
		System.out.println("Blowfish CipherHex : " + asHex(cipherBytes));
		System.out.println("Blowfish Decrypted : " + new String(plainBytes, StandardCharsets.UTF_8));
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.out.println("Usage: java RC4_BlowFish <keystore.jceks> <storepass> <alias> <keypass>");
			System.out.println("Example keytool:");
			System.out.println(
					"  keytool -genseckey -alias bfkey -keyalg Blowfish -keysize 128 -storetype JCEKS -keystore blowfish.jceks -storepass changeit -keypass changeit");
			return;
		}

		runRc4Demo();

		String keystorePath = args[0];
		char[] storePass = args[1].toCharArray();
		String alias = args[2];
		char[] keyPass = args[3].toCharArray();

		runBlowfishDemo(keystorePath, storePass, alias, keyPass);
	}
}
