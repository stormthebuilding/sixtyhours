package Model.Enemies;

import Model.Enemy;
import Model.EnemyType;

public class Advanced extends Enemy {
    public Advanced(EnemyType type) {
        super(type);
        speed = 6;
        health = 10;
        damage = .8;
    }

}