import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final BigInteger n;
    private final BigInteger e;
    private final BigInteger d;

    public RSA(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger publicE = BigInteger.valueOf(65537);

        // Ensure e is coprime with phi
        while (!phi.gcd(publicE).equals(BigInteger.ONE)) {
            publicE = publicE.add(BigInteger.TWO);
        }

        e = publicE;
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        String text = "Hello World";
        BigInteger message = new BigInteger(text.getBytes());

        BigInteger cipher = rsa.encrypt(message);
        BigInteger plain = rsa.decrypt(cipher);

        System.out.println("Plaintext : " + text);
        System.out.println("Cipher    : " + cipher.toString(16));
        System.out.println("Decrypted : " + new String(plain.toByteArray()));
    }
}
