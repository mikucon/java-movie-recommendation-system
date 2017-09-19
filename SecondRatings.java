
/**
 * Write a description of SecondRatings here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/2017)
 */

import java.util.*;
import edu.duke.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private String title;
        //   	private double average;
        //     private double total;
        //     private int numRaters;
    public SecondRatings() {
                // default constructor
                
                this("data/ratedmoviesfull.csv", "data/ratings.csv");
        //         average = 0;
        //         total = 0;
        //         numRaters = 0;
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
        
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        
        for(Movie movie: myMovies){
            
            if(getAverageByID(movie.getID(), minimalRaters) != 0){
                Rating rat = new Rating(movie. getID(), getAverageByID(movie.getID(), minimalRaters));
                rating.add(rat);
            }
            
        }
        
         //         Collections.sort(rating);
        return rating;
    }
    
    public String getTitle(String id){
        for(Movie movie: myMovies){
            
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
    
        }
        return "not found";
    }
    
       public String getID(String title) {
    	for(Movie movie: myMovies) {
    		if(movie.getTitle().equals(title))
    			return movie.getID();
    	}
    	return "NO SUCH TITLE.";
    }
    
   
    
}