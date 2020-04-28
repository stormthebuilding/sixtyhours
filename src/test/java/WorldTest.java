import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

import Model.DifficultyType;
import Model.Enemy;
import Model.EnemyType;
import Model.Player;
import Model.Score;
import Model.Serializer;
import Model.Stronghold;
import Model.Weapon;
import Model.WeaponType;
import Model.World;

// -------------------------------- BOTH TESTS ARE NOT CAUGHT UP WITH CODE CHANGES ------------------------------------

public class WorldTest {
    @Test
    public void testSave_SaveToFile_Pass() throws IOException {
        // start with a new world 
        World.reset();
        World instance = World.instance();
        ArrayList<Serializer> objectCollection = new ArrayList<Serializer>();
        // add player
        Player player = new Player();
        // player.setClipCapacity(10);
        // player.setClipRest(5);
        player.setPoint(100);
        player.setScore(1000);
        objectCollection.add(player);
        // add stronghold
        Stronghold stronghold = new Stronghold();
        stronghold.setHealth(99.0);
        objectCollection.add(stronghold);
        // add score
        Score score = new Score("Moe", 1000, DifficultyType.INSANE);
        // objectCollection.add(score);
        // add enemy
        Enemy enemy = new Enemy(EnemyType.BASIC);
        enemy.setY(200);
        objectCollection.add(enemy);
        // add weapon
        // Weapon weapon = new Weapon(WeaponType.SNIPER, 4);
        // weapon.setCost(40);
        // objectCollection.add(weapon);
        instance.setObjectCollection(objectCollection);
        // clear current contents of text file
        PrintWriter writer = new PrintWriter("testSave.txt");
        writer.print("");
        writer.close();
        // call save method
        try {
            instance.save("testSave.txt");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        // check text file contents against expected data
        try (BufferedReader reader = new BufferedReader(new FileReader("testSave.txt"))) {
            String line;
                line = reader.readLine();
                assertEquals("PLAYER;10,5,100,1000,PISTOL,0,2", line);
                line = reader.readLine();
                assertEquals("STRONGHOLD;99.0", line);
                line = reader.readLine();
                assertEquals("SCORE;Moe,1000,INSANE", line);
                line = reader.readLine();
                assertEquals("ENEMY;BASIC,0.0,200.0,7,10,0.5", line);
                line = reader.readLine();
                assertEquals("WEAPON;SNIPER,40,4", line);
                line = reader.readLine();
                assertEquals("END;", line);
        }
    }

    @Test 
    public void testLoad_LoadFromFile_Pass() throws FileNotFoundException {
        // start with a new world instance
        World.reset();
        World instance = World.instance();
        // fill text file with data
        PrintWriter writer = new PrintWriter("testLoad.txt");
        writer.println("PLAYER;20,10,200,2000,PISTOL,0,2");
        writer.println("STRONGHOLD;1");
        writer.println("SCORE;Larry,2000,NORMAL");
        writer.println("ENEMY;HEAVY,0,100,5,35,30");
        writer.println("WEAPON;RIFLE,20,3");
        writer.println("END;");
        writer.close();
        // call load method
        try {
            instance.load("testLoad.txt");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        var objectCollection = instance.getObjectCollection();
        
        // check player 
        var player = objectCollection.get(0);
        // assertEquals(20, ((Player) player).getClipCapacity());
        // assertEquals(10, ((Player) player).getClipRest());
        assertEquals(200, ((Player) player).getPoint());
        assertEquals(2000, ((Player) player).getScore());
        assertEquals(WeaponType.PISTOL, ((Player) player).getCurrentWeapon().getType());
        // assertEquals(0, ((Player) player).getCurrentWeapon().getCost());
        assertEquals(2, ((Player) player).getCurrentWeapon().getDamage()); 
        // check stronghold
        var stronghold = objectCollection.get(1);
        assertEquals(1.0, ((Stronghold) stronghold).getHealth(), 0.0);
        // check score
        var score = objectCollection.get(2);
        assertEquals("Larry", ((Score) score).getName());
        assertEquals(2000, ((Score) score).getScore());
        assertEquals(DifficultyType.NORMAL, ((Score) score).getDifficultyType());
        // check enemy
        var enemy = objectCollection.get(3);
        assertEquals(EnemyType.HEAVY, ((Enemy) enemy).getType());
        assertEquals(0.0, ((Enemy) enemy).getX(), 0.0);
        assertEquals(100.0, ((Enemy) enemy).getY(), 0.0);
        assertEquals(5, ((Enemy) enemy).getSpeed());
        assertEquals(35, ((Enemy) enemy).getHealth());
        assertEquals(30.0, ((Enemy) enemy).getDamage(), 0.0);
        // check weapon
        var weapon = objectCollection.get(4);
        assertEquals(WeaponType.RIFLE, ((Weapon) weapon).getType());
        // assertEquals(20, ((Weapon) weapon).getCost());
        assertEquals(3, ((Weapon) weapon).getDamage());
        }
}