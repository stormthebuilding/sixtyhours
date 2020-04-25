package Model.Enemies;

import Model.Enemy;
import Model.EnemyType;

public class Heavy extends Enemy {
    public Heavy(EnemyType type) {
        super(type);
        speed = 3;
        health = 35;
        damage = 1.5;
    }

}