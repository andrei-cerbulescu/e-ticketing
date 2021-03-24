package eticketing;

import java.util.Vector;

public class client extends person {

    private Vector<ticket> tickets;
    private static int lastID=0;

    client(String name, String surname, int age){

        lastID++;
        this.ID=lastID;
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

    client(){
        lastID++;
        this.ID=lastID;
    }

    public Vector<ticket> getTickets() {
        return tickets;
    }
}
