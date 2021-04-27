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

    avenue(String locationName, int maxSpectators, int minAge){
        lastID++;
        this.ID = lastID;
        this.locationName=locationName;
        this.maxSpectators=maxSpectators;
        this.minAge=minAge;
    }

    avenue(){
        lastID++;
        this.ID = lastID;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getMinAge() {
        return minAge;
    }

    @Override
    public String toString(){
        return this.locationName;
    }


}
