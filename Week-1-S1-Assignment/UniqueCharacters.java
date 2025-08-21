import java.util.*;

public class UniqueCharacters {
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (Exception e) {}
        return count;
    }

    public static char[] uniqueCharacters(String text) {
        int len = getLength(text);
        char[] temp = new char[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        char[] result = uniqueCharacters(text);
        System.out.println(Arrays.toString(result));
        sc.close();
    }
}
