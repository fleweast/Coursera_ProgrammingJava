package spelling;

import java.util.Locale;
import java.util.TreeSet;

public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
   int size = 0;

   public DictionaryBST() {
       dict = new TreeSet<String>();
   }

    public boolean addWord(String word) {
    	word = word.toLowerCase();
    	if (!dict.contains(word)){
    	    dict.add(word);
    	    size++;
    	    return true;
        }
        return false;
    }

    public int size()
    {
    	return size;
    }

    public boolean isWord(String s) {
    	s = s.toLowerCase();
    	if (dict.contains(s)){
    	    return true;
        }
    	return false;
    }
}
