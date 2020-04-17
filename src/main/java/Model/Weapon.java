package Model;

public class Weapon implements Serializer{

    protected WeaponType type;
    protected int cost;
    protected int damage;

    public Weapon(WeaponType type, int damage) {
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

    public WeaponType getType() {
        return type;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    @Override
    public String serialize() {
        String serialized = "";
        String typeToSave = "";
        if (type == WeaponType.PISTOL) {
            typeToSave = "PISTOL";
        }
        else if (type == WeaponType.RIFLE) {
            typeToSave = "RIFLE";
        }
        else if (type == WeaponType.SNIPER) {
            typeToSave = "SNIPER";
        }
        else if (type == WeaponType.MACHINEGUN) {
            typeToSave = "MACHINEGUN";
        }
        else if (type == WeaponType.GRENADE) {
            typeToSave = "GRENADE";
        }
        serialized = "WEAPON;"+typeToSave+","+cost+","+damage;
        return serialized;
    }

    @Override
    public void deserialize(String data) {
        // TODO Auto-generated method stub

    }



}