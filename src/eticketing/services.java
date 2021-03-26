package eticketing;

public class services {

    private services(){
        //Nu e instantiabil
    }

    public static void buyTicket(client curentClient, event curentEvent){

        ticket newTicket = new ticket(curentClient, curentEvent);
        curentEvent.increaseTicketsSold(1);

    }

}
