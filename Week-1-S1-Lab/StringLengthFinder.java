import java.util.*;

public class StringLengthFinder {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.next();
        int customLength = findLength(input);
        int builtinLength = input.length();
        System.out.println("Custom Length: " + customLength);
        System.out.println("Built-in Length: " + builtinLength);
        sc.close();
    }
}
