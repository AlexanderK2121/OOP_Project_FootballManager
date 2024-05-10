import java.util.Scanner;

public class FootballManagerGame {
    private static Scanner scanner = new Scanner(System.in);

    private static Manager createManager() throws ValidationException {
        System.out.println("Enter Manager's Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Manager's Experience Years:");
        int experienceYears = scanner.nextInt();
        if (experienceYears < 0) {
            throw new ValidationException("Experience years cannot be negative.");
        }

        return new Manager(name, experienceYears);
    }

    private static Player createPlayer() throws ValidationException {
        System.out.println("Enter Player's Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Player's Skill Level (1-10):");
        int skillLevel = scanner.nextInt();
        if (skillLevel < 1 || skillLevel > 10) {
            throw new ValidationException("Skill level must be between 1 and 10.");
        }

        System.out.println("Enter Player's Position:");
        String position = scanner.nextLine();

        return new Player(name, skillLevel, position);
    }

    private static Team createTeam() throws ValidationException {
        Manager manager = createManager();
        System.out.println("Enter Team Name:");
        String teamName = scanner.nextLine();
        Team team = new Team(teamName, manager);

        int playerCount = 0;
        while (playerCount < 11) {
            System.out.println("Add a player to the team (Player " + (playerCount + 1) + " of 11):");
            try {
                Player player = createPlayer();
                team.addPlayer(player);
                playerCount++;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Team completed with 11 players.");
        return team;
    }


    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Creating Team 1:");
                Team team1 = createTeam();

                System.out.println("Creating Team 2:");
                Team team2 = createTeam();

                System.out.println("Teams have been created. Would you like to simulate a match between them? (yes/no)");
                String answer = scanner.nextLine();
                if ("yes".equalsIgnoreCase(answer)) {
                    String matchResult = Match.simulateMatch(team1, team2);
                    System.out.println(matchResult);
                }

                System.out.println("Do you want to exit the game? (yes/no)");
                answer = scanner.nextLine();
                if ("yes".equalsIgnoreCase(answer)) {
                    running = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
