
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
   Location loc; 
   double maxDist;
    
    public DistanceFilter(Location currLoc, double max) { 
        loc = currLoc;
        maxDist = max;
    } 
    
    public boolean satisfies(QuakeEntry qe) { 
        double dist = loc.distanceTo(qe.getLocation());
        return (dist < maxDist);
    } 

    public String getName() {
        return "Distance";
    }

}
