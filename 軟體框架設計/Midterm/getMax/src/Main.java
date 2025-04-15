/**
 * 測試 Player & House getMax
 */
class Main {
    public static void main(String[] args) {
        Player[] players = {
                new Player("Alex", 80),
                new Player("Bob", 40),
                new Player("Curry", 70),
                new Player("David", 95),
                new Player("Eden", 55)
        };
        House[] houses = {
                new House("A", 4),
                new House("B", 8),
                new House("C", 3),
                new House("D", 6),
                new House("E", 5)
        };

        Player maxPlayer = (Player) GeneralMax.getMax(players);
        House maxHouse = (House) GeneralMax.getMax(houses);

        System.out.println("Max player: " + maxPlayer);
        System.out.println("Max house: " + maxHouse);
    }
}
