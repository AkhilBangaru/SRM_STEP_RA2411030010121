import java.util.*;

public class StringBuiltInMethods {
    public static int countVowels(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count++;
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";
        System.out.println("Original: " + sampleText);
        System.out.println("Length: " + sampleText.length());
        String trimmed = sampleText.trim();
        System.out.println("Trimmed: " + trimmed);
        System.out.println("Trimmed Length: " + trimmed.length());
        System.out.println("Char at index 5: " + trimmed.charAt(5));
        System.out.println("Substring: " + trimmed.substring(trimmed.indexOf("Programming"), trimmed.indexOf("Programming") + "Programming".length()));
        System.out.println("Index of Fun: " + trimmed.indexOf("Fun"));
        System.out.println("Contains Java: " + trimmed.contains("Java"));
        System.out.println("Starts with Java: " + trimmed.startsWith("Java"));
        System.out.println("Ends with !: " + trimmed.endsWith("!"));
        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());
        System.out.println("Vowels: " + countVowels(trimmed));
        System.out.print("Occurrences of 'a': ");
        findAllOccurrences(trimmed, 'a');
    }
}
