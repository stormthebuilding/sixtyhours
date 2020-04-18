//-----------------------------------------------------------
//File:   (Collaboration)Project.java
//Desc:   This program making a game the player use mouse 
//and keyboard to destroy enemy the robots and defend
//the stronghold.
//----------------------------------------------------------- 
package Model;

import java.util.Random;

public class Enemy implements Serializer {
    private EnemyType type;
    private int id;
    private int y;
    private int x;
    private int speed;
    private int health;
    private int damage;

    private static int nextId;

    public Enemy(EnemyType type) {
        this.type = type;
        y = World.instance().rand.nextInt(900);//section of left side of window we want the enemies to spawn
        x = 0;
        speed = 7;
        health = 10;
        damage = 10;
        id = ++nextId;
        // if (type == EnemyType.BASIC) {
        //     speed = 7;
        //     health = 10;
        //     damage = 10;
        // }
        // else if (type == EnemyType.ADVANCED) {
        //     speed = 10;
        //     health = 20;
        //     damage = 15;
        // }
        // else if (type == EnemyType.HEAVY) {
        //     speed = 5;
        //     health = 35;
        //     damage = 30;
        // }
        // else if (type == EnemyType.BOSS) {
        //     speed = 2;
        //     health = 50;
        //     damage = 50;
        // }
    }

    //called while the enemy moves
    public void moveEnemy() {
        x += speed;
    }

    //called when enemy is damaged
    public int damageEnemy(int damage) {
        health -= damage;
        return health;
    }
    //called when enemy is attacking
    public int attack() {
        return damage;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
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

    public EnemyType getType() {
        return type;
    }




}