import java.util.Scanner;

class hill {
    static int[][] key = {{3, 3}, {2, 5}};
    
    static int mod26(int x) {
        return (x % 26 + 26) % 26;
    }
    
    static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1) {
                return i;
            }
        }
        return 1;
    }
    
    static int[][] inverseKey() {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]);
        det = mod26(det);
        int detInv = modInverse(det, 26);
        
        int[][] inv = new int[2][2];
        inv[0][0] = mod26(detInv * key[1][1]);
        inv[0][1] = mod26(detInv * (-key[0][1]));
        inv[1][0] = mod26(detInv * (-key[1][0]));
        inv[1][1] = mod26(detInv * key[0][0]);
        
        return inv;
    }
    
    static String encrypt(String text) {
        String result = "";
        if (text.length() % 2 != 0) {
            text += "x";
        }
        for (int i = 0; i < text.length(); i += 2) {
            int x = text.charAt(i) - 'A';
            int y = text.charAt(i + 1) - 'A';
            char c1 = (char)(mod26(key[0][0] * x + key[0][1] * y) + 'A');
            char c2 = (char)(mod26(key[1][0] * x + key[1][1] * y) + 'A');
            result += c1;
            result += c2;
        }
        return result;
    }
    
    static String decrypt(String text) {
        String result = "";
        int[][] invKey = inverseKey();
        
        for (int i = 0; i < text.length(); i += 2) {
            int x = text.charAt(i) - 'A';
            int y = text.charAt(i + 1) - 'A';
            char c1 = (char)(mod26(invKey[0][0] * x + invKey[0][1] * y) + 'A');
            char c2 = (char)(mod26(invKey[1][0] * x + invKey[1][1] * y) + 'A');
            result += c1;
            result += c2;
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