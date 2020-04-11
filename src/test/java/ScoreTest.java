import Model.DifficultyType;
import Model.Score;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ScoreTest {

    /**
     * Unit Test to See if a Score Object is created
     */
    @Test
    public void testScore() {
        //Create a Score
        Score score = new Score("Don", 10000, DifficultyType.INSANE);

        assertTrue(score.getName().equals("Don"));
        assertTrue(score.getScore() == 10000);

    }

}