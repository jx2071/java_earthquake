import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    private ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location tokyo = new Location(35.42, 139.43);
        
        Filter f1 = new MagnitudeFilter(4.0,5.0); 
        Filter f2 = new DepthFilter(-12000,-35000);
        ArrayList<QuakeEntry> answer1 = filter(list, f1);
        ArrayList<QuakeEntry> answer2 = filter(answer1, f2);
        for (QuakeEntry qe: answer2) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+answer2.size()+" Match the Search");
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location tulsa = new Location(36.1314, -95.9372);
        Location denver = new Location(39.7392, -104.9903);
        Location billund = new Location(55.7308, 9.1153);
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0,5.0);
        //Filter f2 = new DepthFilter(-30000,-180000);
        Filter f3 = new PhraseFilter("end","a");
        Filter f4 = new DistanceFilter(denver,1000000);
        //maf.addFilter(f1);
        //maf.addFilter(f2);
        maf.addFilter(f3);
        maf.addFilter(f4);
        ArrayList<QuakeEntry> answer = filter(list,maf);
        System.out.println("Filter Used: "+maf.getName());
        for(QuakeEntry qe:answer){
            System.out.println(qe);
        }
        System.out.println("Found "+answer.size()+" Match the Search");
    }
    private void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    private void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
