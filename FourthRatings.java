
/**
 * Write a description of SecondRatings here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/2017)
 */

import java.util.*;
import edu.duke.*;

public class FourthRatings {
        
    private double getAverageByID(String id, int minimalRaters){
        double average = 0;
        double total = 0;
        int numRaters = 0;
        RaterDatabase RD = new RaterDatabase();
        ArrayList<Rater> myRaters = RD.getRaters();
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
            double aveRating = getAverageByID(movie, minimalRaters);
            if(aveRating != 0){
                Rating rat = new Rating(movie,aveRating);
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
    
    
   private double dotProduct(Rater me, Rater r){
    double dotProduct = 0;
    ArrayList<String> movies = me.getItemsRated();
    for(String movie : movies){
        if(r.hasRating(movie)){
            double meRev = me.getRating(movie);
            meRev -= 5;
            double rRev = r.getRating(movie);
            rRev -= 5;
            dotProduct += meRev * rRev;
        }
    }
    
    return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater rater : raters){
            if(!rater.equals(me)){
                double dotProduct = dotProduct(me, rater);
                if(dotProduct > 0){
                    ratings.add(new Rating(rater.getID(), dotProduct));
                }
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());    
        for(String movie : movies){
            double weightAverageRating = 0;
            double sum = 0;
            int numRaters = 0;
            for(int i = 0; i < numSimilarRaters; i++){
                Rating rating = list.get(i);
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if(rater.hasRating(movie)){
                    sum = sum + rating.getValue() * rater.getRating(movie);
                    numRaters++;
                }
            }
            
            if (numRaters >= minimalRaters) {
                weightAverageRating = sum/numRaters;
                ratings. add(new Rating(movie, weightAverageRating));
            }
            
        }
        
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
 
    
    }
    
    public  ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
            ArrayList<Rating> list = getSimilarities(id);
            ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);  
            for(String movie : movies){
                double weightAverageRating = 0;
                double sum = 0;
                int numRaters = 0;
                for(int i = 0; i <= numSimilarRaters; i++){
                    Rating rating = list.get(i);
                    Rater rater = RaterDatabase.getRater(rating.getItem());
                    if(rater.hasRating(movie)){
                        sum = sum + rating.getValue() * rater.getRating(movie);
                        numRaters++;
                    }
                }
                    
               if (numRaters >= minimalRaters) {
                    weightAverageRating = sum/numRaters;
                    ratings. add(new Rating(movie, weightAverageRating));

                
            }
        }
            Collections.sort(ratings, Collections.reverseOrder());
            return ratings;
    }
}