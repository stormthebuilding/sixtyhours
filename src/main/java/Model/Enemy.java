//-----------------------------------------------------------
//File:   (Collaboration)Project.java
//Desc:   This program making a game the player use mouse 
//and keyboard to destroy enemy the robots and defend
//the stronghold.
//----------------------------------------------------------- 
package Model;

public class Enemy implements Serializer{
    private EnemyType type;

    public Enemy(EnemyType type) {

    }

    //called while the enemy moves
    public void moveEnemy() {

    }

    //called when enemy is destroyed
    public void destroyEnemy() {

    }
    //called when enemy is attacking
    public void attack() {

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