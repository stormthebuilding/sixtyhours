package Model;

public class Stronghold {
    private int health=100;

    public Stronghold() {

    }
    
    //called when an enemy reaches the stronghold
    //type determines how much health is lost
    public void breached(EnemyType type) {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
}