package eticketing;

public class event {

    private int id;
    private avenue location;
    private int maxNoTickets, ticketsSold;
    private String eventName;
    private static int lastID = 0;

    event(avenue location, String eventName){
        lastID++;
        this.id = lastID;
        this.location = location;
        this.eventName = eventName;
    }

    void increaseTicketsSold(int amount){
        this.ticketsSold+=amount;
    }

}
