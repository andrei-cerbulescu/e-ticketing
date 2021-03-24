package eticketing;

public class ticket {

    private static int lastTicketID = 0;
    private int ticketID;
    private client thisClient;
    private event thisEvent;

    ticket(client thisClient, event thisEvent){

        lastTicketID++;
        this.ticketID=lastTicketID;
        this.thisClient = thisClient;
        this.thisEvent = thisEvent;

    }

}
