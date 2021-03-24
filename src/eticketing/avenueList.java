package eticketing;

import java.util.Vector;

public class avenueList {

    private static Vector<avenue> avenueVector;

    public static void addAvenue(avenue toBeAdded){
        avenueVector.add(toBeAdded);
    }

    public static Vector<avenue> getAvenueVector() {
        return avenueVector;
    }
}
