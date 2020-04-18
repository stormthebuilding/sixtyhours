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
    private double y;
    private double x;
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

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }

    

    @Override
    public String serialize() {
        String serialized = "";
        String typeToSave = "";
        if (type == EnemyType.BASIC) {
            typeToSave = "BASIC";
        }
        else if (type == EnemyType.ADVANCED) {
            typeToSave = "ADVANCED";
        }
        else if (type == EnemyType.HEAVY) {
            typeToSave = "HEAVY";
        }
        else if (type == EnemyType.BOSS) {
            typeToSave = "BOSS";
        }
        serialized = "ENEMY;"+typeToSave+","+x+","+y+","+speed+","+health+","+damage;
        return serialized;
    }

    @Override
    public void deserialize(String data) {
        String[] splitted = data.split(";")[1].split(",");
        if (splitted[0].equals("BASIC")) {
            type = EnemyType.BASIC;
        }
        else if (splitted[0].equals("ADVANCED")) {
            type = EnemyType.ADVANCED;
        }
        else if (splitted[0].equals("HEAVY")) {
            type = EnemyType.HEAVY;
        }
        else if (splitted[0].equals("BOSS")) {
            type = EnemyType.BOSS;
        }
        x = Integer.parseInt(splitted[1]);
        y = Integer.parseInt(splitted[2]);
        speed = Integer.parseInt(splitted[3]);
        health = Integer.parseInt(splitted[4]);
        damage = Integer.parseInt(splitted[5]);
        

    }











}