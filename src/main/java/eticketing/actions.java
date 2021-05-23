package eticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class actions {

    public static void decission(){

        Scanner reader = new Scanner(new InputStreamReader(System.in));

        try {

            while (true) {

                int decission;
                System.out.println(
                                "1.Add a new client \n2.Create a group \n3.Add person to group \n4.Remove person from group "+
                                "\n5.Buy a ticket \n6.Transfer a ticket \n7.Create an event \n8.Add an artist/band to the event "+
                                "\n9.Cancel an event \n10.Add/remove an artist from a band \n11.Delete a client \n12.Transfer an artist "+
                                        "\n13.Database commands \n14.List all elements in a vector \n15.Print all tickets of an user \n16.Print all people from a group "+
                                        "\n17.Print all members from a band \n18.Add artist to band");

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

                    vectorWrapper.getGroups().elementAt(groupIndex).addClient(vectorWrapper.getClientVector().elementAt(personIndex));

                    logger.getLogger().writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" added to group with index "+groupIndex);

                }

                if(decission == 4){

                    System.out.println("Group index:");
                    int groupIndex, personIndex;
                    groupIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());

                    vectorWrapper.getGroups().elementAt(groupIndex).removeClient(personIndex);

                    logger.getLogger().writeToAudit(vectorWrapper.getClientVector().elementAt(personIndex)+" removed from group with index "+groupIndex);

                }

                if(decission==5){

                    int personIndex, eventIndex;
                    System.out.println("Person Index:");
                    personIndex = Integer.parseInt(reader.nextLine());
                    System.out.println("Event Index:");
                    eventIndex = Integer.parseInt(reader.nextLine());

                    if(vectorWrapper.getEventVector().elementAt(eventIndex).getLocation().getMinAge()<=vectorWrapper.getClientVector().elementAt(personIndex).getAge()){
                        client curentClient = vectorWrapper.getClientVector().elementAt(personIndex);
                        curentClient.getTickets().add(new ticket(curentClient, vectorWrapper.getEventVector().elementAt(eventIndex)));
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

                if(decission==13){
                    dbDecissions();
                }

                if(decission==14){
                    listElementsInVector();
                }

                if(decission==15){
                    System.out.println("Insert person index:");
                    int personIndex = Integer.parseInt(reader.nextLine());
                    printTicketsOfClient(personIndex);
                }

                if(decission==16){
                    System.out.println("Insert group index:");
                    int grooupIndex = Integer.parseInt(reader.nextLine());
                    printMembersOfGroup(grooupIndex);
                }

                if(decission==17){
                    System.out.println("Insert band index:");
                    int bandIndex = Integer.parseInt(reader.nextLine());
                    printMembersOfBand(bandIndex);
                }

                if(decission==18){
                    System.out.println("Insert artist index:");
                    int artistIndex = Integer.parseInt(reader.nextLine());

                    System.out.println("Insert band index:");
                    int bandIndex = Integer.parseInt(reader.nextLine());
                    addArtistToBand(artistIndex, bandIndex);
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void addArtistToBand(int artistIndex, int bandIndex){

        artist thisArtist = vectorWrapper.getArtistVector().elementAt(artistIndex);
        band thisBand = vectorWrapper.getBandVector().elementAt(bandIndex);

        if(thisArtist.hasBand() == true){
            System.out.println("This artist is already part of a band");
        }
        else{

            thisArtist.setBand(thisBand);
            thisBand.addArtist(thisArtist);

        }

    }

    public static void printMembersOfBand(int bandIndex){
        band thisBand = vectorWrapper.getBandVector().elementAt(bandIndex);
        int index = 0;
        for (artist element:thisBand.getArtists()
             ) {
            System.out.println(index+ " "+element);
            index++;
        }

    }

    public static void printMembersOfGroup(int groupIndex){
        int index = 0;

        group thisGroup = vectorWrapper.getGroups().elementAt(groupIndex);
        for (client element:thisGroup.getClients()
             ) {

            System.out.println(index+ " "+element);
            index++;

        }

    }

    public static void printTicketsOfClient(int personIndex){

        Scanner reader = new Scanner(new InputStreamReader(System.in));
        Vector<ticket> ticketVector = vectorWrapper.getClientVector().elementAt(personIndex).getTickets();

        int index = 0;

        for (ticket element:ticketVector
             ) {

            System.out.println(index+" "+element);
            index++;

        }

        System.out.println("\n");

    }

    public static void listElementsInVector(){

        Scanner reader = new Scanner(new InputStreamReader(System.in));
        System.out.println("What do you want to print?\n1.Groups\n2.Clients\n3.Avenues\n4.Artists\n5.Bands\n6.Events\n");
        int decission = Integer.parseInt(reader.nextLine());
        int index = 0;
        
        if(decission == 1){
            for (group element:vectorWrapper.getGroups()
                 ) {
                System.out.println(index+" "+element);
                index++;
            }
        }

        if(decission == 2){
            for (client element:vectorWrapper.getClientVector()
                 ) {
                System.out.println(index+" "+element);
                index++;
            }
        }

        if(decission == 3){
            for (avenue element:vectorWrapper.getAvenueVector()
                 ) {
                System.out.println(index+" "+element);
                index++;
            }
        }

        if(decission == 4){
            for (artist element:vectorWrapper.getArtistVector()
                 ) {
                System.out.println(index+" "+element);
                index++;
            }
        }

        if(decission == 5){
            for (band element:vectorWrapper.getBandVector()
                 ) {
                System.out.println(index+" "+element);
                index++;
            }
        }
        if(decission == 6){
            for (event element:vectorWrapper.getEventVector()
            ) {
                System.out.println(index+" "+element);
                index++;
            }
        }
        System.out.println();


    }

    public static void dbDecissions(){

        Scanner reader = new Scanner(new InputStreamReader(System.in));

        while(true){

            System.out.println("1.Create\n2.Read\n3.Update\n4.Delete");
            int decission = Integer.parseInt(reader.nextLine());

            if(decission == 3){
                sqlUpdate();
            }

            if(decission == 1){
                insertDb();
            }

            if(decission == 2 || decission == 4){
                printAllTables();
                String sql = "";
                if(decission == 2){
                    sql = "SELECT * FROM "+ reader.nextLine();
                    logger.getLogger().writeToAudit("Ran database command "+sql);
                    sqlRead(sql);
                }

                else{
                    try{
                        sql = "DELETE FROM "+ reader.nextLine() + " WHERE id = ";
                        System.out.println("Input ID");
                        sql = sql + reader.nextLine();
                        System.out.println(sql);
                        logger.getLogger().writeToAudit("Ran database command "+sql);
                        DbConnection.getDataBaseConnection().createStatement().execute(sql);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }

        }

    }

    public static void sqlRead(String sql){
        try{
            Statement stmt = DbConnection.getDataBaseConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            System.out.println("\n");

            while(result.next()){
                for(int i=1;i<=result.getMetaData().getColumnCount();i++){
                    System.out.print(result.getString(i)+" ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void sqlUpdate(){
        Scanner reader = new Scanner(new InputStreamReader(System.in));
        printAllTables();
        String sql = "UPDATE "+ reader.nextLine()+" SET ";

        System.out.println("Number of columns you want to update:");
        int numberOfColumns = Integer.parseInt(reader.nextLine());
        for(int i=0;i<numberOfColumns;i++){
            System.out.println("Column name: ");
            sql+= reader.nextLine()+" = ";

            System.out.println("New value: ");
            sql+= '"'+reader.nextLine()+'"';
            if(i != numberOfColumns-1){
                sql+=", ";
            }
        }

        sql +=" WHERE id = ";
        System.out.println("Insert id: ");
        sql+=reader.nextLine();
        System.out.println(sql);
        try {
            logger.getLogger().writeToAudit("Ran database command "+sql);
            DbConnection.getDataBaseConnection().createStatement().execute(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void printAllTables(){
        try{
            ResultSet result = DbConnection.getDataBaseConnection().createStatement().executeQuery("SHOW TABLES");
            while(result.next()){
                System.out.println(result.getString(1));
            }
        }
        catch(Exception e){
            System.out.println("Couldn't get the tables!");
        }
    }

    public static void insertDb(){

        System.out.println("Choose a table to insert into.");
        Scanner reader = new Scanner(new InputStreamReader(System.in));
        printAllTables();
        String table = reader.nextLine();
        String sql = "SELECT * FROM "+table+" WHERE id = -1";
        try{
            ResultSet results = DbConnection.getDataBaseConnection().createStatement().executeQuery(sql);

            sql = "INSERT INTO "+table+" (";

            for(int i=2;i<=results.getMetaData().getColumnCount();i++){
                sql+=results.getMetaData().getColumnName(i);

                if(i!=results.getMetaData().getColumnCount()){
                    sql+=", ";
                }
            }

            sql+=") VALUES (";

            for(int i=2;i<=results.getMetaData().getColumnCount();i++){
                System.out.println("Insert value for "+results.getMetaData().getColumnName(i));
                sql+='"'+reader.nextLine()+'"';

                if(i!=results.getMetaData().getColumnCount()){
                    sql+=", ";
                }
            }
            sql+=")";
            System.out.println(sql);
            logger.getLogger().writeToAudit("Ran database command "+sql);
            DbConnection.getDataBaseConnection().createStatement().executeUpdate(sql);

        }
        catch(Exception e){
            System.out.println("Something went very wrong with this insert!");
        }


    }

}
