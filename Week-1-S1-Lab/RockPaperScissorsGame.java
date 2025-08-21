import java.util.*;

public class RockPaperScissorsGame {
    public static String getComputerChoice() {
        int choice = (int) (Math.random() * 3);
        if (choice == 0) return "rock";
        else if (choice == 1) return "paper";
        else return "scissors";
    }

    public static String getWinner(String user, String comp) {
        if (user.equals(comp)) return "Draw";
        if ((user.equals("rock") && comp.equals("scissors")) ||
            (user.equals("paper") && comp.equals("rock")) ||
            (user.equals("scissors") && comp.equals("paper"))) {
            return "User";
        } else {
            return "Computer";
        }
    }

    public static String[][] calculateStats(int userWins, int compWins, int totalGames) {
        String[][] stats = new String[3][2];
        stats[0][0] = "User Wins";
        stats[0][1] = String.valueOf(userWins);
        stats[1][0] = "Computer Wins";
        stats[1][1] = String.valueOf(compWins);
        stats[2][0] = "Winning % (User vs Computer)";
        double userPerc = (userWins * 100.0) / totalGames;
        double compPerc = (compWins * 100.0) / totalGames;
        stats[2][1] = String.format("%.2f%% vs %.2f%%", userPerc, compPerc);
        return stats;
    }

    public static void displayResults(List<String[]> gameResults, String[][] stats) {
        System.out.println("\nGame Results:");
        System.out.printf("%-10s %-12s %-12s %-10s\n", "Game", "User", "Computer", "Winner");
        for (int i = 0; i < gameResults.size(); i++) {
            String[] res = gameResults.get(i);
            System.out.printf("%-10d %-12s %-12s %-10s\n", i + 1, res[0], res[1], res[2]);
        }
        System.out.println("\nStatistics:");
        for (String[] row : stats) {
            System.out.printf("%-25s %s\n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of games: ");
        int n = sc.nextInt();
        sc.nextLine();

        int userWins = 0, compWins = 0;
        List<String[]> gameResults = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter your choice (rock/paper/scissors): ");
            String userChoice = sc.nextLine().toLowerCase();
            String compChoice = getComputerChoice();
            String winner = getWinner(userChoice, compChoice);
            if (winner.equals("User")) userWins++;
            else if (winner.equals("Computer")) compWins++;
            gameResults.add(new String[]{userChoice, compChoice, winner});
        }

        String[][] stats = calculateStats(userWins, compWins, n);
        displayResults(gameResults, stats);

        sc.close();
    }
}
