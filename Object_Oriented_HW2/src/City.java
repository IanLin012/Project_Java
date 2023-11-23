class City {
    String cityName; // two variable
    int cityPopulation;

    City(String cityName, int cityPopulation) { // initial constructor
        this.cityName = cityName;
        this.cityPopulation = cityPopulation;
    }

    static double convertPopulationUnit(double num) { // convert cityPopulation function
        return num/10000;
    }
}