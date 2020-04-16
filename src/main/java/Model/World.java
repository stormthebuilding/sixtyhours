package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class World {
    // arguments for the world
    private String difficulty;
    private String userName;
    private int id;

    private static int nextId;

    private ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
    private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    // Store methods

    // Stronghold methods (pending)

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

    public void handleEnemies() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> searchList()));
        timeline.setCycleCount(Animation.INDEFINITE);
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

    public void searchList() {
        for (int i = 0; i < enemyList.size(); ++i) {
            Enemy enemy = enemyList.get(i);
            if (enemy.getHealth() <= 0) {
                enemyList.remove(i);
            }
            else {
                if (enemy.getX() == 250) {
                    // code for enemy attacking goes here
                }
                else {
                    enemy.moveEnemy();
                }
            }
            
        }
    }

    /**
     * 
     * @param fileName - The name of the file to save data to
     */
    public void save(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true); 
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Serializer object : objectCollection) {
            printWriter.println(object.serialize());
        }
        printWriter.println("END;");
        printWriter.close();
    }

    /**
     * https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java Used with modifications.
     * @param fileName - The name of the file to extract data from
     */
    public void load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (!(line = reader.readLine()).startsWith("END")) {
                Serializer object = null;
                if (line.startsWith("PLAYER")) {
                    object = new Player();
                    object.deserialize(line);
                }
                else if (line.startsWith("STRONGHOLD")) {
                    object = new Stronghold();
                    object.deserialize(line);
                }
                else if (line.startsWith("SCORE")) {
                    object = new Score("", 0, DifficultyType.NORMAL);
                    object.deserialize(line);
                }
                else if (line.equals("HIGHSCORE")) {
                    object = HighScore.getInstance();
                    object.deserialize(line);
                }
                else if (line.startsWith("ENEMY")) {
                    object = new Enemy(EnemyType.BASIC);
                    object.deserialize(line);
                }
                else if (line.startsWith("WEAPON")) {
                    object = new Weapon(WeaponType.PISTOL, 0);
                    object.deserialize(line); 
                }
            }
        }
    }
}