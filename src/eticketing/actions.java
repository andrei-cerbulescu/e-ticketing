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
                System.out.println("1. Add a new client \n2.Create a group \n3.Add person to group \n4.Remove person from group \n5.Buy a ticket \n6.Transfer a ticket \n7.Create an event \n8.Add an artist/band to the event.");

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

                if(decission == 4){

                    System.out.println("Group index:");
                    int groupIndex, personIndex;
                    groupIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getGroups().elementAt(groupIndex).getClients().remove(personIndex);

                    logger.writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" removed from group with index "+groupIndex);

                }

                if(decission==5){

                    int personIndex, eventIndex;
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Event Index:");
                    eventIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getClientVector().elementAt(personIndex).getTickets().add(new ticket(vectorWrapper.getClientVector().elementAt(personIndex), vectorWrapper.getEventVector().elementAt(eventIndex)));
                    logger.writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex) + " bought a ticket to "+vectorWrapper.getEventVector().elementAt(eventIndex));

                }

                if(decission == 6){

                    int personFromIndex, personToIndex, ticketIndex;
                    System.out.println("Owner Index:");
                    personFromIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Ticket Index:");
                    ticketIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Recipient Index:");
                    personToIndex = Integer.parseInt(reader.nextLine());

                    client owner = vectorWrapper.getClientVector().elementAt(personFromIndex);
                    client recipient = vectorWrapper.getClientVector().elementAt(personToIndex);

                    logger.writeToAudit(owner + " transfered ticket number "+ticketIndex+" to "+recipient);

                    recipient.getTickets().add(owner.getTickets().elementAt(ticketIndex));
                    owner.getTickets().elementAt(ticketIndex).setThisClient(recipient);
                    owner.getTickets().remove(ticketIndex);

                }

                if(decission == 7){
                    int locationIndex;
                    String eventName;
                    System.out.println("Location index:");
                    locationIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Event name:");
                    eventName = reader.nextLine();

                    vectorWrapper.getEventVector().add(new event(vectorWrapper.getAvenueVector().elementAt(locationIndex), eventName));
                    logger.writeToAudit("Event created with name: "+eventName+" at location "+ vectorWrapper.getAvenueVector().elementAt(locationIndex));
                    services.writeToFile("eventData.csv", "\n"+eventName+","+vectorWrapper.getAvenueVector().elementAt(locationIndex).getLocationName());
                }

                if(decission == 8){

                    int newDecission;
                    System.out.println("1-artist\n2-band");
                    newDecission = Integer.parseInt(reader.nextLine());
                    int index, eventIndex;
                    System.out.println("Index of the band/person");
                    index = Integer.parseInt(reader.nextLine());
                    System.out.println("Index of the band/person");
                    eventIndex = Integer.parseInt(reader.nextLine());

                    if(newDecission==1){
                        vectorWrapper.getEventVector().elementAt(eventIndex).addArtistBand(vectorWrapper.getArtistVector().elementAt(index));
                        logger.writeToAudit(vectorWrapper.getArtistVector().elementAt(index)+ " was added to event "+vectorWrapper.getEventVector().elementAt(eventIndex));
                    }
                    if(newDecission==2){
                        vectorWrapper.getEventVector().elementAt(eventIndex).addArtistBand(vectorWrapper.getBandVector().elementAt(index));
                        logger.writeToAudit(vectorWrapper.getBandVector().elementAt(index)+ " was added to event "+vectorWrapper.getEventVector().elementAt(eventIndex));
                    }

                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
