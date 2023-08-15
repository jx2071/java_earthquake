
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String place;
    private String phrase;
    public PhraseFilter(String where, String what){
        place = where;
        phrase = what;
    }
    public String getName(){
        return "Phrase Filter";
    }
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getTitle();
        //Search Phrase in Start String
        if(place.equals("start")){
            int index = title.indexOf(" ");//Fine first space index
            title = title.substring(0,index);
            if(title.contains(phrase)){
                return true;
            }
        }
        //Search Phrase in End String
        if(place.equals("end")){
            int index = title.lastIndexOf(" ");//Find last space index
            title = title.substring(index);
            if(title.contains(phrase)){
                return true;
            }
        }
        //Search Phrase in Entire String
        if(place.equals("any")){
            if(title.contains(phrase)){
                return true;
            }
        }
        return false;
    }
}
