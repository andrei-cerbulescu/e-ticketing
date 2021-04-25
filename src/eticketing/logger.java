package eticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class logger {

    public static void writeToAudit(String eventName){

        try{
            FileWriter fw = new FileWriter("auditData.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n"+eventName+","+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
            bw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

}
