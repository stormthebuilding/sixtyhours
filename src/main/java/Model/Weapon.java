package Model;

public class Weapon implements Serializer{

    protected WeaponType type;
    protected int cost;
    protected int damage;

    protected Weapon(WeaponType type, int damage) {
        this.type = type;
        this.damage = damage;
        cost = 0;
    }

    public int getCost() {
        return cost;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }
}