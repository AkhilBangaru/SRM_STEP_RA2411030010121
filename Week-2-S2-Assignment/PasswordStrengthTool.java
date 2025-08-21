import java.util.*;

public class PasswordStrengthTool {

    public static Map<String, Integer> analyzePassword(String password) {
        int upper = 0, lower = 0, digits = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            int ascii = (int) ch;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digits++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        Map<String, Integer> stats = new HashMap<>();
        stats.put("upper", upper);
        stats.put("lower", lower);
        stats.put("digits", digits);
        stats.put("special", special);
        return stats;
    }

    public static int calculateScore(String password, Map<String, Integer> stats) {
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (stats.get("upper") > 0) score += 10;
        if (stats.get("lower") > 0) score += 10;
        if (stats.get("digits") > 0) score += 10;
        if (stats.get("special") > 0) score += 10;
        String lowerPwd = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password", "111"};
        for (String pattern : weakPatterns) {
            if (lowerPwd.contains(pattern)) score -= 15;
        }
        return score;
    }

    public static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    public static String generateStrongPassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}|;:,.<>?/";
        String allChars = upper + lower + digits + special;

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder finalPwd = new StringBuilder();
        for (char c : chars) finalPwd.append(c);

        return finalPwd.toString();
    }

    public static void displayResults(String password, Map<String, Integer> stats, int score, String strength) {
        System.out.printf("%-15s %-7d %-10d %-10d %-10d %-13d %-7d %-10s%n",
                password,
                password.length(),
                stats.get("upper"),
                stats.get("lower"),
                stats.get("digits"),
                stats.get("special"),
                score,
                strength);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== PASSWORD STRENGTH ANALYZER ===");
        System.out.print("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.printf("%-15s %-7s %-10s %-10s %-10s %-13s %-7s %-10s%n",
                "Password", "Length", "Uppercase", "Lowercase", "Digits", "Special Chars", "Score", "Strength");
        System.out.println("-------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            String pwd = sc.nextLine();
            Map<String, Integer> stats = analyzePassword(pwd);
            int score = calculateScore(pwd, stats);
            String strength = getStrengthLevel(score);
            displayResults(pwd, stats, score, strength);
        }

        System.out.print("\nDo you want to generate a strong password? (y/n): ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            System.out.print("Enter desired length: ");
            int len = sc.nextInt();
            String newPwd = generateStrongPassword(len);
            System.out.println("Generated Strong Password: " + newPwd);
        }

        sc.close();
    }
}
