import java.util.*;

public class UniqueCharFrequency {
    public static char[] uniqueCharacters(String text) {
        char[] temp = new char[text.length()];
        int index = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean unique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    unique = false;
                    break;
                }
            }
            if (unique) temp[index++] = c;
        }
        return Arrays.copyOf(temp, index);
    }

    public static String[][] uniqueCharFrequency(String text) {
        char[] uniques = uniqueCharacters(text);
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) freq[text.charAt(i)]++;
        String[][] result = new String[uniques.length][2];
        for (int i = 0; i < uniques.length; i++) {
            result[i][0] = String.valueOf(uniques[i]);
            result[i][1] = String.valueOf(freq[uniques[i]]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[][] result = uniqueCharFrequency(text);
        for (String[] r : result) System.out.println(r[0] + " -> " + r[1]);
        sc.close();
    }
}
