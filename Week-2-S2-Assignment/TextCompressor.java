import java.util.*;

public class TextCompressor {

    public static Object[] countFrequencies(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (chars[j] == c) {
                    freq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                chars[uniqueCount] = c;
                freq[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        char[] resultChars = Arrays.copyOf(chars, uniqueCount);
        int[] resultFreq = Arrays.copyOf(freq, uniqueCount);
        return new Object[]{resultChars, resultFreq};
    }

    public static String[][] generateCodes(char[] chars, int[] freq) {
        int n = chars.length;
        String[][] codes = new String[n][2];

        Character[] boxedChars = new Character[n];
        for (int i = 0; i < n; i++) boxedChars[i] = chars[i];
        Integer[] boxedFreq = Arrays.stream(freq).boxed().toArray(Integer[]::new);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (boxedFreq[j] > boxedFreq[i]) {
                    int tmpF = boxedFreq[i]; boxedFreq[i] = boxedFreq[j]; boxedFreq[j] = tmpF;
                    char tmpC = boxedChars[i]; boxedChars[i] = boxedChars[j]; boxedChars[j] = tmpC;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            codes[i][0] = String.valueOf(boxedChars[i]);
            if (i < 10) codes[i][1] = String.valueOf(i); 
            else codes[i][1] = "#" + i;
        }

        return codes;
    }

    public static String compressText(String text, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            for (String[] code : codes) {
                if (code[0].equals(c)) {
                    sb.append(code[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static String decompressText(String compressed, String[][] codes) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            String token = String.valueOf(compressed.charAt(i));
            String match = null;
            for (String[] code : codes) {
                if (code[1].equals(token)) {
                    match = code[0];
                    break;
                }
            }
            if (match != null) {
                sb.append(match);
                i++;
            } else {
                if (compressed.charAt(i) == '#') {
                    String token2 = compressed.substring(i, i + 2);
                    for (String[] code : codes) {
                        if (code[1].equals(token2)) {
                            sb.append(code[0]);
                            break;
                        }
                    }
                    i += 2;
                } else {
                    i++;
                }
            }
        }
        return sb.toString();
    }

    public static void displayAnalysis(String text, String compressed, String decompressed, char[] chars, int[] freq, String[][] codes) {
        System.out.println("\n=== FREQUENCY TABLE ===");
        for (int i = 0; i < chars.length; i++) {
            System.out.println("'" + chars[i] + "' : " + freq[i]);
        }

        System.out.println("\n=== COMPRESSION MAPPING ===");
        for (String[] code : codes) {
            System.out.println("'" + code[0] + "' -> " + code[1]);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        double ratio = (double) compressed.length() / text.length();
        double efficiency = (1 - ratio) * 100;
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== TEXT COMPRESSION TOOL ===");
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        Object[] result = countFrequencies(text);
        char[] chars = (char[]) result[0];
        int[] freq = (int[]) result[1];

        String[][] codes = generateCodes(chars, freq);
        String compressed = compressText(text, codes);
        String decompressed = decompressText(compressed, codes);

        displayAnalysis(text, compressed, decompressed, chars, freq, codes);

        sc.close();
    }
}
