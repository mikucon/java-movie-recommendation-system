
/**
 * Write a description of class FirstRatings here.
 * 
 * @author (Xiaozhe Li) 
 * @version (7/20/2017)
 */


import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings
{
    private int countGenre;
    private int countTime;
    private int numByMovie;
    public FirstRatings(){
        countGenre = 0;
        countTime = 0;
        numByMovie = 0;
    }
    
    //Movies===========================================================================================
    public ArrayList<Movie>  loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec: parser){
            Movie movie = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"),rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            movies.add(movie);
        }
        return movies;
    }

    public void countGenres(ArrayList<Movie> movies){
        String genres = "comedy";
        for(Movie part : movies){
            if(part.getGenres().toLowerCase().contains(genres))
            countGenre++;
        }
        System.out.println(genres + " " + "shows" + " " + countGenre + " " +"times");
    }
    
    public void countTime(ArrayList<Movie> movies){
        int time = 150;
        for(Movie part : movies){
            if(part.getMinutes() > time)
            countTime++;
        }
        System.out.println("there are" + " " +countTime + " " + "movies longer than " +  "" + time + " " + "minutes");
    }
    
    public void maxDirector(ArrayList<Movie> movies){
        int maxNum = 1;
        HashMap<String, Integer> map = new HashMap<String,Integer>();
        for(Movie movie: movies){
            String[] directors = movie.getDirector().split(",");
            for(String direct : directors){
                if(!map.containsKey(direct)){
                     map.put(direct,1);

                }
                else{
                    int number = map.get(direct);
                    map.put(direct,number+1);
                    if(maxNum < number+1)
                        maxNum = number+1;
                    }
               
            
            }
              
        }
        System.out.println("maximun of directors" + " " + "shows" + " " + maxNum + " " + "times");     
        for(String direct : map.keySet()){
                if(map.get(direct) == maxNum)
                    System.out.println("The director is " + " " + direct);
        }
    }

    
    public void testLoadMovies(){
         //String filename = "data/ratedmovies_short.csv";
         String filename = "data/ratedmoviesfull.csv";
         ArrayList<Movie> movies = loadMovies(filename);
         HashMap<String, Integer> map = new HashMap<String, Integer>();
         countGenres(movies);
         countTime(movies);
         maxDirector(movies);
         System.out.println("this" + " " + filename + " " + "file contains" + " " + movies.size() + " " + "movies");
         
        
    }

    //Raters===================================================================================================================
        public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> rater = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord rec: parser) {
            if((rater.size() != 0) && rater.get(rater.size()-1).getID().equals(rec.get("rater_id"))) {
                rater.get(rater.size()-1).addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
            }
            else {
                Rater raters = new PlainRater(rec.get("rater_id"));
                raters.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                rater.add(raters);
                
            }
        }
        return rater;
    }
    
    
    public void testLoadRaters(){
    
        String filename = "data/ratings.csv";
        ArrayList<Rater> rater = loadRaters(filename);
        ArrayList<String> DiffMovies = new ArrayList<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        numOfRatingsByRater(rater);
        numOfRatingsByMovie(rater);
        numOfRaters(rater);
        DifferentMovies(rater);
        MaxNumOfRatings(rater);
    }
   
    public void numOfRaters(ArrayList<Rater> rater){
        System.out.println("there are total " + rater.size() + " raters");
    }

    public void numOfRatingsByRater(ArrayList<Rater> rater){
         String rater_id = "193";
         for(Rater part : rater){
                 
           if(part.getID().equals(rater_id)){
             System.out.println("the rater_id #" + " " + rater_id + " " + "has " + part.numRatings() + " ratings");
             return;
           }
        }
       
      }

     public void numOfRatingsByMovie(ArrayList<Rater> rater){
       String movie_id = "1798709";
       for(Rater part : rater){
          if(part.hasRating(movie_id)){
             numByMovie ++ ;
          }
        
       }
       System.out.println("the movie_id # " + movie_id + " has " + numByMovie + " ratings");
    }
   
   public void MaxNumOfRatings(ArrayList<Rater> rater){
       HashMap<String, Integer> map = new HashMap<String, Integer>();
       for(Rater part: rater){
           if(!map.containsKey(part.getID()))
             map.put(part.getID(), 1);
           else{
               int maxNum = map.get(part.getID());
               map.put(part.getID(), maxNum++);
            }
        }
   
        int maxValue = Collections.max(map.values());
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue().equals(maxValue))
                System.out.println("Rater ID " + pair.getKey() + " has the maximum number of ratings, which is " + maxValue);
        }
    }
    
   public void DifferentMovies(ArrayList<Rater> rater){
       HashSet<String> movies = new HashSet<String>();
       for(Rater part : rater){
        ArrayList<String> item = part.getItemsRated();
            for(String s : item){
                if(!movies.contains(s))
                movies.add(s);
            }
        }
     System.out.println("DiffMovies "+movies.size()+" by all raters");
   }
    
   public static void main(String arg[]){
       FirstRatings fR = new FirstRatings();
       fR.testLoadMovies();
       fR.testLoadRaters();
}
}
