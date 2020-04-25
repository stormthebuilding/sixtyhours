package Model.Enemies;

import Model.Enemy;
import Model.EnemyType;

public class Boss extends Enemy {
    public Boss(EnemyType type) {
        super(type);
        speed = 2;
        health = 50;
        damage = 2.5;
    }

}