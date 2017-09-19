
/**
 * Write a description of class MovieRunnerAverage here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/17)
 */

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
public class MovieRunnerWithFilters
{
 private int minimalRater;
    
 public MovieRunnerWithFilters() {
        minimalRater = 3;
 }

 
public void printAverageRatings(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    

    System.out.println("total numbers of ratings: "+ ThirdRatings.getRaterSize());
    int count = 0;


    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatings(minimalRater);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getTitle(r.getItem()));
            count ++;
        }
     System.out.println("Total number of screened movies is " + count);
    
}

public void printAverageRatingsByYear(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    int year = 2000;
    Filter YearAfterFilter = new YearAfterFilter(year);

    System.out.println("total numbers of ratings: "+ ThirdRatings.getRaterSize());
    int count = 0;


    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, YearAfterFilter);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getYear(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
     System.out.println("Total number of screened movies is " + count);
    
}

public void printAverageRatingsByGenre(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    String genre = "Comedy";
    Filter GenreFilter = new GenreFilter(genre);
    System.out.println("total numbers of ratings: "+ ThirdRatings.getRaterSize());
    
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    int count = 0;
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, GenreFilter);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getGenres(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
         System.out.println("Total number of screened movies is " + count);
}

public void printAverageRatingsByMinutes(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    int minMinutes = 105;
    int maxMinutes = 135;
    Filter MinutesFilter = new MinutesFilter(minMinutes, maxMinutes);
    System.out.println("total numbers of ratings: "+ ThirdRatings.getRaterSize());
    int count = 0;
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, MinutesFilter);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getMinutes(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
        System.out.println("Total number of screened movies is " + count);
}

public void printAverageRatingsByDirectors(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
    Filter DirectorsFilter = new DirectorsFilter(directors);
    System.out.println("total numbers of ratings: "+ ThirdRatings.getRaterSize());
    
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    int count = 0;
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, DirectorsFilter);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getDirector(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
          System.out.println("Total number of screened movies is " + count);
}

public void printAverageRatingsByYearAfterAndGenre(){
    ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
    String genre = "Drama";
    int year = 1990;
    Filter GenreFilter = new GenreFilter(genre);
    Filter YearAfterFilter = new YearAfterFilter(year);
    AllFilters AllFilters = new AllFilters();
    AllFilters.addFilter(YearAfterFilter);
    AllFilters.addFilter(GenreFilter);
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    System.out.println("total numbers of movies : " + MD.size());
    int count = 0;
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, AllFilters);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getDirector(r.getItem()) + " " + MD.getYear(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
          System.out.println("Total number of screened movies is " + count);
}

public void printAverageRatingsByDirectorsAndMinutes(){
     ThirdRatings ThirdRatings = new ThirdRatings("data/ratings.csv");
     String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
     int minMinutes = 90;
     int maxMinutes = 180;
     Filter DirectorsFilter = new DirectorsFilter(directors);
     Filter MinutesFilter = new MinutesFilter(minMinutes, maxMinutes);
     AllFilters AllFilters = new AllFilters();
     AllFilters.addFilter(DirectorsFilter);
     AllFilters.addFilter(MinutesFilter);
     MovieDatabase MD = new MovieDatabase();
     MD.initialize("ratedmoviesfull.csv");
     System.out.println("total numbers of movies : " + MD.size());
    int count = 0;
    ArrayList<Rating> aveRating = ThirdRatings.getAverageRatingsByFilter(minimalRater, AllFilters);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getMinutes(r.getItem()) + " " + MD.getDirector(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
           System.out.println("Total number of screened movies is " + count);
}
}
