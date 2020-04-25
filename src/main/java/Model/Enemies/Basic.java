package Model.Enemies;

import Model.Enemy;
import Model.EnemyType;

public class Basic extends Enemy {

    public Basic(EnemyType type) {
        super(type);
        speed = 4;
        health = 5;
        damage = .5;
    }
}