package eticketing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class dbParser {
    private dbParser(){

    }

    public static Vector<client> readClientsFromDB(){
        Vector<client> clients = new Vector<>();

        Connection dbCon = DbConnection.getDataBaseConnection();
        String sql = "SELECT * FROM CLIENTS";


        try{
            Statement stmt = DbConnection.getDataBaseConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()){

                clients.add(new client(result.getString(2), result.getString(3), Integer.parseInt(result.getString(4))));

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return clients;
    }

    public static Vector<artist> readArtistsFromDB(){
        Vector<artist> artists = new Vector<>();

        Connection dbCon = DbConnection.getDataBaseConnection();
        String sql = "SELECT * FROM ARTISTS";


        try{
            Statement stmt = DbConnection.getDataBaseConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()){

                artists.add(new artist(result.getString(2), result.getString(3), Integer.parseInt(result.getString(4))));

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return artists;
    }

    public static Vector<avenue> readAvenueFromDB(){
        Vector<avenue> avenues = new Vector<>();

        Connection dbCon = DbConnection.getDataBaseConnection();
        String sql = "SELECT * FROM AVENUES";


        try{
            Statement stmt = DbConnection.getDataBaseConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()){

                avenues.add(new avenue(result.getString(2), Integer.parseInt(result.getString(3)), Integer.parseInt(result.getString(4))));

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return avenues;
    }

}
