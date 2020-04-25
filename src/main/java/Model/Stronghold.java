package Model;

public class Stronghold implements Serializer {
    private double health= 500;

    public Stronghold() {
        // collectObject();
    }
    
    //called when an enemy reaches the stronghold
    //type determines how much health is lost
    public void breached(EnemyType type) {

    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String serialize() {
        String serialized = "";
        serialized = "STRONGHOLD;"+health;

        return serialized;
    }

    @Override
    public void deserialize(String data) {
        String[] splitted = data.split(";");
        health = Integer.parseInt(splitted[1]);
    }

    public void collectObject() {
        if (World.instance().getObjectCollection() != null) {
            var objectList = World.instance().getObjectCollection();
            objectList.add(this);
            World.instance().setObjectCollection(objectList);
        }

    }

    
}