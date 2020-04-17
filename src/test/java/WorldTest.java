import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import Model.DifficultyType;
import Model.Enemy;
import Model.EnemyType;
import Model.Player;
import Model.Score;
import Model.Stronghold;
import Model.Weapon;
import Model.WeaponType;
import Model.World;

public class WorldTest {
    @Test
    public void testSave_SaveToFile_Pass() throws IOException {
        // start with a new world instance
        World.reset();
        World instance = World.instance();
        // add entities to the world
        var objectCollection = instance.getObjectCollection();
        objectCollection.add(new Player());
        objectCollection.add(new Stronghold());
        objectCollection.add(new Score("Caleb", 10, DifficultyType.HARD));
        objectCollection.add(new Enemy(EnemyType.ADVANCED));
        objectCollection.add(new Weapon(WeaponType.SNIPER, 2));
        instance.setObjectCollection(objectCollection);
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
                assertEquals("PLAYER;CLIPSIZE,CLIPREST,POINTS,SCORE", line);
                line = reader.readLine();
                assertEquals("STRONGHOLD;HEALTH", line);
                line = reader.readLine();
                assertEquals("SCORE;NAME,SCORE,DIFFICULTY", line);
                line = reader.readLine();
                assertEquals("ENEMY;TYPE", line);
                line = reader.readLine();
                assertEquals("WEAPON;TYPE", line);
                line = reader.readLine();
                assertEquals("END;", line);
        }
    }

    @Test 
    public void testLoad_LoadFromFile_Pass() {
        // start with a new world instance
        World.reset();
        World instance = World.instance();
        // call load method
        try {
            instance.load("testLoad.txt");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        var objectCollection = instance.getObjectCollection();
        
        // check player 
        var player = objectCollection.get(0);
        assertEquals(3, ((Player) player).getClipCapacity());
        assertEquals(2, ((Player) player).getClipRest());
        assertEquals(100, ((Player) player).getPoint());
        assertEquals(1500, ((Player) player).getScore());
        // check stronghold
        var stronghold = objectCollection.get(1);
        assertEquals(1000, ((Stronghold) stronghold).getHealth());
        // check score
        var score = objectCollection.get(2);
        assertEquals("Caleb", ((Score) score).getName());
        assertEquals(1500, ((Score) score).getScore());
        assertEquals(DifficultyType.INSANE, ((Score) score).getDifficultyType());
        // check enemy
        var enemy = objectCollection.get(3);
        assertEquals(EnemyType.HEAVY, ((Enemy) enemy).getType());
        // check weapon
        var weapon = objectCollection.get(4);
        assertEquals(WeaponType.GRENADE, ((Weapon) weapon).getType());
        }
}