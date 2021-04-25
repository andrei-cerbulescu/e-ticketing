package eticketing;

import java.util.Vector;

public class group {

    private Vector<client> clients;

    public Vector<client> getClients() {
        return clients;
    }

    public group(){
        this.clients = new Vector<client>();
        vectorWrapper.getGroups().add(this);
    }

}
