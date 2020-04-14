package Model;

import java.util.ArrayList;
import java.util.List;

public class HighScore implements Serializer {
    private List<Score> scoresList = new ArrayList<Score>();
    private int maxNumOfHighScoreEntries = 15;
    private int maxScoresDisplayed = 5;

    /**
     * Takes a Score as I/P.
     * Loads the High Scores from File into a Data Structure (allScoresList).
     * Adds the incoming Score into the appropriate position in the List.
     * Writes the List back to the File (overwrite the old file).
     * 
     * @param score - Score Object to be added 
     */
    public void addHighScore(Score score) {
        scoresList.add(score);
    
    }

    /**
     * Takes a Score as I/P.
     * Loads the High Scores from File into a Data Structure (allScoresList).
     * This Method checks ......
     * if Incoming Score > than the lowest score in the List. 
     * If so return 'true' or else return 'false'.
     * 
     * @param score - Score to be checked if qualifies to be high
     * @return - True or False
     */
    public boolean findIfScoreQualifiesAsHigh(Score score) {
        return true;
    }

    /**
     * 
     * @param fileName
     */
    public void loadScores(String fileName) {

    }
    
    public void saveScores(String fileName) {

    }

    /**
     * 
     * @return - returns the list of Highscores.
     */
    public List<Score> getScoresList() {
        return scoresList;
    }

    /**
     * Takes the Highscore list as input and sets it as the scoresList.
     * 
     * @param scoresList
     */
    public void setScoresList(List<Score> scoresList) {
        this.scoresList = scoresList;
    }

    /**
     * 
     * @return - returns the maximum number of high score entries (15).
     */
    public int getMaxNumOfHighScoreEntries() {
        return maxNumOfHighScoreEntries;
    }

    /**
     * sets the maximum number of high score entries to 15. 
     * @param maxNumOfHighScoreEntries
     */
    public void setMaxNumOfHighScoreEntries(int maxNumOfHighScoreEntries) {
        this.maxNumOfHighScoreEntries = maxNumOfHighScoreEntries;
    }

    /**
     * 
     * @return - returns the maximum number of scores displayed on the screen at a time (5).
     */
    public int getMaxScoresDisplayed() {
        return maxScoresDisplayed;
    }

    /**
     * Sets the maximum number of scores displayed on the screen at a time to 5.
     * @param maxScoresDisplayed
     */
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

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }
}

