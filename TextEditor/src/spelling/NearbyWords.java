package spelling;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NearbyWords implements SpellingSuggest {
	private static final int THRESHOLD = 1000;
	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		for (int i = 0; i <= s.length(); i++){
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++){
				StringBuffer sb = new StringBuffer(s);
				sb.insert(i, (char)charCode);

				if(!currentList.contains(sb.toString()) &&
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		for (int i = 0; i < s.length(); i++){
			StringBuffer sb = new StringBuffer(s);
			sb.deleteCharAt(i);
			if(!currentList.contains(sb.toString()) &&
					(!wordsOnly||dict.isWord(sb.toString())) &&
					!s.equals(sb.toString())) {
				currentList.add(sb.toString());
			}
		}
	}
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		List<String> queue = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		List<String> retList = new LinkedList<String>();

		queue.add(word);
		visited.add(word);

		while (!queue.isEmpty() && retList.size() < numSuggestions){
			String cur = queue.remove(0);
			List<String> list = distanceOne(cur, true);
			for (String sug : list){
				if (!visited.contains(sug)){
					visited.add(sug);
					queue.add(sug);
					if (dict.isWord(sug)){
						retList.add(sug);
					}
				}
			}
		}
		
		return retList;
	}	

   public static void main(String[] args) {
	   String word = "hio";
	   Dictionary d = new DictionaryHashSet();

	   DictionaryLoader.loadDictionary(d, "C:\\Users\\Елена\\Downloads\\Course1StarterCode-master\\Course1StarterCode-master\\Coursera_ProgrammingJava\\TextEditor\\data\\dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");
	   System.out.println(l.size());

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
   }
}
