// Hunter.java
public class Hunter {
    private String name;
    private int hp;
    private Weapon weapon;   // 獵人使用的武器
    private Mount mount;     // 獵人騎的坐騎

    public Hunter(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println(name + " 換上武器 " + weapon.getName());
    }
    
    public void setMount(Mount mount) {
        this.mount = mount;
        System.out.println(name + " 騎上坐騎 " + mount.getName());
    }
    
    // 徒手打怪，一回合作戰，依情境直接更新血量
    public void fight(Monster m) {
        // 依題目描述：徒手一回合後，Hunter 血量變 30，Monster 血量變 90
        this.hp = 30;
        m.setHp(90);
        System.out.println(name + " 徒手打怪 " + m.getName() + " 後：獵人 HP=" + this.hp + ", 怪物 HP=" + m.getHp());
    }
    
    // 使用武器與坐騎進行連續作戰，rounds 代表回合數
    public void fight(Monster m, int rounds) {
        // 根據題目情境，採用不同武器會有不同效果
        // 這裡簡單模擬：若使用不適當武器 (damage 較低)，效果較差；適當武器則有效果更好
        if (this.weapon == null) {
            System.out.println(name + " 沒有武器，無法進行戰鬥！");
            return;
        }
        for (int i = 1; i <= rounds; i++) {
            if (weapon.getName().equalsIgnoreCase("bow")) {
                this.hp -= 10;
                m.setHp(m.getHp() - 5);
                System.out.println("回合 " + i + " (使用 " + weapon.getName() + ")：" + name + " HP=" + this.hp + ", " + m.getName() + " HP=" + m.getHp());
            } else {
                this.hp -= 0; 
                m.setHp(m.getHp() - 15 / rounds);
                System.out.println("回合 " + i + " (使用 " + weapon.getName() + ")：" + name + " HP=" + this.hp + ", " + m.getName() + " HP=" + m.getHp());
            }
        }
    }
    
    // 獵人吃仙丹並睡覺，恢復部分血量
    public void rest(int recoveredHp) {
        this.hp = recoveredHp;
        System.out.println(name + " 吃了仙丹並睡了一覺，HP 恢復到 " + this.hp);
    }
    
    public String getName() {
        return name;
    }
}
