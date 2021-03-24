package eticketing;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

//Work in progress!!!

public class jsonParser {

    public static Vector<client> readClients (){

        Gson gson = new Gson();
        try {
            Object dataObject = gson.fromJson(new FileReader("data.json"), Object.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Vector<client> listOfClients = new Vector<client>();

        return(listOfClients);

    }


}
