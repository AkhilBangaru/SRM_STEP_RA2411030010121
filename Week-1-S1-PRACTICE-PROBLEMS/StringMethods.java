import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name (first and last): ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();

        System.out.print("Enter a sentence about your programming experience: ");
        String sentence = scanner.nextLine();

        String[] nameParts = fullName.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        int charCount = sentence.replace(" ", "").length();

        String languageUpper = language.toUpperCase();

        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Language (uppercase): " + languageUpper);
        System.out.println("Characters in experience sentence (excluding spaces): " + charCount);
        System.out.println("Full Sentence: " + sentence);

        scanner.close();
    }
}
