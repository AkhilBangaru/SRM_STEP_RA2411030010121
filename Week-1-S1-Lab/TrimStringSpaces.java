import java.util.*;

public class TrimStringSpaces {
    public static int[] findTrimPoints(String text) {
        int start = 0;
        int end = text.length() - 1;

        while (start <= end && text.charAt(start) == ' ') {
            start++;
        }
        while (end >= start && text.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end};
    }

    public static String customSubstring(String text, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }

    public static boolean compareStrings(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string with spaces: ");
        String input = sc.nextLine();

        int[] points = findTrimPoints(input);
        String trimmedCustom = customSubstring(input, points[0], points[1]);
        String trimmedBuiltIn = input.trim();

        System.out.println("Custom Trim Result: [" + trimmedCustom + "]");
        System.out.println("Built-in Trim Result: [" + trimmedBuiltIn + "]");
        System.out.println("Both are equal? " + compareStrings(trimmedCustom, trimmedBuiltIn));

        sc.close();
    }
}
