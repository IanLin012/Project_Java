public class Train extends Railway { // extend Railway
    private double fare; // private variable
    public void setFare(double fare) { // fare mutator
        this.fare = fare;
    }
    public double getFare() { // fare accessor
        return fare;
    }
    public Train(String conductorName, int passenger, double fare) { // initial constructor
        super(conductorName, passenger);
        conductorName = getConductorName();
        passenger = getPassenger();
        this.fare = fare;
    }
    public double calcPrice() { // public function calcPrice
        return(getFare() * getPassenger());
    }
}