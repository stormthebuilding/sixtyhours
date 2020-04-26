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
    private int coins = 1000000;
    private int currentWave;
    private ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
    private boolean cheatMode = false;

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

        objectCollection.add(player); // add Player object to objectCollection
        objectCollection.add(player.getCurrentWeapon()); // add Weapon object to objectCollection
        objectCollection.add(stronghold); // add Stronghold object to objectCollection

        // Enemy objects are added to objectCollection in spawnEnemy

        objectCollection.add(this); // add World instance to objectCollection



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

    public void addWave() {
        ++currentWave;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public Basic spawnBasic() {
        Basic enemy = new Basic(EnemyType.BASIC);
        enemyList.add(enemy);
        objectCollection.add(enemy);
        return enemy;
    }

    public Advanced spawnAdvanced() {
        Advanced enemy = new Advanced(EnemyType.ADVANCED);
        enemyList.add(enemy);
        objectCollection.add(enemy);
        return enemy;
    }

    public Heavy spawnHeavy() {
        Heavy enemy = new Heavy(EnemyType.HEAVY);
        enemyList.add(enemy);
        objectCollection.add(enemy);
        return enemy;
    }

    public Boss spawnBoss() {
        Boss enemy = new Boss(EnemyType.BOSS);
        enemyList.add(enemy);
        objectCollection.add(enemy);
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

    public boolean isCheatMode() {
        return cheatMode;
    }

    public void setCheatMode(boolean cheatMode) {
        this.cheatMode = cheatMode;
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
                    object = player;
                }
                else if (line.startsWith("STRONGHOLD")) {
                    object = stronghold;
                }
                // else if (line.startsWith("SCORE")) {
                //     object = new Score("", 0, DifficultyType.NORMAL);
                // }
                else if (line.startsWith("WEAPON")) {
                    object = player.getCurrentWeapon();
                }
                else if (line.startsWith("WORLD")) {
                    object = instance();
                }
                else if (line.startsWith("ENEMY")) {
                    object = new Enemy(EnemyType.BASIC);
                }
                object.deserialize(line);
                // adds only Enemy object to objectCollection 
                if (object instanceof Enemy) {
                    objectCollection.add(object);
                    enemyList.add((Enemy) object);
                }
            }
        }
    }

    @Override
    public String serialize() {
        String serialized = "";

        serialized = "WORLD;"+difficulty+","+userName+","+id+","+score+","
        +coins+","+currentWave+","+nextId+","+cheatMode;

        return serialized; 


    }

    @Override
    public void deserialize(String data) {
        String[] splitted = data.split(";")[1].split(",");
        difficulty = splitted[0];
        userName = splitted[1];
        id = Integer.parseInt(splitted[2]);
        score = Integer.parseInt(splitted[3]);
        coins = Integer.parseInt(splitted[4]);
        currentWave = Integer.parseInt(splitted[5]);
        nextId = Integer.parseInt(splitted[6]);
        if (splitted[7].equals("true")) {
            cheatMode = true;
        }
        else {
            cheatMode = false;
        }

    }



}