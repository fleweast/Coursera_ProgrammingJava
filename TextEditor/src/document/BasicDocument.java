package document;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}

	@Override
	public int getNumWords()
	{
		List<String> tokens = getTokens("[A-Za-z]+");
		return  tokens.size();
	}

	@Override
	public int getNumSentences()
	{
		List<String> tokens = getTokens("[^.|^?|^!]+");
		return tokens.size();
	}

	@Override
	public int getNumSyllables()
	{
		int countAllSylables = 0;
		for (String word : getTokens("[A-Za-z]+")){
			countAllSylables += countSyllables(word);
		}
		return countAllSylables;
	}

	/*protected int countSyllables(String word)
	{
		int count = 0;
		word = word.toLowerCase();

		if (word.charAt(word.length()-1) == 'e') {
			if (silente(word)){
				String newword = word.substring(0, word.length()-1);
				count = count + countit(newword);
			} else {
				count++;
			}
		} else {
			count = count + countit(word);
		}
		return count;
	}

	 */

	private int countit(String word) {
		int count = 0;
		Pattern splitter = Pattern.compile("[^aeiouy]*[aeiouy]+");
		Matcher m = splitter.matcher(word);

		while (m.find()) {
			count++;
		}
		return count;
	}

	private boolean silente(String word) {
		word = word.substring(0, word.length()-1);

		Pattern yup = Pattern.compile("[aeiouy]");
		Matcher m = yup.matcher(word);

		if (m.find()) {
			return true;
		} else return false;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
	}
}
