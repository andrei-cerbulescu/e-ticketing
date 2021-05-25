package eticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class logger {

    private static logger curentLogger;

    private logger(){

    }

    public static logger getLogger(){
        if(curentLogger == null){
            curentLogger = new logger();
        }

        return curentLogger;
    }

    public void writeToAudit(String eventName){

        try{
            FileWriter fw = new FileWriter("auditData.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n"+eventName+","+new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date()));
            bw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String sql = "INSERT INTO LOGS (eventName, timestamp) VALUES (?,?)";
        Connection connection = DbConnection.getDataBaseConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, eventName);
            statement.setString(2, new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date()));
            statement.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
            System.err.println("Cannot insert into table");
        }

    }

}
