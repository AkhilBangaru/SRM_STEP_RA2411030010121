import java.util.*;

public class TextProcessor {
    public static String cleanInput(String input) {
        String cleaned = input.trim().replaceAll("\\s+", " ");
        String[] words = cleaned.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) sb.append(w.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String[] sentences = text.split("[.!?]");
        int sentenceCount = (int) Arrays.stream(sentences).filter(s -> !s.trim().isEmpty()).count();
        String noSpaces = text.replaceAll("\\s+", "");
        int charCount = noSpaces.length();
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        String longestWord = "";
        for (String w : words) {
            String clean = w.replaceAll("[^a-zA-Z]", "");
            if (clean.length() > longestWord.length()) longestWord = clean;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : noSpaces.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        char mostCommon = ' ';
        int max = 0;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                mostCommon = e.getKey();
            }
        }

        System.out.println("Word count: " + wordCount);
        System.out.println("Sentence count: " + sentenceCount);
        System.out.println("Character count (no spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
        System.out.println("Most common character: " + mostCommon);
    }

    public static String[] getWordsSorted(String text) {
        String cleaned = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String[] words = cleaned.split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);
        System.out.println("\nCleaned Text: " + cleaned);

        System.out.println("\n--- Analysis ---");
        analyzeText(cleaned);

        System.out.println("\n--- Words in Alphabetical Order ---");
        String[] sortedWords = getWordsSorted(cleaned);
        for (String w : sortedWords) System.out.println(w);

        System.out.print("\nSearch for a word: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = Arrays.asList(sortedWords).contains(search);
        System.out.println("Found: " + found);

        scanner.close();
    }
}
