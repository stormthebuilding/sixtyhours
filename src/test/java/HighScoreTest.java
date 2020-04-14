
import Model.DifficultyType;
import Model.Score;
import Model.HighScore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class HighScoreTest {

    /**
     * Unit Test to verify the adding of a High Score
     */
    @Test
    public void testHighScoresAdd() {
        HighScore highScores = UnitTestUtil.setupHighScores();

        //Create a Score and add to High Scores
        Score score = new Score("Frank", 20000, DifficultyType.INSANE);
        highScores.addHighScore(score);

        assertTrue(score.getName().equals("Frank"));
        assertTrue(score.getScore() == 20000);
        assertEquals(1, highScores.getScoresList().size());
        assertEquals("Frank", highScores.getScoresList().get(0).getName());


        //Create 2nd Score and add to High Scores
        score = new Score("Joe", 25000, DifficultyType.HARD);
        highScores.addHighScore(score);
        assertEquals("Joe", highScores.getScoresList().get(1).getName());

        assertTrue(score.getName().equals("Joe"));
        assertTrue(score.getScore() == 25000);
        assertEquals(2, highScores.getScoresList().size());

        // Once Joe is added as above into the High Scores list
        // The Scores would have to be sorted in the allScoresList and 
        // also in the file.
        assertEquals("Joe", highScores.getScoresList().get(0).getName());
        assertEquals("Frank", highScores.getScoresList().get(1).getName());

    }

     /**
     * Unit Test for checking sucessful loading of
     * High Scores from File
     */
    @Test
    public void testLoadScores() {
        HighScore highScores = UnitTestUtil.setupHighScores();

        //Load High Scores from File
        highScores.loadScores("HighScoresTestData.txt");


        assertTrue(highScores.getScoresList().get(0).getName().equals("Frank"));
        assertTrue(highScores.getScoresList().get(0).getScore() == 20000);
        
        assertEquals(5, highScores.getScoresList().size());
    }
    
    @Test
    public void testSaveScores() {}
}