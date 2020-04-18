package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScore implements Serializer {
    private List<Score> scoresList = new ArrayList<Score>();
    private int maxNumOfHighScoreEntries = 15;
    private int maxScoresDisplayed = 5;

    /**
     * Takes a Score as I/P.
     * Loads the High Scores from File into a Data Structure (scoresList).
     * Adds the incoming Score into the appropriate position in the List.
     * Writes the List back to the File (overwrite the old file).
     * 
     * @param score - Score Object to be added 
     * @throws IOException
     */
    public void addHighScore(Score score) throws IOException {
        String fileName = "src/main/resources/SaveScoresData.txt";
        File fileObj = new File(fileName);
        if (!fileObj.exists())
            fileObj.createNewFile();
        else
            this.loadScores(fileName);

        scoresList.add(score);
        Collections.sort(scoresList, Collections.reverseOrder());
        this.saveScores(fileName);
    
    }

    /**
     * Takes a Score as I/P. If the List size is < 20 then the Score Qualifies else
     * if Incoming Score > than the lowest score in the List (last element) If so
     * return 'true' or else return 'false'.
     * 
     * @param score - checks if the score qualifies to be a Highscore.
     * @return - True or False
     */
    public boolean findIfScoreQualifiesAsHigh(Score score) {
        return true;
    }

    /**
     * 
     * @param fileName - File name to load high scores from.
     * @throws IOException
     */
    public void loadScores(String fileName) throws IOException {

        // 1. Create the File Object to identify the file to work with
        File myFileObj = new File(fileName);
        // 2. Create the FileReader object from the File object to work with a character Stream
        // 3. Create the BufferedReader object to read through the stream by passing the FileReader object
        BufferedReader buffObj = new BufferedReader(new FileReader(myFileObj));

        // 4. Clear the Scores list
        scoresList.clear();
        // 5. Read through the BufferedReader object line by line
        String line = buffObj.readLine();
        while (line != null) {
        // 6. Parse the individual items in the line into fields
        String[] allFields = line.split(",");
        String name = allFields[0];
        int score = Integer.parseInt(allFields[1]);
        DifficultyType diffType = DifficultyType.valueOf(allFields[2]);
        // 7. Populate the High Scores List with the data from each line
        scoresList.add(new Score(name,score,diffType));
        //Score scr = new Score(name,score,diffType);
        //System.out.println(scr.toString());
        line = buffObj.readLine();
        }
        // Close the File
        buffObj.close();

    }
    
    /**
     * 1. Create the File Object to identify the file to write to
     * 2. Create the FileWriter object for the File object to work with a character Stream
     * 3. Create the BufferedWriter object to write into the stream by passing the FileWriter object
     *    connected to the File object. 
     * 4. Loop through the Scores List and Write each line to PrintWriter Stream 
     * 5. Close the file
     * @param fileName - File to Write to
     */
    public void saveScores(String fileName){
        // Create File Object
        File outFile = new File(fileName);
        try {
            PrintWriter outStream = new PrintWriter(new BufferedWriter(new FileWriter(outFile)), true);

            for (int i = 0; i < scoresList.size(); ++i) {
                String line = scoresList.get(i).getName() + "," + scoresList.get(i).getScore() + "," + scoresList.get(i).getDifficultyType();
                outStream.println(line);
            }
            outStream.close();

        } catch (IOException e) {
        System.out.println("I/O Error");
        System.exit(0);
        }
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
        // Not needed?
        return null;
    }

    @Override
    public void deserialize(String data) {
        // Not needed?

    }
}

