
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMin; 
    private double magMax;
    
    public MagnitudeFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        double mag = qe.getMagnitude();
        return (mag >= magMin) && (mag <= magMax);
    } 
    
    public String getName() {
        return "Magnitude";
    }

}
