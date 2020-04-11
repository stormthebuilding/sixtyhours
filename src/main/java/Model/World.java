package Model;

public class World {
    //arguments for the world
    private String difficulty;
    private String userName;
    private int id;

    private static int nextId;

    //Store methods


    //Stronghold methods (pending)


    // Singleton implementation

    // prevent direct instantiation outside this class
    private World() {
        this.id = ++nextId;
    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
    }

    //Getter and Setter
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}