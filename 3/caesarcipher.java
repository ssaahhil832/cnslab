import java.util.Scanner;

class caesarcipher {
    public static String encrypt(String text, int key) {
        String result = "";
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char ch = (char)(((c - 'A' + key) % 26) + 'A');
                result += ch;
            }
        }
        return result;
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - key);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text (UPPERCASE): ");
        String text = sc.nextLine();
        System.out.print("Enter key: ");
        int key = sc.nextInt();
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, key));
    }
}
