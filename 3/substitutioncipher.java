import java.util.Scanner;

class substitutioncipher {
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String key = "QWERTYUIOPASDFGHJKLZXCVBNM";

    public static String encrypt(String text) {
        String result = "";
        for (char c : text.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                result += key.charAt(index);
            }
        }
        return result;
    }

    public static String decrypt(String text) {
        String result = "";
        for (char c : text.toCharArray()) {
            int index = key.indexOf(c);  // Find position in key instead
            if (index != -1) {
                result += alphabet.charAt(index);  // Get from alphabet instead
            }
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text (UPPERCASE): ");
        String text = sc.nextLine();
        String encrypted = encrypt(text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted));
    }
}