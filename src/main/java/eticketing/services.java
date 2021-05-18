package eticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class services {

    private services(){
        //Nu e instantiabil
    }

    public static void writeToFile(String fileName, String toWrite){

        try{
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(toWrite);
            bw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

}
