package Model.Weapons;

import Model.Weapon;
import Model.WeaponType;

public class Pistol extends Weapon {
    public Pistol(WeaponType type) {
        super(type);
        magazine = 7;
        damage = 2;
    }
}