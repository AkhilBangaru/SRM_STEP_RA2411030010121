import java.util.*;

public class AdvancedStringAnalyzer {

    public static double calculateSimilarity(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        int distance = dp[len1][len2];
        int maxLen = Math.max(len1, len2);
        return maxLen == 0 ? 100.0 : ((double)(maxLen - distance) / maxLen) * 100.0;
    }

    public static void performAllComparisons(String str1, String str2) {
        System.out.println("Reference Equality: " + (str1 == str2));
        System.out.println("Content Equality: " + str1.equals(str2));
        System.out.println("Case-Insensitive Equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic Compare: " + str1.compareTo(str2));
        System.out.println("Case-Insensitive Compare: " + str1.compareToIgnoreCase(str2));
        System.out.println("Similarity %: " + calculateSimilarity(str1, str2));
    }

    public static void analyzeMemoryUsage(String... strings) {
        for (String s : strings) {
            int bytes = s.length() * 2;
            System.out.println("String: \"" + s + "\" Length: " + s.length() + " Approx. Memory: " + bytes + " bytes");
        }
    }

    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        performAllComparisons(str1, str2);

        System.out.println("\nMemory Usage Analysis:");
        analyzeMemoryUsage(str1, str2);

        System.out.println("\nOptimized Processing:");
        String[] arr = {str1, str2, "Extra", "Strings", "Here"};
        System.out.println(optimizedStringProcessing(arr));

        scanner.close();
    }
}
