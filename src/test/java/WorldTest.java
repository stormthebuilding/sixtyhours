import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import Model.World;

public class WorldTest {
    @Test
    public void testSave_SaveToFile_Pass() throws IOException {
        World instance = World.instance();
        try {
            instance.save("save.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
            String line;
            String[] testString;
            while (!(line = reader.readLine()).equals("END")) {
                if (line.equals("PLAYER")) {
                    testString = line.split(",");
                    assertNotEquals(testString[1], null);
                }
                else if (line.equals("STRONGHOLD")) {
                    String[] tempString = line.split(",");
                    assertNotEquals(tempString[1], null);
                }
                else if (line.equals("SCORE")) {
                    String[] tempString = line.split(",");
                    assertNotEquals(tempString[1], null);
                }
                else if (line.equals("ENEMY")) {
                    String[] tempString = line.split(",");
                    assertNotEquals(tempString[1], null);
                }
                else if (line.equals("WEAPON")) {
                    String[] tempString = line.split(",");
                    assertNotEquals(tempString[1], null);
                }

            }
        }
    }

    @Test 
    public void testLoad_LoadFromFile_Pass() {
        World instance = World.instance();
        try {
            instance.save("load.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            instance.load("load.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}