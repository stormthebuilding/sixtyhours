package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class World  {
    //arguments for the world
    private String difficulty;
    private String userName;
    private int id;

    private static int nextId;

    private ArrayList<Object> objectCollection = new ArrayList<Object>();

    //Store methods


    //Stronghold methods (pending)


    // Singleton implementation

    // prevent direct instantiation outside this class
    private World() {
        this.id = ++nextId;
    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
    }

    //Getter and Setter
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @param fileName - The name of the file to save data to
     */
    public void save(String fileName) throws IOException {

        FileWriter fileWriter = new FileWriter("filePath", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for (Object object : objectCollection) {
         
            if (object instanceof Enemy) {
                printWriter.print(((Enemy) object).serialize());
            }
            else if (object instanceof HighScore) {
                printWriter.print(((HighScore) object).serialize());
            }
            else if (object instanceof Player) {
                printWriter.print(((Player) object).serialize());
            }
            else if (object instanceof Score) {
                printWriter.print(((Score) object).serialize());
            }
            else if (object instanceof Stronghold) {
                printWriter.print(((Stronghold) object).serialize());
            }
            // else if (object instanceof Weapon) {
            //     printWriter.print((Weapon) object).serialize();
            // }
        }
        printWriter.close();
    }

    /**
     * 
     * @param fileName - The name of the file to extract data from
     */
    public void load(String fileName) {

    }
}