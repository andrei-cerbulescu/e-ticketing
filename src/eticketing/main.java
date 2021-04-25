package eticketing;

import java.util.Arrays;
import java.util.Vector;

public class main {
    public static void main(String[] args) {

        Vector<client> clients = csvParser.readClients();
        avenueList.setAvenueVector(csvParser.readAvenues());



    }
}
