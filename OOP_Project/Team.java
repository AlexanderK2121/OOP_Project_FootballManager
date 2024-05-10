import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    private String teamName;
    private Manager manager;
    private List<Player> players;

    public Team(String teamName, Manager manager) {
        this.teamName = teamName;
        this.manager = manager;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getTeamName() {
        return teamName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setTeamName(String Name){
        this.teamName = Name;
    }

    public void setManager(Manager name){
        this.manager = name;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void save(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(teamName);
            manager.save(filename);
            writer.println(players.size());
            for (Player player : players) {
                player.save(filename);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public static Team load(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            String teamName = scanner.nextLine();
            Manager manager = Manager.load(filename);
            Team team = new Team(teamName, manager);
            int playerCount = scanner.nextInt();
            for (int i = 0; i < playerCount; i++) {
                Player player = Player.load(filename);
                team.addPlayer(player);
            }
            return team;
        } catch (FileNotFoundException e) {
            System.err.println("Error loading team data: " + e.getMessage());
            return null;
        }
    }
}