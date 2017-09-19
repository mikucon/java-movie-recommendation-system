
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/31/2017)
 */


import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings
{
private int minimalRater;

public MovieRunnerSimilarRatings(){
    minimalRater = 5;
}
    
public void printAverageRatings(){
    RaterDatabase RD = new RaterDatabase();
    RD.initialize("ratings.csv");
   
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    int count = 0;
    System.out.println("total numbers of ratings: "+ RD.size());
    System.out.println("total numbers of movies : " + MD.size());
    FourthRatings fr = new FourthRatings();
    ArrayList<Rating> aveRating = fr.getAverageRatings(minimalRater);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getTitle(r.getItem()));
            count ++;
        }
     System.out.println("Total number of screened movies is " + count);
    
}

public void printAverageRatingsByYearAfterAndGenre(){

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
    FourthRatings fr = new FourthRatings();
    ArrayList<Rating> aveRating = fr.getAverageRatingsByFilter(minimalRater, AllFilters);
    
    Collections.sort(aveRating);
    for(Rating r: aveRating) {
        
            System.out.println(r.getValue() + " " + MD.getDirector(r.getItem()) + " " + MD.getYear(r.getItem()) + " " + MD.getTitle(r.getItem()));
            count ++;
        }
          System.out.println("Total number of screened movies is " + count);
}

public void printSimilarRatings(){
    String id = "337";
    int similarRaters = 10;
    int minimalRating = 3;
    
    int count = 0;
    
    FourthRatings fr = new FourthRatings();
    RaterDatabase RD = new RaterDatabase();
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    RD.initialize("ratings.csv");
    System.out.println("total numbers of movies : " + MD.size());

    ArrayList<Rating> aveRating = fr.getSimilarRatings(id, similarRaters, minimalRating);
    for(int r =0; r < aveRating.size(); r++){
        if( r < 15){
            System.out.println(aveRating.get(r).getValue() + " " + MD.getTitle(aveRating.get(r).getItem()));
        
        }
    count++;
    }
     System.out.println("Total number of screened movies is " + count);

}

public void printSimilarRatingsByGenre(){
    String id = "964";
    int similarRaters = 20;
    int minimalRating = 5;
    String genre = "Mystery";
    
    int count = 0;
    Filter GenreFilter = new GenreFilter(genre);
    FourthRatings fr = new FourthRatings();
    RaterDatabase RD = new RaterDatabase();
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    RD.initialize("ratings.csv");
    System.out.println("total numbers of movies : " + MD.size());

    ArrayList<Rating> aveRating = fr.getSimilarRatingsByFilter(id, similarRaters, minimalRating, GenreFilter);
    
    for(int r =0; r < aveRating.size(); r++){
        //if( r <= 15){
            System.out.println(aveRating.get(r).getValue() + " " + MD.getTitle(aveRating.get(r).getItem()));
        
        //}
    count++;
    }
     System.out.println("Total number of screened movies is " + count);

}

public void printSimilarRatingsByDirector(){
    String id = "120";
    int similarRaters = 10;
    int minimalRating = 2;
    String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
    
    int count = 0;
        
    Filter DirectorsFilter = new DirectorsFilter(directors);
    FourthRatings fr = new FourthRatings();
    RaterDatabase RD = new RaterDatabase();
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    RD.initialize("ratings.csv");
    System.out.println("total numbers of movies : " + MD.size());

    ArrayList<Rating> aveRating = fr.getSimilarRatingsByFilter(id, similarRaters, minimalRating, DirectorsFilter);
    
    for(int r =0; r < aveRating.size(); r++){
        //if( r <= 15){
            System.out.println(aveRating.get(r).getValue() + " " + MD.getTitle(aveRating.get(r).getItem()));
        
        //}
    count++;
    }
     System.out.println("Total number of screened movies is " + count);
}

public void printSimilarRatingsByGenreAndMinutes(){
    String id = "168";
    int similarRaters = 10;
    int minimalRating = 3;
    int minMinutes = 80;
    int maxMinutes = 160;
    String genre = "Drama";
    
    int count = 0;
    FourthRatings fr = new FourthRatings();
    RaterDatabase RD = new RaterDatabase();
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    RD.initialize("ratings.csv");
    System.out.println("total numbers of movies : " + MD.size());
    
    Filter MinutesFilter = new MinutesFilter(minMinutes, maxMinutes);
    Filter GenreFilter = new GenreFilter(genre);
    AllFilters AllFilters = new AllFilters();
    AllFilters.addFilter(MinutesFilter);
    AllFilters.addFilter(GenreFilter);
    
    ArrayList<Rating> aveRating = fr.getSimilarRatingsByFilter(id, similarRaters, minimalRating, AllFilters);
    
    for(int r =0; r < aveRating.size(); r++){
        //if( r <= 15){
            System.out.println(aveRating.get(r).getValue() + " " + MD.getTitle(aveRating.get(r).getItem()));
        
        //}
    count++;
    }
     System.out.println("Total number of screened movies is " + count);
}

public void printSimilarRatingsByYearAfterAndMinutes(){
    String id = "314";
    int similarRaters = 10;
    int minimalRating = 5;
    int minMinutes = 70;
    int maxMinutes = 200;
    int year = 1975;
    
    int count = 0;
    FourthRatings fr = new FourthRatings();
    RaterDatabase RD = new RaterDatabase();
    MovieDatabase MD = new MovieDatabase();
    MD.initialize("ratedmoviesfull.csv");
    RD.initialize("ratings.csv");
    System.out.println("total numbers of movies : " + MD.size());
    
    Filter MinutesFilter = new MinutesFilter(minMinutes, maxMinutes);
    Filter YearAfterFilter = new YearAfterFilter(year);
    AllFilters AllFilters = new AllFilters();
    AllFilters.addFilter(YearAfterFilter);
    AllFilters.addFilter(MinutesFilter);
    
     ArrayList<Rating> aveRating = fr.getSimilarRatingsByFilter(id, similarRaters, minimalRating, AllFilters);
    
    for(int r =0; r < aveRating.size(); r++){
       // if( r <= 15){
            System.out.println(aveRating.get(r).getValue() + " " + MD.getTitle(aveRating.get(r).getItem()));
        
       // }
    count++;
    }
     System.out.println("Total number of screened movies is " + count);
}
}
