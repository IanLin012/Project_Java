public class Monster {
    private String name; //名稱
    private String description; //描述
    private String type;  //型態(陸地, 水上, 空中)
    private int hp; //血量

    public Monster(String name, String description, String type, int hp) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void sleep() {
        this.hp = 100;
        System.out.println(name + " 睡了一覺，血量恢復到 " + this.hp);
    }

}
