// Weapon.java
public class Weapon {
    private String name;
    private int damage;
    private int mobilityReduction;

    public Weapon(String name, int damage, int mobilityReduction) {
        this.name = name;
        this.damage = damage;
        this.mobilityReduction = mobilityReduction;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDamage() {
        return damage;
    }
}
