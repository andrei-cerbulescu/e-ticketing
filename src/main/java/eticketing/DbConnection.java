package eticketing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/aplicatie";
    private static String PASSWORD = "123456";
    private static String USER = "root";

    private DbConnection(){

    }

    public static Connection getDataBaseConnection(){

        try{
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch(SQLException exception){
            exception.printStackTrace();
        }

        return connection;

    }

    public static void closeDataBaseConnection(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }

}
