
/**
 * Write a description of class RecommendationRunner here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/30/17)
 */

import java.util.*;
import edu.duke.*;

public class RecommendationRunner implements Recommender
{

    public ArrayList<String> getItemsToRate(){
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        int minimalRaters = 5;
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int rat = rand.nextInt(movies.size());
            String title = movies.get(rat);
            if (!result.contains(title)) {
                result.add(title);
            }
        }
        return result;
    }
    
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,1,1);
        int length  = 10;
        if(ratings.size() < 10){
            length = ratings.size();
        }
        if(ratings.size() == 0){
            int index = 0;
            ArrayList<String> movie = MovieDatabase.filterBy(new TrueFilter());
            HashSet <String> titles = new HashSet <String>();
            Random rand = new Random();
            for(int i =0; i < 10; i++){
                int rat = rand.nextInt(movie.size());
                String title = movie.get(rat);
                if (ratings.size()!=0 && !titles.contains(title)) {
                    ratings.add(new Rating(title, 5.00));
                    titles.add(title);
                    index++;
                }
            }
        }

        
        for(int i=0; i< 10; i++) {
            System.out.println(MovieDatabase.getPoster(ratings.get(i).getItem())+" ");
            System.out.println(MovieDatabase.getTitle(ratings.get(i).getItem())+" ");
            System.out.println(MovieDatabase.getCountry(ratings.get(i).getItem())+" ");
            System.out.println(MovieDatabase.getYear(ratings.get(i).getItem())+" ");
            System.out.println(MovieDatabase.getMinutes(ratings.get(i).getItem())+" Minutes"+" ");
        }       
    }
}

