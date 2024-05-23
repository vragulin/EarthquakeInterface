
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double depMin; 
    private double depMax;
    
    public DepthFilter(double min, double max) { 
        depMin = min;
        depMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        double depth = qe.getDepth();
        return (depth >= depMin) && (depth <= depMax);
    } 
    
        public String getName() {
        return "Depth";
    }

}
