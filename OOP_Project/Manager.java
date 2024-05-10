import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Manager {
    private String name;
    private int experienceYears;

    public Manager(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int years){
        this.experienceYears = years;
    }

    public void save(String filename){
        PrintWriter outputStream = null;

        try{
            outputStream = new PrintWriter(new FileOutputStream(filename));
            outputStream.println(name);
            outputStream.println(experienceYears);

        }catch(Exception e){
            System.out.println(e);
        }
        outputStream.close();
    }

    public static Manager load(String filename){
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new FileInputStream(filename));
            String name = inputStream.nextLine();
            int experienceYears = inputStream.nextInt();
            return new Manager(name,experienceYears);
        }catch(Exception e){
            System.exit(0);
            return null;
        }
    }

}
