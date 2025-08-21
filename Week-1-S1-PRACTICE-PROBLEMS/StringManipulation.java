public class StringManipulation {
    public static void main(String[] args) {
        String str1 = "Java Programming";
        String str2 = new String("Java Programming");
        char[] chars = {'J', 'a', 'v', 'a', ' ', 'P', 'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g'};
        String str3 = new String(chars);

        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str2 == str3: " + (str2 == str3));

        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        System.out.println("str2.equals(str3): " + str2.equals(str3));

        System.out.println("\nExplanation:");
        System.out.println("== compares memory references (whether they point to the same object).");
        System.out.println(".equals() compares actual string content.");

        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";
        System.out.println("\n" + quote);
    }
}
