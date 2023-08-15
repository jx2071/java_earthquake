
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    private ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int number) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        
        for(int k=0;k<number;k++){
            int index = 0;
            double minDis = current.distanceTo(copy.get(index).getLocation());
            for(int i =1;i<copy.size();i++){
                double distance = current.distanceTo(copy.get(i).getLocation());
                if(distance<minDis){
                    index = i;
                    minDis = distance;
                }
            }
            answer.add(copy.get(index));
            copy.remove(index);
        }
        return answer;
    }

    public void findClosestQuakes(String source, int number) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,number);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
