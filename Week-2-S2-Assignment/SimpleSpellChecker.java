import java.util.*;

public class SimpleSpellChecker {

    public static String[] manualSplit(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (start == -1) start = i;
            } else {
                if (start != -1) {
                    words.add(text.substring(start, i));
                    start = -1;
                }
            }
        }
        if (start != -1) words.add(text.substring(start));
        return words.toArray(new String[0]);
    }

    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[len1][len2];
    }

    public static String findClosestMatch(String word, String[] dictionary) {
        String bestMatch = word;
        int minDist = Integer.MAX_VALUE;
        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                bestMatch = dictWord;
            }
        }
        return minDist <= 2 ? bestMatch : word;
    }

    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s%n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("---------------------------------------------------------------");
        for (String w : words) {
            String suggestion = findClosestMatch(w, dictionary);
            int dist = stringDistance(w.toLowerCase(), suggestion.toLowerCase());
            String status = (dist == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-15s%n", w, suggestion, dist, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== SIMPLE SPELL CHECKER ===");
        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();
        String[] dictionary = {"java", "programming", "is", "fun", "and", "challenging", "hello", "world"};

        String[] words = manualSplit(sentence);
        displayResults(words, dictionary);
        sc.close();
    }
}
