package Model.Weapons;

import Model.Weapon;
import Model.WeaponType;

public class Sniper extends Weapon {
    public Sniper(WeaponType type) {
        super(type);
        damage = 20;
        magazine = 1;
        magazineRest = magazine;
    }
}