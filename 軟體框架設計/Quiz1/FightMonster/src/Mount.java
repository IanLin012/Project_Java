// Mount.java
public class Mount {
    private String name;
    private int speed;
    private int defenseModifier;
    
    public Mount(String name, int speed, int defenseModifier) {
        this.name = name;
        this.speed = speed;
        this.defenseModifier = defenseModifier;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSpeed() {
        return speed;
    }
}
