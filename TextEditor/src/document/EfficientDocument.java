package document;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EfficientDocument extends Document {

	private int numWords;
	private int numSentences;
	private int numSyllables;
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}

	private boolean isWord(String tok)
	{
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}

	private void processText()
	{
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		numSentences = getTokens("[^.|^?|^!]+").size();
		numWords = getTokens("[A-Za-z]+").size();
		for (String word : getTokens("[A-Za-z]+")){
			numSyllables += countSyllables(word);
		}
	}
	protected int countSyllables(String word)
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

	@Override
	public int getNumSentences() {
		return numSentences;
	}

	@Override
	public int getNumWords() {
		return  numWords;
	}

	@Override
	public int getNumSyllables() {
        return numSyllables;
	}

	public static void main(String[] args)
	{
	    testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new EfficientDocument("Segue"), 2, 1, 1);
		testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
	}
	

}
