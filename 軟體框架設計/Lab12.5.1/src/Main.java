public class Main {
    public static void main(String[] args) {
        ShoesFactory sportFactory = new SportShoesFactory();
        Shoes sportShoes = new Shoes();
        sportShoes.makeShoes(sportFactory);

        ShoesFactory leisureFactory = new LeisureShoesFactory();
        Shoes leisureShoes = new Shoes();
        leisureShoes.makeShoes(leisureFactory);

        ShoesFactory leatherFactory = new LeatherShoesFactory();
        Shoes leatherShoes = new Shoes();
        leatherShoes.makeShoes(leatherFactory);
    }
}
