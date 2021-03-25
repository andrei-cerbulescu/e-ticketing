package eticketing;

public class avenue {
    private static int lastID = 0;

    private int ID;
    private String locationName;
    private int maxSpectators;
    private int minAge;

    public int getMaxSpectators(){
        return(maxSpectators);
    }

    avenue(String locationName, int maxSPectators, int minAge){
        lastID++;
        this.ID = lastID;
        this.locationName=locationName;
        this.maxSpectators=maxSPectators;
        this.minAge=minAge;
    }

    avenue(){
        lastID++;
        this.ID = lastID;
    }

}
