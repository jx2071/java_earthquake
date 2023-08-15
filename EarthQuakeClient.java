import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    private ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    private ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location loc) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            Location quakeLoc = qe.getLocation();
            double distance = quakeLoc.distanceTo(loc);
            //System.out.println(distance);
            if(distance<=distMax){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    private ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
                                                double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            double depth = qe.getDepth();
            if(depth<minDepth&&depth>maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                 String place,String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            String title = qe.getTitle();
            //Search Phrase in Start String
            if(place.equals("start")){
                int index = title.indexOf(" ");//Fine first space index
                title = title.substring(0,index);
                if(title.contains(phrase)){
                    answer.add(qe);
                }
            }
            //Search Phrase in End String
            if(place.equals("end")){
                int index = title.lastIndexOf(" ");//Find last space index
                title = title.substring(index);
                if(title.contains(phrase)){
                    answer.add(qe);
                }
            }
            //Search Phrase in Entire String
            if(place.equals("any")){
                if(title.contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    
    private void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void deepQuakes(){
        ArrayList<QuakeEntry> list  = getQuakeData();
        System.out.println("=========Filter Quaks by Depth=========");
        double minDepth = -2000;
        double maxDepth = -4000;
        ArrayList<QuakeEntry> deepQuake = filterByDepth(list,minDepth,maxDepth);
        System.out.println("Find quakes with depth between "+minDepth+" and "+maxDepth);
        for(QuakeEntry qe:deepQuake){
            System.out.println(qe);
        }
        System.out.println("Found "+deepQuake.size()+" quakes that match that criteria");
    }
    
    public void bigQuakes() {
        ArrayList<QuakeEntry> list  = getQuakeData();
        System.out.println("=========Filter Quaks by Magnitude=========");
        ArrayList<QuakeEntry> bigQuake = filterByMagnitude(list,5.0);
        for(QuakeEntry qe:bigQuake){
            System.out.println(qe);
        }
        System.out.println("Found "+bigQuake.size()+" quakes that match that criteria");
    }

    public void closeQuakes(){
        ArrayList<QuakeEntry> list  = getQuakeData();
        System.out.println("=========Filter Quaks by Distance=========");
        // This location is Durham, NC
        Location durham = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location bridgeport =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuake = filterByDistanceFrom(list,1000000, bridgeport);
        for(QuakeEntry qe:closeQuake){
            System.out.println(qe.getLocation().distanceTo(bridgeport)/1000+"km away:  "+qe.getTitle());
        }
        System.out.println("Found "+closeQuake.size()+" quakes that match that criteria");
    }
    
    public void quakesByPhrase(){
        ArrayList<QuakeEntry> list  = getQuakeData();
        System.out.println("=========Filter Quaks by Pharse=========");
        String place = "end";
        String phrase = "Alaska";
        ArrayList<QuakeEntry> phraseQuake = filterByPhrase(list,place,phrase);
        for(QuakeEntry qe:phraseQuake){
            System.out.println(qe);
        }
        System.out.println("Found "+phraseQuake.size()+" quakes that match that criteria");
    }
    
    public void closestQuakes(){
        System.out.println("=========Finding # Closest Quakes=========");
        ClosestQuakes cq = new ClosestQuakes();
        String source = "data/nov20quakedata.atom";
        int number = 3;
        cq.findClosestQuakes(source,number);
    }
    
    public void largestQuakes(){
        System.out.println("=========Finding # Largest Quakes=========");
        LargestQuakes lq = new LargestQuakes();
        String source = "data/nov20quakedata.atom";
        int number = 20;
        lq.findLargestQuakes(source,number);
    }
    
    private ArrayList<QuakeEntry> getQuakeData(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        return list;
    }
    
    private void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
