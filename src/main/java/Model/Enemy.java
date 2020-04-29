//-----------------------------------------------------------
//File:   (Collaboration)Project.java
//Desc:   This program making a game the player use mouse 
//and keyboard to destroy enemy the robots and defend
//the stronghold.
//----------------------------------------------------------- 
package Model;

public class Enemy implements Serializer {
    protected EnemyType type;
    protected int id;
    protected double y;
    protected double x;
    protected int speed;
    protected int health;
    protected double damage;

    private static int nextId;

    public Enemy(EnemyType type) {
        this.type = type;
        y = World.instance().rand.nextInt(780 - 330 + 1) + 301;//section of left side of window we want the enemies to spawn
        x = 0;
        id = ++nextId;
    }

    //called when enemy is damaged
    public int damageEnemy(int damage) {
        health -= damage;
        return health;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }

    public double getDamage() {
        return damage;
    }

    public EnemyType getType() {
        return type;
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

        serialized = "ENEMY;"+typeToSave+","+id+","+x+","+y+","+speed+","+health+","+damage+","+nextId;

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
        
        id = Integer.parseInt(splitted[1]);
        x = Double.parseDouble(splitted[2]);
        y = Double.parseDouble(splitted[3]);
        speed = Integer.parseInt(splitted[4]);
        health = Integer.parseInt(splitted[5]);
        damage = Double.parseDouble(splitted[6]);
        nextId = id = Integer.parseInt(splitted[7]);
        

    }
}