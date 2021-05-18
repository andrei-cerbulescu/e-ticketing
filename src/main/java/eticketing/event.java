package eticketing;

import java.util.Vector;

public class event {

    private int id;
    private avenue location;
    private int maxNoTickets, ticketsSold;
    private String eventName;
    private static int lastID = 0;
    private Vector<artist> artistVector;
    private Vector<band> bandVector;

    event(avenue location, String eventName){
        lastID++;
        this.id = lastID;
        this.location = location;
        this.eventName = eventName;
        this.maxNoTickets = location.getMaxSpectators();
        this.bandVector = new Vector<band>();
        this.artistVector = new Vector<>();
    }

    void increaseTicketsSold(int amount){
        this.ticketsSold+=amount;
    }

    @Override
    public String toString(){
        return this.eventName;
    }

    public avenue getLocation() {
        return location;
    }

    void addArtistBand(artist toBeAdded){
        this.artistVector.add(toBeAdded);
    }

    void addArtistBand(band toBeAdded){
        this.bandVector.add(toBeAdded);
    }

}
