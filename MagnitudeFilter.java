
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    public MagnitudeFilter(double min,double max){
        minMag = min;
        maxMag = max;
    }
    public String getName(){
        return "Magnitude Filter";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude()>=minMag&&qe.getMagnitude()<=maxMag;
    }
}
