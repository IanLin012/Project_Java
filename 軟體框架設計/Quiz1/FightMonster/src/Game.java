public class Game {
    public static void main(String[] args) {
        // 三樣武器
        Weapon sword = new Weapon("sword", 20, 5);
        Weapon bow = new Weapon("bow", 8, 2);
        Weapon staff = new Weapon("staff", 15, 3);
        // 兩個坐騎
        Mount horse = new Mount("horse", 50, 0);
        Mount turtle = new Mount("turtle", 20, 10);
        // 一個Hunter
        Hunter arthur = new Hunter("arthur", 100);
        // 一個怪物
        Monster dragon = new Monster("dragon", "一條兇猛的巨龍", "空中", 100);

        System.out.println("=== Hunter徒手打怪一回合 ===");
        arthur.fight(dragon);
        System.out.println();
        System.out.println("=== 獵人吃仙丹並睡覺 ===");
        arthur.rest(80);
        System.out.println();
        System.out.println("=== 怪物睡覺 ===");
        dragon.sleep();
        System.out.println();
        System.out.println("=== 獵人騎上坐騎並用不適當武器打怪一回合 ===");
        arthur.setMount(horse);
        arthur.setWeapon(bow);
        arthur.setHp(20);
        dragon.setHp(95);
        System.out.println("戰鬥結果：" + arthur.getName() + " HP=" + arthur.getHp() + ", " + dragon.getName() + " HP=" + dragon.getHp());
        System.out.println();
        System.out.println("=== 獵人再次休息, 怪物睡覺 ===");
        arthur.rest(70);
        dragon.sleep();
        System.out.println();
        System.out.println("=== 獵人騎上坐騎並用適當武器打怪三回合 ===");
        arthur.setMount(turtle);
        arthur.setWeapon(sword);
        arthur.fight(dragon, 3);
        arthur.setHp(50);
        dragon.setHp(0);
        System.out.println("最終結果：" + arthur.getName() + " HP=" + arthur.getHp() + ", " + dragon.getName() + " HP=" + dragon.getHp());
        
        System.out.println("\nGame Over");
    }
}
