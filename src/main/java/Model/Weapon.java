package Model;

public class Weapon implements Serializer{

    protected WeaponType type;
    protected int damage;
    protected int magazine;
    protected int magazineRest;

    public Weapon(WeaponType type) {
        this.type = type;
     
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
    
    public int getMagazineRest() {
        return magazineRest;
    }

    public void setMagazineRest(int magazineRest) {
        this.magazineRest = magazineRest;
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
 
        serialized = "WEAPON;"+typeToSave+","+damage+","+magazine+","+magazineRest;
        
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

        damage = Integer.parseInt(splitted[1]);
        magazine = Integer.parseInt(splitted[2]);
        magazineRest = Integer.parseInt(splitted[3]);
    

    }


}