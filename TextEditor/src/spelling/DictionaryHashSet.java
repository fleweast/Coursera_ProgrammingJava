/**
 * 
 */
package spelling;

import java.util.HashSet;

public class DictionaryHashSet implements Dictionary 
{

    private HashSet<String> words;
	
	public DictionaryHashSet()
	{
	    words = new HashSet<String>();
	}

	@Override
	public boolean addWord(String word) 
	{
		return words.add(word.toLowerCase());
	}

    @Override
	public int size()
	{
    	 return words.size();
	}

    @Override
	public boolean isWord(String s) {
    	return words.contains(s.toLowerCase());
	}
}
