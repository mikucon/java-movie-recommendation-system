
/**
 * Write a description of class GenreFilter here.
 * 
 * @author (Xiaozhe Li) 
 * @version (8/5/17)
 */
public class GenreFilter implements Filter
{
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
  
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}
