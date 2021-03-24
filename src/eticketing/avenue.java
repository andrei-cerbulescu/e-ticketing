package eticketing;

public class avenue {
    private String locationName;
    private int maxSpectators;
    private int minAge;

    public int getMaxSpectators(){
        return(maxSpectators);
    }

    avenue(String locationName, int maxSPectators, int minAge){
        this.locationName=locationName;
        this.maxSpectators=maxSPectators;
        this.minAge=minAge;
    }

}
