
/**
 * Write a description of SecondRatings here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/2017)
 */

import java.util.*;
import edu.duke.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;

        //      private double average;
        //     private double total;
        //     private int numRaters;
    public ThirdRatings() {
                // default constructor
                
                this("ratings.csv");
        //         average = 0;
        //         total = 0;
        //         numRaters = 0;
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        
        myRaters = firstRatings.loadRaters(ratingsfile);
        
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    
    private double getAverageByID(String id, int minimalRaters){
        double average = 0;
        double total = 0;
        int numRaters = 0;
        for(Rater rater : myRaters){
                if(rater.hasRating(id)){
                    numRaters++;
                    total = total + rater.getRating(id);
                }
        }
        if(numRaters>=minimalRaters){
            average = total / numRaters;
            
        }
       return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movie: movies){
            
            if(getAverageByID(movie, minimalRaters) != 0){
                Rating rat = new Rating(movie, getAverageByID(movie, minimalRaters));
                rating.add(rat);
            }
            
        }
        
         //         Collections.sort(rating);
        return rating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie: movies){
                if(getAverageByID(movie, minimalRaters)!=0){
                Rating rat = new Rating(movie, getAverageByID(movie,minimalRaters));
                rating.add(rat);
            }
        }
        
        return rating;
    }
    
    
   
    
}