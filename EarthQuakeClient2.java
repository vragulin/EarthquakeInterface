import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        return parser.read(source);
    }
    
    
    public void quakesWithFilter() { 
        ArrayList<QuakeEntry> list  = getQuakeData();         
        System.out.println("read data for "+list.size()+" quakes");
        
        Location tokyo = new Location(35.42, 139.43);
        Location denver = new Location(39.7392, -104.9903);

        Filter mag = new MagnitudeFilter(3.5, 4.5);
        Filter dep = new DepthFilter(-55000.0, -20000.0);
        Filter dist = new DistanceFilter(denver, 1000000.0);
        Filter phrase = new PhraseFilter("end", "a");
        
        ArrayList<QuakeEntry> intList = filter(list, mag); 
        ArrayList<QuakeEntry> finList = filter(intList, dep);
        //ArrayList<QuakeEntry> intList = filter(list, dist); 
        //ArrayList<QuakeEntry> finList = filter(intList, phrase);
        for (QuakeEntry qe: finList) System.out.println(qe);  
        System.out.printf("Found %d matching.", finList.size());        
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "../data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        ArrayList<QuakeEntry> list  = getQuakeData();         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for (QuakeEntry qe: filtered) System.out.println(qe);      
        System.out.println(maf.getName());
        System.out.printf("Found %d matching.", filtered.size());
    }
    
    public void testMatchAllFilter2(){
        ArrayList<QuakeEntry> list  = getQuakeData();         
        System.out.println("read data for "+list.size()+" quakes");

        Location tulsa = new Location(36.1314, -95.9372);
        Location billund = new Location(55.7308, 9.1153) ;

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new DistanceFilter(billund, 3000000.0));
        maf.addFilter(new PhraseFilter("any", "e"));
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for (QuakeEntry qe: filtered) System.out.println(qe);      
        System.out.println(maf.getName());
        System.out.printf("Found %d matching.", filtered.size());
        
    }
}
