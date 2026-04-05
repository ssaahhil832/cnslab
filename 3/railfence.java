import java.util.Scanner;

public class railfence {
    
    static String encrypt(String text, int rails) {
        if (rails == 1) return text;
        
        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            fence[i] = new StringBuilder();
        }
        
        int rail = 0;
        boolean down = true;
        
        for (char c : text.toCharArray()) {
            fence[rail].append(c);
            
            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }
            
            rail += down ? 1 : -1;
        }
        
        StringBuilder result = new StringBuilder();
        for (StringBuilder rail_chars : fence) {
            result.append(rail_chars);
        }
        
        return result.toString();
    }
    
    static String decrypt(String text, int rails) {
        if (rails == 1) return text;
        
        int[] railLengths = new int[rails];
        int rail = 0;
        boolean down = true;
        
        // Calculate length of each rail
        for (int i = 0; i < text.length(); i++) {
            railLengths[rail]++;
            
            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }
            
            rail += down ? 1 : -1;
        }
        
        // Create fence with characters from ciphertext
        String[] fence = new String[rails];
        int pos = 0;
        for (int i = 0; i < rails; i++) {
            fence[i] = text.substring(pos, pos + railLengths[i]);
            pos += railLengths[i];
        }
        
        // Reconstruct plaintext by following zigzag pattern
        StringBuilder result = new StringBuilder();
        int[] railPos = new int[rails];
        
        rail = 0;
        down = true;
        
        for (int i = 0; i < text.length(); i++) {
            result.append(fence[rail].charAt(railPos[rail]++));
            
            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }
            
            rail += down ? 1 : -1;
        }
        
        return result.toString();
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter text (UPPERCASE): ");
        String text = sc.nextLine();
        
        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();
        
        String encrypted = encrypt(text, rails);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decrypt(encrypted, rails);
        System.out.println("Decrypted: " + decrypted);
    }
}
