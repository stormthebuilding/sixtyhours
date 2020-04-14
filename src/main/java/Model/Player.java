package Model;

public class Player implements Serializer {
    private int clipCapacity;
    private int clipRest;
    private int point;
    private int score;


    public Player() {

    }

    //called when the player attacks an enemy
    //check the weapon then edit the corresponding enemy
    public void attack() {

    }

    public int getClipCapacity() {
        return clipCapacity;
    }

    public void setClipCapacity(int clipCapacity) {
        this.clipCapacity = clipCapacity;
    }

    public int getClipRest() {
        return clipRest;
    }

    public void setClipRest(int clipRest) {
        this.clipRest = clipRest;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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