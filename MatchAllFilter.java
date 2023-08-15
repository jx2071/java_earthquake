import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public String getName(){
        String[] names = new String[filters.size()];
        for(int i =0;i<names.length;i++){
            names[i]=filters.get(i).getName();
        }
        return Arrays.toString(names);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
