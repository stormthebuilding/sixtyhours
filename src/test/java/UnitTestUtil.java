import Model.HighScore;

public class UnitTestUtil {
    public static HighScore setupHighScores() {
        HighScore.reset();
        return HighScore.getInstance();
    }
}