package eticketing;

import java.util.Vector;

public class band {

    private String name;
    private Vector<artist> artists;

    public band(String name){
        this.name = name;
        artists = new Vector<artist>();
    }

    public void addArtist(artist newArtist){

        artists.add(newArtist);
        newArtist.setBand(this);

    }

}
