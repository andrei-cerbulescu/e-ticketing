package eticketing;

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
                System.out.println("1. Add a new client \n2.Create a group \n3.Add person to group");

                decission = Integer.parseInt(reader.nextLine());

                if(decission == 1){
                    client newClient = new client(reader.nextLine(), reader.nextLine(), Integer.parseInt(reader.nextLine()));
                    FileWriter fw = new FileWriter("clientData.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("\n"+newClient.name+","+newClient.surname+","+newClient.age);
                    bw.close();

                    vectorWrapper.getClientVector().add(newClient);

                    logger.writeToAudit("Added "+newClient);
                }

                if(decission == 2){

                    group newGroup = new group();
                    vectorWrapper.getGroups().add(newGroup);
                    logger.writeToAudit("New group created");

                }

                if(decission == 3){

                    System.out.println("Group index:");
                    int groupIndex, personIndex;
                    groupIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getGroups().elementAt(groupIndex).getClients().add(vectorWrapper.getClientVector().elementAt(personIndex));

                    logger.writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" added to group with index "+groupIndex);

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



}
