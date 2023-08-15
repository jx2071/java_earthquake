
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location city;
    int maxDistance;
    public DistanceFilter(Location where, int max){
        city = where;
        maxDistance = max;
    }
    public String getName(){
        return "Distance Filter";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(city)<=maxDistance;
    }
}
