import java.util.*;

public class ASCIIProcessor {
    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z') return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9') return "Digit";
        else return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') return (char)(ch + 32);
        else if (ch >= 'a' && ch <= 'z') return (char)(ch - 32);
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append((char)('A' + (c - 'A' + shift + 26) % 26));
            } else if (Character.isLowerCase(c)) {
                sb.append((char)('a' + (c - 'a' + shift + 26) % 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char)i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int)text.charAt(i);
        }
        return arr;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char)val);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int)ch;
            System.out.println("Char: " + ch + " ASCII: " + ascii);
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);
            if (type.contains("Letter")) {
                char toggled = toggleCase(ch);
                System.out.println("Toggle Case: " + toggled + " ASCII: " + (int)toggled);
                System.out.println("Diff: " + Math.abs(ch - toggled));
            }
        }

        System.out.println("\nASCII Table (65-90):");
        displayASCIITable(65, 90);

        System.out.println("\nCaesar Cipher (+3): " + caesarCipher(input, 3));

        int[] asciiArr = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArr));
        System.out.println("Back to String: " + asciiToString(asciiArr));

        scanner.close();
    }
}
