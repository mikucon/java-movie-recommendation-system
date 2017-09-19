
/**
 * Write a description of class MovieRunnerAverage here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/2017)
 */

import java.util.*;

public class MovieRunnerAverage
{
 private int minimalRater;
    
 public MovieRunnerAverage() {
        minimalRater = 12;
 }
    
public void printAverageRatings(){
    SecondRatings secondRatings = new SecondRatings();
    System.out.println("total numbers of movies: " + secondRatings.getMovieSize());
    System.out.println("total numbers of ratings: "+ secondRatings.getRaterSize());
    int count = 0;

    ArrayList<Rating> aveRating = secondRatings.getAverageRatings(minimalRater);
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
	    	System.out.println(r.getValue() + " " + secondRatings.getTitle(r.getItem()));
	    	//count ++
	    }
	//System.out.println(count);
    
}

 public void getAverageRatingOneMovie(){
    SecondRatings secondRatings2 = new SecondRatings();
    
    ArrayList<Rating> rating = secondRatings2.getAverageRatings(minimalRater);
    //String name = "Moneyball";
    String name3 = "Vacation";
    String ID = secondRatings2.getID(name3);
    for(Rating rat : rating){
        if(rat.getItem().equals(ID)){
            System.out.println("The movie " + name3 + "'s rating is " + rat.getValue());
        }

    }
    }
}
