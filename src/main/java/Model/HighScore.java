package Model;

import java.util.ArrayList;
import java.util.List;

public class HighScore {
    private List<Score> scoresList = new ArrayList<Score>();
    private int maxNumOfHighScoreEntries = 15;
    private int maxScoresDisplayed = 5;

    /**
     * 
     * @param score
     */
    public void addHighScore(Score score) {
        scoresList.add(score);
    
    }

    /**
     * This Method checks ......
     * @param score - Score to be checked if qualifies to be high
     * @return - True or False
     */
    public boolean findIfScoreQualifiesAsHigh(Score score) {
        return true;
    }

    public void loadScores(String fileName) {

    }
    
    public void saveScores(String fileName) {

    }

    public List<Score> getScoresList() {
        return scoresList;
    }

    public void setScoresList(List<Score> scoresList) {
        this.scoresList = scoresList;
    }

    public int getMaxNumOfHighScoreEntries() {
        return maxNumOfHighScoreEntries;
    }

    public void setMaxNumOfHighScoreEntries(int maxNumOfHighScoreEntries) {
        this.maxNumOfHighScoreEntries = maxNumOfHighScoreEntries;
    }

    public int getMaxScoresDisplayed() {
        return maxScoresDisplayed;
    }

    public void setMaxScoresDisplayed(int maxScoresDisplayed) {
        this.maxScoresDisplayed = maxScoresDisplayed;
    }
    
    // Singleton implementation
    // prevent direct instantiation outside this class
    private HighScore() {

    }

    private static HighScore instance = new HighScore();

    public static HighScore getInstance() {
        return instance;
    }

    public static void reset() {
        instance = new HighScore();
    }
}

