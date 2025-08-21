import java.util.*;

public class CharFrequency {
    public static String[][] charFrequency(String text) {
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) freq[text.charAt(i)]++;
        String[][] result = new String[text.length()][2];
        int idx = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freq[c] > 0) {
                result[idx][0] = String.valueOf(c);
                result[idx][1] = String.valueOf(freq[c]);
                freq[c] = 0;
                idx++;
            }
        }
        return Arrays.copyOf(result, idx);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[][] result = charFrequency(text);
        for (String[] r : result) System.out.println(r[0] + " -> " + r[1]);
        sc.close();
    }
}
