
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


        //Create 2nd Score and add to High Scores
        score = new Score("Joe", 15000, DifficultyType.HARD);
        highScores.addHighScore(score);

        assertTrue(score.getName().equals("Joe"));
        assertTrue(score.getScore() == 15000);
        assertEquals(2, highScores.getScoresList().size());

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
  

}