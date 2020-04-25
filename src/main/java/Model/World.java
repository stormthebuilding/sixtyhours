package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import Model.Enemies.Advanced;
import Model.Enemies.Basic;
import Model.Enemies.Boss;
import Model.Enemies.Heavy;

public class World implements Serializer {
    // arguments for the world
    private String difficulty;
    private String userName;
    private int id;
    private int score;
    private int coins;
    private int currentWave;
    private ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
    public Player player = new Player();
    
    public Stronghold stronghold = new Stronghold();

    private static int nextId;

    
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    public Random rand = new Random();

    // Store methods

    // Stronghold methods
    

    // Singleton implementation

    // prevent direct instantiation outside this class
    private World() {
        this.id = ++nextId;
        objectCollection.add(player);
        objectCollection.add(player.getCurrentWeapon());
        objectCollection.add(stronghold);
        objectCollection.add(this);

    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
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

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void subtractCoins(int coins) {
        this.coins -= coins;
    }

    public int getCoins() {
        return coins;
    }

    public Basic spawnBasic() {
        Basic enemy = new Basic(EnemyType.BASIC);
        enemyList.add(enemy);
        return enemy;
    }

    public Advanced spawnAdvanced() {
        Advanced enemy = new Advanced(EnemyType.ADVANCED);
        enemyList.add(enemy);
        return enemy;
    }

    public Heavy spawnHeavy() {
        Heavy enemy = new Heavy(EnemyType.HEAVY);
        enemyList.add(enemy);
        return enemy;
    }

    public Boss spawnBoss() {
        Boss enemy = new Boss(EnemyType.BOSS);
        enemyList.add(enemy);
        return enemy;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Serializer> getObjectCollection() {
        return objectCollection;
    }

    public void setObjectCollection(ArrayList<Serializer> objectCollection) {
        this.objectCollection = objectCollection;
    }

    /**
     * Saves the existing game objects to a text file
     * @param fileName - The name of the file to save data to
     */
    public void save(String fileName) throws IOException {
        PrintWriter cleaner = new PrintWriter(fileName);
        cleaner.print("");
        cleaner.close();
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
                    object = new Weapon(WeaponType.PISTOL);
                }
                object.deserialize(line);
                // objectCollection.add(object);
            }
        }
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }

}