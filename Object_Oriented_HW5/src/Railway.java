public class Railway {
    private String conductorName; // two private variable
    private int passenger;
    public void setConductorName(String conductorName) { // conductorName mutator
        this.conductorName = conductorName;
    }
    public String getConductorName() { // conductorName accessor
        return conductorName;
    }
    public void setPassenger(int passenger) { // passenger mutator
        this.passenger = passenger;
    }
    public int getPassenger() { // passenger accessor
        return passenger;
    }
    public Railway (String conductorName, int passenger) { // initial constructor
        this.conductorName = conductorName;
        this.passenger = passenger;
    }
    final String getInfo() { // public function getInfo cannot override
        return (getConductorName() + "\t" + getPassenger());
    }
}