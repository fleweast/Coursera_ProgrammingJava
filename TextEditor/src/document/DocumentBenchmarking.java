package document;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DocumentBenchmarking {

	
	public static void main(String [] args) {
	    int trials = 20;
	    String textfile = "TextEditor/data/warAndPeace.txt";
		int increment = 20000;
		int numSteps = 20;
		int start = 50000;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment 
		// instructions and following the pseudocode below.
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// numToCheck holds the number of characters that you should read from the 
			// file to create both a BasicDocument and an EfficientDocument.  

			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 * 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below.
			 * 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a BasicDocument 
			 *     b. Calls fleshScore on this document
			 * 4. Print out the time it took to complete the loop in step 3 
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 * 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an EfficientDocument 
			 *     b. Calls fleshScore on this document
			 * 6. Print out the time it took to complete the loop in step 5 
			 *      (on the same line as the first print statement) followed by a newline (\n) 
			 */
			System.out.print(numToCheck + "\t");
			String strFromFIle = getStringFromFile(textfile, numToCheck);
			int bdTime = 0;
			for (int i = 0; i < trials; i++){
				long startTime = System.nanoTime();
				BasicDocument bd = new BasicDocument(strFromFIle);
				bd.getFleschScore();
				long finishTime = System.nanoTime();
				bdTime += (finishTime - startTime)/100000;
			}
			System.out.print(bdTime/trials + "\t");
			int edTime = 0;
			for (int i = 0; i < trials; i++){
				long startTime = System.nanoTime();
				BasicDocument ed = new BasicDocument(strFromFIle);
				ed.getFleschScore();
				long finishTime = System.nanoTime();
				edTime += (finishTime - startTime)/100000;
			}
			System.out.print(edTime/trials + "\n");

		}
	
	}

	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
