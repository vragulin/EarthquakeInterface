
/**
 * Write a description of PhraseFilter here.
 * 
 * @author V. Ragulin - 23-May-24
 * @version (a version number or a date)
 */
import java.util.regex.Pattern;

public class PhraseFilter implements Filter {

    private Pattern p;
    
    public PhraseFilter(String where, String phrase) { 

        switch (where) {
            case "start":
                p = Pattern.compile("^"+phrase);
                break;
            case "end":
                p = Pattern.compile(phrase+"$");
                break;
            default:
                p = Pattern.compile(phrase);
                break;                
        }
    } 
    
    public boolean satisfies(QuakeEntry qe) { 
        return p.matcher(qe.getInfo()).find();
    } 
    
    public String getName() {
        return "Phrase";
    }

}