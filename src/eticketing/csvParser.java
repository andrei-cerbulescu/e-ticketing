package eticketing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class csvParser {

    private csvParser(){

    }

    public static Vector<client> readClients(){

        Vector<client> clients = new Vector<>();

        try (Reader in = new FileReader("clientData.csv")) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(new String[]{"name", "surname", "age"})
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records){
                client newClient = new client(record.get("name"), record.get("surname"), Integer.parseInt(record.get("age")));
                clients.add(newClient);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return clients;

    }

    public static Vector<avenue> readAvenues(){
        Vector<avenue> avenues = new Vector<>();

        try (Reader in = new FileReader("avenueData.csv")) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(new String[]{"avenue", "maxSpectators", "minAge"})
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records){
                avenue newAvenue = new avenue(record.get("avenue"), Integer.parseInt(record.get("maxSpectators")), Integer.parseInt(record.get("minAge")));
                avenues.add(newAvenue);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return avenues;
    }

}
