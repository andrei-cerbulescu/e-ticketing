package eticketing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.Vector;


public class csvParser {

    private csvParser(){

    }

    private static Vector<client> clientVector;
    private static Vector<avenue> avenueVector;
    private static Vector<artist> artistVector;
    private static Vector<band> bandVector;

    public static void setClientVector(Vector<client> clientVector) {
        csvParser.clientVector = clientVector;
    }

    public static void setAvenueVector(Vector<avenue> avenueVector) {
        csvParser.avenueVector = avenueVector;
    }

    public static void setArtistVector(Vector<artist> artistVector) {
        csvParser.artistVector = artistVector;
    }

    public static Vector<band> getBandVector() {
        return bandVector;
    }

    public static void initVectors(){
        clientVector = readClients();
        avenueVector = readAvenues();
        artistVector = readArtists();
        bandVector = readBands();
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

    public static Vector<artist> readArtists(){

        Vector<artist> artists = new Vector<>();

        try (Reader in = new FileReader("artistData.csv")) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(new String[]{"name", "surname", "age"})
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records){
                artist newArtist = new artist(record.get("name"), record.get("surname"), Integer.parseInt(record.get("age")));
                artists.add(newArtist);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return artists;

    }

    public static Vector<band> readBands(){

        Vector<band> bands = new Vector<>();

        try (Reader in = new FileReader("bandData.csv")) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(new String[]{"name"})
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records){
                band newBand = new band(record.get("name"));
                bands.add(newBand);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return bands;

    }


}
