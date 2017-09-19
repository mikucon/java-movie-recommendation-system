
/**
 * Write a description of class Rater here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/17)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String k : myRatings.keySet()){
            if(myRatings.get(k).getItem().equals(item)){
            return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String k : myRatings.keySet()){
            list.add(k);
        }
        
        return list;
    }
}
