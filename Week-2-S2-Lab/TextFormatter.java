import java.util.*;

public class TextFormatter {
    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int count = 0, start = 0;
        for (int i = 0; i < words.size(); i++) {
            if (count + words.get(i).length() + (i - start) > width) {
                int spaces = width - count;
                int gaps = i - start - 1;
                StringBuilder sb = new StringBuilder();
                if (gaps == 0) {
                    sb.append(words.get(start));
                    while (sb.length() < width) sb.append(" ");
                } else {
                    int spaceEach = spaces / gaps;
                    int extra = spaces % gaps;
                    for (int j = start; j < i; j++) {
                        sb.append(words.get(j));
                        if (j < i - 1) {
                            for (int k = 0; k <= spaceEach; k++) sb.append(" ");
                            if (extra > 0) {
                                sb.append(" ");
                                extra--;
                            }
                        }
                    }
                }
                lines.add(sb.toString());
                start = i;
                count = 0;
            }
            count += words.get(i).length();
        }
        StringBuilder last = new StringBuilder();
        for (int i = start; i < words.size(); i++) {
            if (i > start) last.append(" ");
            last.append(words.get(i));
        }
        while (last.length() < width) last.append(" ");
        lines.add(last.toString());
        return lines;
    }

    public static List<String> centerAlign(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int count = 0;
        for (String word : words) {
            if (count + word.length() + (line.length() > 0 ? 1 : 0) > width) {
                int padding = width - line.length();
                int left = padding / 2;
                int right = padding - left;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < left; i++) sb.append(" ");
                sb.append(line);
                for (int i = 0; i < right; i++) sb.append(" ");
                lines.add(sb.toString());
                line.setLength(0);
                count = 0;
            }
            if (line.length() > 0) line.append(" ");
            line.append(word);
            count += word.length();
        }
        int padding = width - line.length();
        int left = padding / 2;
        int right = padding - left;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < left; i++) sb.append(" ");
        sb.append(line);
        for (int i = 0; i < right; i++) sb.append(" ");
        lines.add(sb.toString());
        return lines;
    }

    public static long testConcatenation(List<String> words, int width) {
        long start = System.nanoTime();
        String line = "";
        int count = 0;
        for (String word : words) {
            if (count + word.length() + (line.isEmpty() ? 0 : 1) > width) {
                line = "";
                count = 0;
            }
            if (!line.isEmpty()) line += " ";
            line += word;
            count += word.length();
        }
        return System.nanoTime() - start;
    }

    public static void display(String title, List<String> lines) {
        System.out.println("\n" + title);
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("Line %2d (%2d chars): %s\n", i + 1, lines.get(i).length(), lines.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();
        List<String> words = splitWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(words, width);
        display("Justified Text", justified);
        display("Centered Text", centered);
        long concatTime = testConcatenation(words, width);
        long builderTime = System.nanoTime();
        justifyText(words, width);
        builderTime = System.nanoTime() - builderTime;
        System.out.println("\nPerformance Analysis:");
        System.out.println("String Concatenation Time: " + concatTime + " ns");
        System.out.println("StringBuilder Time: " + builderTime + " ns");
        sc.close();
    }
}
