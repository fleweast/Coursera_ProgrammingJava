import java.io.File;
import java.util.*;

import edu.duke.*;

public class VigenereBreaker {
    public static String sliceString(String message, int whichSlice, int totalSlices) {
        String res = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            res += message.charAt(i);
        }
        return res;
    }

    public static int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String ss = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(ss);
        }
        return key;
    }

    public static void breakVigenere() {
        FileResource fr = new FileResource();
        String temp = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        for (File f : dr.selectedFiles()) {
            FileResource dict = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dict);
            String lang = f.getName();
            languages.put(lang, dictionary);
        }
        breakForAllLangs(temp, languages);
    }

    public static HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }

    public static int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] allWords = message.split("\\W+");
        for (int i = 0; i < allWords.length; i++) {
            if (dictionary.contains(allWords[i].toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public static String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int bestCount = 0;
        String bestStr = "";
        char c = mostCommonCharIn(dictionary);
        int keyLength = 0;
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(keys);
            String tempDec = vc.decrypt(encrypted);
            int count = countWords(tempDec, dictionary);
            if (count > bestCount) {
                bestCount = count;
                bestStr = tempDec;
                keyLength = keys.length;
            }
        }
        return bestStr;
    }

    public static char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> chars = new HashMap();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                if (!chars.containsKey(word.charAt(i))) {
                    chars.put(word.charAt(i), 1);
                } else {
                    chars.put(word.charAt(i), chars.get(word.charAt(i)) + 1);
                }
            }
        }
        int maxCount = 0;
        char mostCommon = ' ';
        for (char c : chars.keySet()) {
            if (chars.get(c) > maxCount) {
                maxCount = chars.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }

    public static void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String res = "";
        String langu = "";
        for (String lang : languages.keySet()) {
            String tempRes = breakForLanguage(encrypted, languages.get(lang));
            int tempC = countWords(tempRes, languages.get(lang));
            if (tempC > maxCount) {
                maxCount = tempC;
                res = tempRes;
                langu = lang;
            }
        }
        System.out.println(res);
        System.out.println(langu);
    }

    /* First you need to choose what message you want to decrypt (all texts in VigenereTestData directory).
    After that you need to choose all languages in directory "dictionaries".
     */
    public static void main(String[] args) {
        breakVigenere();
    }

    public static void testMostCommonCharIn() {
        FileResource d = new FileResource();
        HashSet<String> dictionary = readDictionary(d);
        char res = mostCommonCharIn(dictionary);
        System.out.println(res);
    }

}


