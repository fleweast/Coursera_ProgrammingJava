package spelling;

public class DictionaryBenchmarking {

    public static void main(String[] args) {
        int trials = 500;
        String dictFile = "data/dict.txt";
        int increment = 2000;
        int numSteps = 20;
        int start = 50000;

        String notInDictionary = "notaword";

        for (int numToCheck = start; numToCheck < numSteps * increment + start;
             numToCheck += increment) {
            DictionaryLL llDict = new DictionaryLL();
            DictionaryBST bstDict = new DictionaryBST();

            DictionaryLoader.loadDictionary(llDict, dictFile, numToCheck);
            DictionaryLoader.loadDictionary(bstDict, dictFile, numToCheck);

            long startTime = System.nanoTime();
            for (int i = 0; i < trials; i++) {
                llDict.isWord(notInDictionary);
            }
            long endTime = System.nanoTime();
            long timeLL = (endTime - startTime);

            startTime = System.nanoTime();
            for (int i = 0; i < trials; i++) {
                bstDict.isWord(notInDictionary);
            }
            endTime = System.nanoTime();
            long timeBST = (endTime - startTime);

            System.out.println(numToCheck + "\t" + timeLL + "\t" + timeBST);
        }
    }
}
