public class HighSpeedRail extends Railway { // extend Railway
    private int ticket; // private variable
    public void setTicket(int ticket) { // ticket mutator
        this.ticket = ticket;
    }
    public int getTicket() { // ticket accessor
        return ticket;
    }
    public HighSpeedRail(String conductorName, int passenger, int ticket) { // initial constructor
        super(conductorName, passenger);
        conductorName = getConductorName();
        passenger = getPassenger();
        this.ticket = ticket;
    }
    public int calcPrice() { // public function calcPrice
        return(getTicket() * getPassenger());
    }
}