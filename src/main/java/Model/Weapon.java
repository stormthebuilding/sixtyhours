package Model;

public class Weapon implements Serializer{

    protected WeaponType type;
    protected int cost;
    protected int damage;
    protected int magazine;

    public Weapon(WeaponType type, int damage) {
        this.type = type;
        this.damage = damage;
        cost = 0;
    }

    //getter and setter
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

    public int getMagazine() {
        return this.magazine;
    }

    public void setMagazine(int magazine) {
        this.magazine = magazine;
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
        String[] splitted = data.split(";")[1].split(",");
        if (splitted[0].equals("PISTOL")) {
            type = WeaponType.PISTOL;
        }
        else if (splitted[0].equals("RIFLE")) {
            type = WeaponType.RIFLE;
        }
        else if (splitted[0].equals("SNIPER")) {
            type = WeaponType.SNIPER;
        }
        else if (splitted[0].equals("MACHINEGUN")) {
            type = WeaponType.MACHINEGUN;
        }
        else if (splitted[0].equals("GRENADE")) {
            type = WeaponType.GRENADE;
        }
        cost = Integer.parseInt(splitted[1]);
        damage = Integer.parseInt(splitted[2]);

    }




}