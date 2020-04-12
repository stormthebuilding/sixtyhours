package Model;

public class Score implements Serializer {
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
    }

    /**
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 
     * @return DifficultyType
     */
    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    /**
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }
    
}