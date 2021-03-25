package eticketing;

import java.util.Arrays;
import java.util.Vector;

public class main {
    public static void main(String[] args) {

        Vector<client> clients = jsonParser.readClients();
        avenueList.setAvenueVector(jsonParser.readAvenues());

        Vector<avenue> test = avenueList.getAvenueVector();

    }
}
