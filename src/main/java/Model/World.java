package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        FileWriter fileWriter = new FileWriter(fileName, true); 
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Object object : objectCollection) {
            if (object instanceof Player) {
                printWriter.println("PLAYER;" + ((Player) object).serialize());
            }
            else if (object instanceof Stronghold) {
                printWriter.println("STRONGHOLD;" + ((Stronghold) object).serialize());
            }
            else if (object instanceof Score) {
                printWriter.println("SCORE;" + ((Score) object).serialize());
            }
            // CHECK WITH RESHI
            // else if (object instanceof HighScore) {
            //     printWriter.println("HIGHSCORE;" + ((HighScore) object).serialize());
            // }
            else if (object instanceof Enemy) {
                printWriter.println("ENEMY;" + ((Enemy) object).serialize());
            }
            else if (object instanceof Weapon) {
                printWriter.println("WEAPON;" + ((Weapon) object).serialize());
            }
            if (objectCollection.indexOf(object) == objectCollection.size() -1 ) {
                printWriter.println("END;");
            }
        }
        printWriter.close();
    }

    /**
     * https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java Used with modifications.
     * @param fileName - The name of the file to extract data from
     */
    public void load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (!(line = reader.readLine()).equals("END")) {
                Object object = null;
                if (line.equals("PLAYER")) {
                    object = new Player();
                    ((Player) object).deserialize(line);
                }
                else if (line.equals("STRONGHOLD")) {
                    object = new Stronghold();
                    ((Stronghold) object).deserialize(line);
                }
                else if (line.equals("SCORE")) {
                    object = new Score("", 0, DifficultyType.NORMAL);
                    ((Score) object).deserialize(line);
                }
                // CHECK WITH RESHI
                // else if (line.equals("HIGHSCORE")) {
                //     object = new HighScore();
                //     ((HighScore) object).deserialize(line);
                // }
                else if (line.equals("ENEMY")) {
                    object = new Enemy(EnemyType.BASIC);
                    ((Enemy) object).deserialize(line);
                }
                else if (line.equals("WEAPON")) {
                    object = new Weapon(WeaponType.PISTOL);
                    ((Weapon) object).deserialize(line); 
                }
            }
        }
    }
}