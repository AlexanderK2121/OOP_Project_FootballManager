import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player {
    private String name;
    private int skillLevel;
    private String position;
    private int goalsScored;

    public Player(String name, int skillLevel, String position) {
        this.name = name;
        this.skillLevel = skillLevel;
        this.position = position;
        this.goalsScored = 0;
    }

    public String getName() {
        return name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public String getPosition() {
        return position;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void scoreGoal() {
        goalsScored++;
    }

    public void save(String filename){
        PrintWriter outputStream = null;

        try{
            outputStream = new PrintWriter(new FileOutputStream(filename));
            outputStream.println(name);
            outputStream.println(skillLevel);
            outputStream.println(position);
            outputStream.println(goalsScored);
        }catch(Exception e){
            System.out.println(e);
        }
        outputStream.close();
    }

    public static Player load(String filename){
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new FileInputStream(filename));
            String name = inputStream.nextLine();
            int skillLevel = inputStream.nextInt();
            String position = inputStream.nextLine();
            int goalScored = inputStream.nextInt();
            return new Player(name,skillLevel,position);
        }catch(Exception e){
            System.exit(0);
            return null;
        }
    }

}
