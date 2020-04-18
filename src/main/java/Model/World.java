package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class World {
    // arguments for the world
    private String difficulty;
    private String userName;
    private int id;
    private int currentWave;

    private static int nextId;

    private ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
    String file = "SavedGame.txt";
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    public Random rand = new Random();

    // Store methods

    // Stronghold methods (pending)
    public Stronghold stronghold = new Stronghold();

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
        for (int i = 0; i < enemyList.size(); ++i) {
            Enemy enemy = enemyList.get(i);
            if (enemy.getHealth() <= 0) {
                enemyList.remove(i);
            }
            else {
                if (enemy.getX() == 870) {
                    int damage = enemy.attack();
                    int health = stronghold.getHealth();
                    stronghold.setHealth(health - damage);
                }
                else {
                    enemy.moveEnemy();
                }
            }
        }
    }

    public Enemy findEnemy(int id) {
        for (Enemy enemy: enemyList) {
            if (enemy.getId() == id) {
                return enemy;
            }
        }
        return null;
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

    public Enemy spawnEnemy() {
        Enemy enemy = new Enemy(EnemyType.BASIC);
        enemyList.add(enemy);
        return enemy;
    }

    /**
     * Saves the existing game objects to a text file
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
     * Retrieves data from a text file and places the data into the appropriate entity
     * @param fileName - The name of the file to extract data from
     */
    public void load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (!(line = reader.readLine()).startsWith("END")) {
                Serializer object = null;
                if (line.startsWith("PLAYER")) {
                    object = new Player();
                }
                else if (line.startsWith("STRONGHOLD")) {
                    object = new Stronghold();
                }
                else if (line.startsWith("SCORE")) {
                    object = new Score("", 0, DifficultyType.NORMAL);
                }
                else if (line.startsWith("ENEMY")) {
                    object = new Enemy(EnemyType.BASIC);
                }
                else if (line.startsWith("WEAPON")) {
                    object = new Weapon(WeaponType.PISTOL, 0);
                }
                object.deserialize(line);
                objectCollection.add(object);
            }
        }
    }

    public ArrayList<Serializer> getObjectCollection() {
        return objectCollection;
    }

    public void setObjectCollection(ArrayList<Serializer> objectCollection) {
        this.objectCollection = objectCollection;
    }
}