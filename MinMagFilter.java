
/**
 * Write a description of MinMagFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter {
    private double magMin;
    public MinMagFilter(double min){
        magMin = min;
    }
    public String getName(){
        return "Min Magnitude Filter";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude()>magMin;
    }
}
