//-----------------------------------------------------------
//File:   Stronghold.java
//Desc:   This class holds the Stronghold object and its methods
//save the health point of the stronghold
//----------------------------------------------------------- 

package Model;

public class Stronghold implements Serializer {
    private double health= 500;

    public Stronghold() {
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

    /**
     * Takes instance variables 
     * @return A comma-delimited String containing the variables
     */
    @Override
    public String serialize() {
        
        String serialized = "";
        serialized = "STRONGHOLD;"+health;

        return serialized;
    }

    /**
     * Sets instance variables according to the data from the specified file
     * @param  - The file to extract data from
     */
    @Override
    public void deserialize(String data) {

        String[] splitted = data.split(";");
        health = Double.parseDouble(splitted[1]);
    }

    
}