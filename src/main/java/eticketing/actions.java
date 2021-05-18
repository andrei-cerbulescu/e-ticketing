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
                System.out.println("1.Add a new client \n2.Create a group \n3.Add person to group \n4.Remove person from group \n5.Buy a ticket \n6.Transfer a ticket \n7.Create an event \n8.Add an artist/band to the event \n9.Cancel an event \n10.Add/remove an artist from a band \n11.Delete a client \n12.Transfer an artist");

                decission = Integer.parseInt(reader.nextLine());

                if(decission == 1){
                    client newClient = new client(reader.nextLine(), reader.nextLine(), Integer.parseInt(reader.nextLine()));
                    FileWriter fw = new FileWriter("clientData.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("\n"+newClient.name+","+newClient.surname+","+newClient.age);
                    bw.close();

                    vectorWrapper.getClientVector().add(newClient);

                    logger.getLogger().writeToAudit("Added "+newClient);
                }

                if(decission == 2){

                    group newGroup = new group();
                    vectorWrapper.getGroups().add(newGroup);
                    logger.getLogger().writeToAudit("New group created");

                }

                if(decission == 3){

                    System.out.println("Group index:");
                    int groupIndex, personIndex;
                    groupIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getGroups().elementAt(groupIndex).getClients().add(vectorWrapper.getClientVector().elementAt(personIndex));

                    logger.getLogger().writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" added to group with index "+groupIndex);

                }

                if(decission == 4){

                    System.out.println("Group index:");
                    int groupIndex, personIndex;
                    groupIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getGroups().elementAt(groupIndex).getClients().remove(personIndex);

                    logger.getLogger().writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" removed from group with index "+groupIndex);

                }

                if(decission==5){

                    int personIndex, eventIndex;
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Event Index:");
                    eventIndex = Integer.parseInt(reader.nextLine());

                    if(vectorWrapper.getEventVector().elementAt(eventIndex).getLocation().getMinAge()<=vectorWrapper.getClientVector().elementAt(personIndex).getAge()){
                        vectorWrapper.getClientVector().elementAt(personIndex).getTickets().add(new ticket(vectorWrapper.getClientVector().elementAt(personIndex), vectorWrapper.getEventVector().elementAt(eventIndex)));
                        vectorWrapper.getEventVector().elementAt(eventIndex).increaseTicketsSold(1);
                        logger.getLogger().writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex) + " bought a ticket to "+vectorWrapper.getEventVector().elementAt(eventIndex));
                    }
                    else{
                        System.out.println("This person is not old enough!");
                    }


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

                    logger.getLogger().writeToAudit(owner + " transfered ticket number "+ticketIndex+" to "+recipient);

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
                    logger.getLogger().writeToAudit("Event created with name: "+eventName+" at location "+ vectorWrapper.getAvenueVector().elementAt(locationIndex));
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
                        logger.getLogger().writeToAudit(vectorWrapper.getArtistVector().elementAt(index)+ " was added to event "+vectorWrapper.getEventVector().elementAt(eventIndex));
                    }
                    if(newDecission==2){
                        vectorWrapper.getEventVector().elementAt(eventIndex).addArtistBand(vectorWrapper.getBandVector().elementAt(index));
                        logger.getLogger().writeToAudit(vectorWrapper.getBandVector().elementAt(index)+ " was added to event "+vectorWrapper.getEventVector().elementAt(eventIndex));
                    }

                }
                if(decission==9){
                    System.out.println("Event index:");
                    int eventIndex = Integer.parseInt(reader.nextLine());

                    logger.getLogger().writeToAudit("Event "+ vectorWrapper.getEventVector().elementAt(eventIndex)+" was canceled");

                    for (client curentClient:vectorWrapper.getClientVector()
                    ) {

                        for(int i=0;i<curentClient.getTickets().size();i++){
                            if(curentClient.getTickets().elementAt(i).getThisEvent()==vectorWrapper.getEventVector().elementAt(eventIndex)){
                                curentClient.getTickets().remove(i);
                                i--;
                            }
                        }

                    }

                    vectorWrapper.getEventVector().remove(eventIndex);

                }

                if(decission==10){

                    System.out.println("1.Add an artist to a band\n2.Remove an artist from a band");

                    int decission2 = Integer.parseInt(reader.nextLine());
                    if(decission2==1){
                        System.out.println("Input artist index:");
                        int artistIndex = Integer.parseInt(reader.nextLine());
                        System.out.println("Input band index:");
                        int bandIndex = Integer.parseInt(reader.nextLine());
                        vectorWrapper.getBandVector().elementAt(bandIndex).addArtist(vectorWrapper.getArtistVector().elementAt(artistIndex));
                        logger.getLogger().writeToAudit(vectorWrapper.getArtistVector().elementAt(artistIndex)+" was added to "+vectorWrapper.getBandVector().elementAt(bandIndex));
                    }

                    if(decission2==2){
                        System.out.println("Input band index:");
                        int bandIndex = Integer.parseInt(reader.nextLine());
                        System.out.println("Input artist number in band:");
                        int artistIndex = Integer.parseInt(reader.nextLine());
                        vectorWrapper.getBandVector().elementAt(bandIndex).removeArtistAtPosition(artistIndex);
                        logger.getLogger().writeToAudit(vectorWrapper.getArtistVector().elementAt(artistIndex)+" was removed from "+vectorWrapper.getBandVector().elementAt(bandIndex));
                    }

                }

                if(decission==11){

                    System.out.println("Input client index:");
                    int clientIndex = Integer.parseInt(reader.nextLine());

                    logger.getLogger().writeToAudit("The client "+vectorWrapper.getClientVector().elementAt(clientIndex)+" was deleted");

                    vectorWrapper.getClientVector().elementAt(clientIndex).deleteTickets();
                    vectorWrapper.getClientVector().remove(clientIndex);

                }

                if(decission==12){
                    System.out.println("Input initial band index:");
                    int sourceBandIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Input artist band index:");
                    int artistIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Input destination band index:");
                    int destinationBandIndex = Integer.parseInt(reader.nextLine());

                    logger.getLogger().writeToAudit("The artist "+vectorWrapper.getBandVector().elementAt(sourceBandIndex).getArtistAtPosition(artistIndex)+" was transfered form "+vectorWrapper.getBandVector().elementAt(sourceBandIndex)+ " to "+vectorWrapper.getBandVector().elementAt(destinationBandIndex));

                    vectorWrapper.getBandVector().elementAt(destinationBandIndex).addArtist(vectorWrapper.getBandVector().elementAt(sourceBandIndex).getArtistAtPosition(artistIndex));
                    vectorWrapper.getBandVector().elementAt(sourceBandIndex).removeArtistAtPosition(artistIndex);

                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
