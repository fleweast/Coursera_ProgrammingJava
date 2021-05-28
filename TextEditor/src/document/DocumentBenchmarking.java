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

		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
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
