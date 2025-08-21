import java.util.*;

public class EmailAnalyzer {
    public static boolean validateEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dotPos = email.lastIndexOf('.');
        if (atPos == -1 || atPos != lastAt) return false;
        if (dotPos == -1 || dotPos < atPos) return false;
        if (atPos == 0 || atPos == email.length() - 1) return false;
        if (dotPos == email.length() - 1) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int atPos = email.indexOf('@');
        int dotPos = email.lastIndexOf('.');
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        String domainName = email.substring(atPos + 1, dotPos);
        String extension = email.substring(dotPos + 1);
        return new String[]{username, domain, domainName, extension};
    }

    public static void analyzeStatistics(List<String> emails) {
        int validCount = 0, invalidCount = 0, totalUserLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (String email : emails) {
            if (validateEmail(email)) {
                validCount++;
                String[] comp = extractComponents(email);
                totalUserLength += comp[0].length();
                domainCount.put(comp[1], domainCount.getOrDefault(comp[1], 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String commonDomain = "None";
        int max = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                commonDomain = entry.getKey();
            }
        }
        double avgUserLength = validCount == 0 ? 0 : (double) totalUserLength / validCount;
        System.out.println("\n=== EMAIL STATISTICS ===");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + commonDomain);
        System.out.println("Average Username Length: " + avgUserLength);
    }

    public static void displayResults(List<String> emails) {
        System.out.printf("\n%-30s %-15s %-20s %-15s %-10s %-10s\n", 
                          "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (String email : emails) {
            boolean valid = validateEmail(email);
            if (valid) {
                String[] comp = extractComponents(email);
                System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", 
                                  email, comp[0], comp[1], comp[2], comp[3], "Valid");
            } else {
                System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", 
                                  email, "-", "-", "-", "-", "Invalid");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter emails (type 'done' to finish):");
        while (true) {
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("done")) break;
            emails.add(email);
        }
        displayResults(emails);
        analyzeStatistics(emails);
        sc.close();
    }
}
