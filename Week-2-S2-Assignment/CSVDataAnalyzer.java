import java.util.*;

public class CSVDataAnalyzer {

    public static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else if ((c == '\n' || c == '\r') && !inQuotes) {
                if (current.length() > 0 || fields.size() > 0) {
                    fields.add(current.toString());
                    rows.add(fields.toArray(new String[0]));
                    fields.clear();
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0 || !fields.isEmpty()) {
            fields.add(current.toString());
            rows.add(fields.toArray(new String[0]));
        }
        return rows.toArray(new String[0][0]);
    }

    public static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    data[i][j] = data[i][j].trim();
                }
            }
        }
        return data;
    }

    public static void analyzeData(String[][] data) {
        int rows = data.length - 1;
        int cols = data[0].length;
        System.out.println("\n=== DATA ANALYSIS ===");
        System.out.println("Total records: " + rows);

        for (int col = 0; col < cols; col++) {
            boolean numeric = true;
            List<Double> numbers = new ArrayList<>();
            Set<String> unique = new HashSet<>();
            int missing = 0;

            for (int row = 1; row < data.length; row++) {
                String val = data[row][col];
                if (val == null || val.isEmpty()) {
                    missing++;
                    continue;
                }
                if (isNumeric(val)) {
                    numbers.add(Double.parseDouble(val));
                } else {
                    numeric = false;
                    unique.add(val);
                }
            }

            System.out.println("\nColumn: " + data[0][col]);
            if (numeric && !numbers.isEmpty()) {
                double min = Collections.min(numbers);
                double max = Collections.max(numbers);
                double avg = numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                System.out.printf("Numeric → Min: %.2f, Max: %.2f, Avg: %.2f%n", min, max, avg);
            } else {
                System.out.println("Categorical → Unique values: " + unique.size());
            }
            System.out.println("Missing/Invalid entries: " + missing);
        }
    }

    public static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '.') return false;
        }
        return !s.isEmpty();
    }

    public static void formatTable(String[][] data) {
        int cols = data[0].length;
        int[] widths = new int[cols];

        for (int j = 0; j < cols; j++) {
            int maxLen = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i][j] != null) {
                    maxLen = Math.max(maxLen, data[i][j].length());
                }
            }
            widths[j] = maxLen + 2;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=== FORMATTED TABLE ===\n");
        for (int i = 0; i < data.length; i++) {
            sb.append("|");
            for (int j = 0; j < cols; j++) {
                String field = data[i][j] == null ? "" : data[i][j];
                sb.append(String.format(" %-" + widths[j] + "s", field));
                sb.append("|");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void generateReport(String[][] data) {
        System.out.println("\n=== DATA SUMMARY REPORT ===");
        int totalRecords = data.length - 1;
        int totalFields = totalRecords * data[0].length;
        int missing = 0;

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == null || data[i][j].isEmpty()) {
                    missing++;
                }
            }
        }

        double completeness = 100.0 * (totalFields - missing) / totalFields;
        System.out.println("Total Records: " + totalRecords);
        System.out.println("Missing Fields: " + missing);
        System.out.printf("Data Completeness: %.2f%%%n", completeness);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] data = parseCSV(input.toString());
        cleanData(data);
        formatTable(data);
        analyzeData(data);
        generateReport(data);

        sc.close();
    }
}
