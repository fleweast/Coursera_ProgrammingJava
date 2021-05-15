package textgen;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	private List<ListNode> wordList;
	private String starter;
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	@Override
	public void train(String sourceText)
	{
		String[] words = sourceText.split(" ");
		starter = words[0];
		String prevWord = starter;
		boolean exist = false;
		ListNode currNode = null;

		for (int i = 1; i < words.length; i++){

			for (ListNode n : wordList){
				if (prevWord.equals(n.getWord())){
					exist = true;
					currNode = n;
				}
			}
			if (exist) currNode.addNextWord(words[i]);
			else {
				ListNode newWord = new ListNode(prevWord);
				newWord.addNextWord(words[i]);
				wordList.add(newWord);
			}
			if (i == words.length-1){
				ListNode newWord = new ListNode(words[i]);
				newWord.addNextWord(starter);
				wordList.add(newWord);
			}
			prevWord = words[i];
			exist = false;
		}
	}

	@Override
	public String generateText(int numWords) {
		if (numWords <= 0){
			return "";
		}
	    String res = "";
	    String currWord = starter;
	    res+= starter + " ";
	    for (int i = 1; i < numWords; i++){
	    	for (ListNode n : wordList){
	    		if (currWord.equals(n.getWord())){
	    			ListNode currNode = n;
	    			String word = currNode.getRandomNextWord(this.rnGenerator);
	    			res += word+" ";
	    			currWord = word;
				}
			}
		}
	    return  res;
	}

	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		starter = "";
		train(sourceText);
	}

	public static void main(String[] args)
	{
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}
}

class ListNode
{
	private String word;
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		int randomInt = generator.nextInt(nextWords.size());
		String RandomNext = nextWords.get(randomInt);
		return RandomNext;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
}


