import java.util.*;

public class SubstringReplacer {
    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                sb.append(replace);
                i += find.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static boolean compareWithBuiltIn(String text, String find, String replace, String manual) {
        String builtIn = text.replace(find, replace);
        return builtIn.equals(manual);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String find = sc.nextLine();
        String replace = sc.nextLine();
        List<Integer> positions = findOccurrences(text, find);
        String manual = manualReplace(text, find, replace);
        boolean same = compareWithBuiltIn(text, find, replace, manual);
        System.out.println("Original: " + text);
        System.out.println("Manual Replace: " + manual);
        System.out.println("Built-in Replace: " + text.replace(find, replace));
        System.out.println("Positions: " + positions);
        System.out.println("Same as Built-in: " + same);
        sc.close();
    }
}
