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
import Model.Weapons.Rifle;
import Model.Weapons.Sniper;

public class World implements Serializer {
    // arguments for the world
    private String difficulty;
    private String userName;
    private int id;
    private int score;
    private int coins;
    private int currentWave;
    private static int nextId;
    private boolean cheatMode = false;

    private ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
    public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

    public Player player = new Player();
    public Stronghold stronghold = new Stronghold();
    public Random rand = new Random();

    // Store methods

    // Stronghold methods

    // Singleton implementation

    // prevent direct instantiation outside this class
    private World() {
        this.id = ++nextId;

        objectCollection.add(player); // add Player object to objectCollection
        objectCollection.add(stronghold); // add Stronghold object to objectCollection
        objectCollection.add(this); // add World instance to objectCollection

        objectCollection.add(player.getCurrentWeapon()); // add original Weapon object to objectCollection
        var weaponList = player.getWeaponList(); // add original Weapon object to weaponList
        weaponList.add(player.getCurrentWeapon());
        player.setWeaponList(weaponList);
        // Additional Weapon objects are added in GameWindow.java when they are created
        // Enemy objects are added in World.java when they are spawned in their
        // respective methods
    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
    }

    public Enemy findEnemy(int id) {
        for (Enemy enemy : enemyList) {
            if (enemy.getId() == id) {
                return enemy;
            }
        }
        return null;
    }

    // Getter and Setter
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
     * 
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
     * https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java
     * Used with modifications. Retrieves data from a text file and places the data
     * into the appropriate entity
     * 
     * @param fileName - The name of the file to extract data from
     */
    public void load(String fileName) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Serializer objectDelayed = null;
            String dataDelayed = "";
            while (!(line = reader.readLine()).startsWith("END")) {
                Serializer object = null;

                if (line.startsWith("PLAYER")) {
                    objectDelayed = player;
                    dataDelayed = line;
                } else if (line.startsWith("STRONGHOLD")) {
                    object = stronghold;
                } else if (line.startsWith("WORLD")) {
                    object = instance();
                } else if (line.startsWith("WEAPON")) {
                    if (line.startsWith("WEAPON;PISTOL")) {
                        object = player.getCurrentWeapon();
                    } else if (line.startsWith("WEAPON;RIFLE")) {
                        object = new Rifle(WeaponType.RIFLE);
                        objectCollection.add(object);
                        var weaponList = player.getWeaponList();
                        weaponList.add((Weapon) object);
                        player.setWeaponList(weaponList);
                    }
                    else if (line.startsWith("WEAPON;SNIPER")) {
                        object = new Sniper(WeaponType.SNIPER);
                        objectCollection.add(object);
                        var weaponList = player.getWeaponList();
                        weaponList.add((Weapon) object);
                        player.setWeaponList(weaponList);
                    }
                }
                else if (line.startsWith("ENEMY")) {
                    if (line.startsWith("ENEMY;BASIC")) {
                        object = new Enemy(EnemyType.BASIC);
                    }
                    else if (line.startsWith("ENEMY;ADVANCED")) {
                        object = new Enemy(EnemyType.ADVANCED);
                    }
                    else if (line.startsWith("ENEMY;HEAVY")) {
                        object = new Enemy(EnemyType.HEAVY);
                    }
                    else if (line.startsWith("ENEMY;BOSS")) {
                        object = new Enemy(EnemyType.BOSS);
                    }
                    objectCollection.add(object);
                    enemyList.add((Enemy) object);
                }
                if (object!=null) {
                    object.deserialize(line);
                }
                
            }
            if (objectDelayed!=null) {
                objectDelayed.deserialize(dataDelayed);
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