import java.util.*;

public class WordSplitter {
    public static String[] splitWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (start != i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }

    public static int findLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }

    public static String[][] wordWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findLength(words[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();
        String[] words = splitWords(input);
        String[][] table = wordWithLengths(words);

        System.out.println("\nWord\tLength");
        for (String[] row : table) {
            System.out.println(row[0] + "\t" + Integer.valueOf(row[1]));
        }
        sc.close();
    }
}
