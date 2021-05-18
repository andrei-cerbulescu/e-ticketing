package eticketing;

import java.util.Vector;

public class artist extends person{
    private Vector<event> events;
    private band band;
    private static int lastID = 0;

    public artist(String name, String surname, int age){

        lastID++;
        this.ID = lastID;
        this.name = name;
        this.surname = surname;

    }

    public void setBand(band newBand){
        this.band=newBand;
    }

}
