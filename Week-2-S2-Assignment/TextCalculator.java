import java.util.*;

public class TextCalculator {

    public static boolean validateExpression(String expr) {
        int balance = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!((c >= '0' && c <= '9') || "+-*/() ".indexOf(c) >= 0)) {
                return false;
            }
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (c >= '0' && c <= '9') {
                int j = i;
                while (j < expr.length() && expr.charAt(j) >= '0' && expr.charAt(j) <= '9') j++;
                tokens.add(expr.substring(i, j));
                i = j;
            } else {
                tokens.add(String.valueOf(c));
                i++;
            }
        }
        return tokens;
    }

    public static int evaluateSimple(List<String> tokens, StringBuilder steps) {
        for (int i = 0; i < tokens.size(); ) {
            if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                int res = tokens.get(i).equals("*") ? a * b : a / b;
                steps.append(String.join(" ", tokens)).append(" => ").append(res).append("\n");
                tokens.set(i - 1, String.valueOf(res));
                tokens.remove(i);
                tokens.remove(i);
            } else i++;
        }

        for (int i = 0; i < tokens.size(); ) {
            if (tokens.get(i).equals("+") || tokens.get(i).equals("-")) {
                int a = Integer.parseInt(tokens.get(i - 1));
                int b = Integer.parseInt(tokens.get(i + 1));
                int res = tokens.get(i).equals("+") ? a + b : a - b;
                steps.append(String.join(" ", tokens)).append(" => ").append(res).append("\n");
                tokens.set(i - 1, String.valueOf(res));
                tokens.remove(i);
                tokens.remove(i);
            } else i++;
        }
        return Integer.parseInt(tokens.get(0));
    }

    public static int evaluateWithParentheses(String expr, StringBuilder steps) {
        while (expr.contains("(")) {
            int close = expr.indexOf(")");
            int open = expr.lastIndexOf("(", close);
            String inside = expr.substring(open + 1, close);
            int val = evaluateWithParentheses(inside, steps);
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
            steps.append("Replace (" + inside + ") with " + val + " => " + expr + "\n");
        }
        List<String> tokens = tokenize(expr);
        return evaluateSimple(tokens, steps);
    }

    public static void calculateExpression(String expr) {
        System.out.println("\n=== CALCULATION PROCESS ===");
        System.out.println("Original Expression: " + expr);
        if (!validateExpression(expr)) {
            System.out.println("Invalid expression format!");
            return;
        }
        StringBuilder steps = new StringBuilder();
        int result = evaluateWithParentheses(expr, steps);
        System.out.println(steps);
        System.out.println("Final Result: " + result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== TEXT-BASED CALCULATOR ===");
        while (true) {
            System.out.print("\nEnter expression (or 'exit'): ");
            String expr = sc.nextLine();
            if (expr.equalsIgnoreCase("exit")) break;
            calculateExpression(expr);
        }
        sc.close();
    }
}
