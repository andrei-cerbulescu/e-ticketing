package eticketing;

import java.sql.Connection;
import java.util.Vector;

public class dbParser {
    private dbParser(){

    }

    public static Vector<client> readClientsFromDB(){
        Vector<client> clients = new Vector<>();

        Connection dbCon = DbConnection.getDataBaseConnection();
        String sql = "SELECT * FROM CLIENTS";

        return clients;
    }

}
