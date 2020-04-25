package Model;

public class Weapon implements Serializer{

    protected WeaponType type;
    protected int damage;
    protected int magazine;
    static boolean instantiated = false;

    public Weapon(WeaponType type) {
        this.type = type;
        this.damage = damage;

        // if (instantiated) {
        //     collectObject(); 
        // }
        // instantiated = true;
        
    }

    //getter and setter
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public WeaponType getType() {
        return type;
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
        else if (type == WeaponType.GRENADE) {
            typeToSave = "GRENADE";
        }
        serialized = "WEAPON;"+typeToSave+","+","+damage;
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
        else if (splitted[0].equals("GRENADE")) {
            type = WeaponType.GRENADE;
        }
        damage = Integer.parseInt(splitted[2]);

    }

    public void collectObject() {
        var objectList = World.instance().getObjectCollection();
        objectList.add(this);
        World.instance().setObjectCollection(objectList);
    }




}