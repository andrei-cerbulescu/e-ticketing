package eticketing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class actions {

    public static void decission(){

        Scanner reader = new Scanner(new InputStreamReader(System.in));

        try {

            while (true) {

                int decission;
                System.out.println("1. Add a new client");

                decission = Integer.parseInt(reader.nextLine());

                if(decission == 1){
                    client newClient = new client(reader.nextLine(), reader.nextLine(), Integer.parseInt(reader.nextLine()));
                    FileWriter fw = new FileWriter("clientData.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("\n"+newClient.name+","+newClient.surname+","+newClient.age);
                    bw.close();

                    csvParser.getClientVector().add(newClient);

                    logger.writeToAudit("Added "+newClient);
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



}
