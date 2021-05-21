package eticketing;

import java.util.Vector;

public class vectorWrapper {
    private static Vector<group> groups = new Vector<>();
    private static Vector<client> clientVector;
    private static Vector<avenue> avenueVector;
    private static Vector<artist> artistVector;
    private static Vector<band> bandVector;
    private static Vector<event> eventVector;

    public static Vector<group> getGroups() {
        return groups;
    }

    public static Vector<client> getClientVector() {
        return clientVector;
    }

    public static Vector<avenue> getAvenueVector() {
        return avenueVector;
    }

    public static Vector<artist> getArtistVector() {
        return artistVector;
    }

    public static Vector<band> getBandVector() {
        return bandVector;
    }

    public static Vector<event> getEventVector() {
        return eventVector;
    }

    public static void initVectors(){

        clientVector = csvParser.readClients();
        clientVector.addAll(dbParser.readClientsFromDB());
        avenueVector = csvParser.readAvenues();
        avenueVector.addAll(dbParser.readAvenueFromDB());
        artistVector = csvParser.readArtists();
        artistVector.addAll(dbParser.readArtistsFromDB());
        bandVector = csvParser.readBands();
        bandVector.addAll(dbParser.readBandsFromDB());
        eventVector = csvParser.readEvents();

    }

}
