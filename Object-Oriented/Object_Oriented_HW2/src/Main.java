public class Main {
    public static void main(String[] args) {
        City city = new City("Taichung",12345678);

        System.out.println("轉換前"); // before convert
        System.out.println("City:" + city.cityName);
        System.out.println("Population:" + city.cityPopulation);

        System.out.println("轉換後"); // after convert
        System.out.println("City:" + city.cityName);
        System.out.printf("Population:%.2f", City.convertPopulationUnit(city.cityPopulation));
    }
}