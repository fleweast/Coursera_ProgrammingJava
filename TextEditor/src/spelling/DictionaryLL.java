package spelling;

import java.util.LinkedList;
import java.util.Locale;

public class DictionaryLL implements Dictionary {

    private LinkedList<String> dict;
    int size = 0;

    public DictionaryLL() {
        dict = new LinkedList<String>();
    }

    public boolean addWord(String word) {
        word = word.toLowerCase();
        if (!dict.contains(word)) {
            dict.add(word);
            size++;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isWord(String s) {
        s = s.toLowerCase();
        return dict.contains(s);
    }
}
