package eticketing;

import java.util.Vector;

public class group {

    private Vector<client> clients;

    public void addClient(client toBeAdded){

        clients.add(toBeAdded);

    }

    public void removeClient(int index){
        clients.remove(index);
    }

    public Vector<client> getClients() {
        return clients;
    }

    public group(){
        this.clients = new Vector<client>();
        vectorWrapper.getGroups().add(this);
    }

}
