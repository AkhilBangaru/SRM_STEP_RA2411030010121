import java.util.*;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                char c = (char) ((ch - 'A' + shift) % 26 + 'A');
                encrypted.append(c);
            } else if (ch >= 'a' && ch <= 'z') {
                char c = (char) ((ch - 'a' + shift) % 26 + 'a');
                encrypted.append(c);
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayASCII(String label, String text) {
        System.out.println("\n" + label + ":");
        for (int i = 0; i < text.length(); i++) {
            System.out.println("'" + text.charAt(i) + "' -> " + (int) text.charAt(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();
        String encrypted = encrypt(input, shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("\n=== CAESAR CIPHER SYSTEM ===");
        displayASCII("Original Text", input);
        displayASCII("Encrypted Text", encrypted);
        displayASCII("Decrypted Text", decrypted);
        System.out.println("\nOriginal: " + input);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        if (input.equals(decrypted)) {
            System.out.println("\nValidation Success: Decryption matches Original Text");
        } else {
            System.out.println("\nValidation Failed: Decryption does not match Original Text");
        }
        sc.close();
    }
}
