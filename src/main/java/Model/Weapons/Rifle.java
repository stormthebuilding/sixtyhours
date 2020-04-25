package Model.Weapons;

import Model.Weapon;
import Model.WeaponType;

public class Rifle extends Weapon {
    public Rifle(WeaponType type) {
        super(type);
        damage = 4;
        magazine = 10;
        magazineRest = magazine;
    }
}