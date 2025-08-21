import java.util.*;

public class StringManipulation {
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String,Integer> map = new LinkedHashMap<>();
        for (String w : words) map.put(w, map.getOrDefault(w, 0) + 1);
        for (Map.Entry<String,Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();

        String trimmed = input.trim();
        String replaced = trimmed.replace(" ", "_");
        String noDigits = trimmed.replaceAll("\\d", "");
        String[] words = trimmed.split("\\s+");
        String joined = String.join(" | ", words);
        String noPunct = removePunctuation(trimmed);
        String capitalized = capitalizeWords(trimmed);
        String reversed = reverseWordOrder(trimmed);

        System.out.println("Original: " + input);
        System.out.println("Trimmed: " + trimmed);
        System.out.println("Spaces replaced: " + replaced);
        System.out.println("No digits: " + noDigits);
        System.out.println("Words: " + Arrays.toString(words));
        System.out.println("Joined: " + joined);
        System.out.println("No punctuation: " + noPunct);
        System.out.println("Capitalized: " + capitalized);
        System.out.println("Reversed order: " + reversed);
        System.out.println("Word Frequencies:");
        countWordFrequency(trimmed);

        scanner.close();
    }
}
