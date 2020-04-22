package Model.Enemies;

import Model.Enemy;
import Model.EnemyType;

public class Basic extends Enemy {
    private int speed;
    private int health;
    private double damage;

    public Basic(EnemyType type) {
        super(type);
        speed = 4;
        health = 2;
        damage = .5;
    }
}