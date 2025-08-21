import java.util.*;

public class NestedLoopFrequency {
    public static String[] frequencyNestedLoops(String text) {
        char[] chars = text.toCharArray();
        int[] freq = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') continue;
            freq[i] = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0';
                }
            }
        }
        String[] result = new String[chars.length];
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') result[idx++] = chars[i] + " -> " + freq[i];
        }
        return Arrays.copyOf(result, idx);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[] result = frequencyNestedLoops(text);
        for (String r : result) System.out.println(r);
        sc.close();
    }
}
