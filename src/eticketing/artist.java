package eticketing;

import java.util.Vector;

public class artist extends person{
    private Vector<event> events;
    private band thisBand;
    private static int lastID = 0;

    artist(band thisBand, String name, String surname, int age){

        lastID++;
        this.ID = lastID;
        this.thisBand = thisBand;
        this.name = name;
        this.surname = surname;

    }

}
