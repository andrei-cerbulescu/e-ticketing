package eticketing;

import com.google.gson.*;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;


public class jsonParser {

    public static Vector<client> readClients (){

        Gson gson = new Gson();
        Object dataObject = null;

        String strCurrentLine;
        BufferedReader objReader;
        String dataString = "";

        try {
            objReader = new BufferedReader(new FileReader("data.json"));
            while ((strCurrentLine = objReader.readLine()) != null) {

                dataString+=strCurrentLine;
            }
            objReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        client[] gsonData = new Gson().fromJson(dataString, client[].class);

        Vector<client> listOfClients = new Vector<client>(Arrays.asList(gsonData));

        return(listOfClients);

    }


}
