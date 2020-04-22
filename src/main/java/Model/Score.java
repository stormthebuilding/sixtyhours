package Model;

public class Score implements Serializer, Comparable<Score> {
    private String name;
    private int score;
    private DifficultyType difficultyType;

    /**
     * Constructor for the Score Class
     * 
     * @param name - Name of the Player with high Score
     * @param score - High Score
     * @param difficultyType - Difficulty Type of the High Score
     * @author
     */
    public Score(String name, int score, DifficultyType difficultyType) {
        this.name = name;
        this.score = score;
        this.difficultyType = difficultyType;
        // collectObject();

        
    }

    /**
     * 
     * @return - returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Takes a string(name) as input for the player's name and sets the name to this string.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return - returns the score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Takes an int(score) as input for the player's score and sets the score to this value.
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 
     * @return- returns the DifficultyType of the game (EASY, HARD or INSANE).
     */
    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    /**
     * Sets the DifficultyType of the game to EASY, HARD or INSANE as selected by the player.
     * 
     * @param difficultyType
     */
    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }

    /** 
     * Parses the Score Object to String
     * @return String
     */
    @Override
    public String toString() {
        return "Score [difficultyType=" + difficultyType + ", name=" + name + ", score=" + score + "]";
    }

    @Override
    public String serialize() {
        String serialized = "";
        String difficultyToSave = "";
        if (difficultyType == DifficultyType.NORMAL) {
            difficultyToSave = "NORMAL";
        }
        else if (difficultyType == DifficultyType.HARD) {
            difficultyToSave = "HARD";
        }
        else if (difficultyType == DifficultyType.INSANE) {
            difficultyToSave = "INSANE";
        }
        serialized = "SCORE;"+name+","+score+","+difficultyToSave;
       
        return serialized;
    }

    @Override
    public void deserialize(String data) {
        String[] splitted = data.split(";")[1].split(",");
        name = splitted[0];
        score = Integer.parseInt(splitted[1]);
        if (splitted[2].equals("NORMAL")) {
            difficultyType = DifficultyType.NORMAL;
        }
        else if (splitted[2].equals("HARD")) {
            difficultyType = DifficultyType.HARD;
        }
        else if (splitted[2].equals("INSANE")) {
            difficultyType = DifficultyType.INSANE;
        }
    }

    public void collectObject() {
        var objectList = World.instance().getObjectCollection();
        objectList.add(this);
        World.instance().setObjectCollection(objectList);
    }
    
    public int compareTo(Score scoreObj) {
        int scoreToCompare = ((Score) scoreObj).getScore(); // same type object
        if (this.score > scoreToCompare)
         return 1;
        else if (this.score < scoreToCompare)
         return -1;
        else
         return 0;
}



}