import edu.duke.*;
import java.util.*;

/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int number){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        
        for(int k=0;k<number;k++){
            int index = 0;
            double maxMag = copy.get(index).getMagnitude();
            for(int i =1;i<copy.size();i++){
                double currMag = copy.get(i).getMagnitude();
                if(currMag>maxMag){
                    index = i;
                    maxMag=currMag;
                }
            }
            answer.add(copy.get(index));
            copy.remove(index);
        }
        return answer;
    }
    
    public void findLargestQuakes(String source, int number){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        
        ArrayList<QuakeEntry> largest = getLargest(list,number);
        for(QuakeEntry qe:largest){
            System.out.println(qe);
        }
        System.out.println("number found: "+largest.size());
    }
}
