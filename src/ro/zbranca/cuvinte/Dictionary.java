package ro.zbranca.cuvinte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary {

	public static HashMap<String,ArrayList<String>> indexedDictionary;
	public static Set<String> allAnagrams = new TreeSet<String>();

	//	load dictionary from given file and fill hashMap 
	public void loadDictionaryFromFile (String filePath) {

		indexedDictionary =new HashMap<String, ArrayList<String>>();
		String lineFromTextDictionary;
		BufferedReader textDictionary;

		try {
			textDictionary = new BufferedReader(new FileReader(filePath));

			while ((lineFromTextDictionary = textDictionary.readLine()) != null) {
				String sortedDictionaryLine = sortLetters(lineFromTextDictionary);
				if(!indexedDictionary.containsKey(sortedDictionaryLine)){
					ArrayList<String> initiedArray = new ArrayList<String>();
					indexedDictionary.put(sortedDictionaryLine,initiedArray);
				}

				indexedDictionary.get(sortedDictionaryLine).add(lineFromTextDictionary);
			}

			textDictionary.close();

		} catch (IOException e) {
			System.out.println("Dictionary file not found");
		}
		// TODO
		//remove this after testing phase
		int numOfkeys = indexedDictionary.size();
		System.out.println("We have " + numOfkeys + " keys in Dictionary");
	}

	public static Set<String> getAllAnagrams (String lettersOnRack, int minLength, int maxLength){
		allAnagrams.clear();
		combinations(sortLetters(lettersOnRack), minLength, maxLength);
		return allAnagrams;
	}


	//	sort letters alphabetically in a string
	// for search or add key in indexedDictionary
	public static String sortLetters (String word) { 
		char[] c = word.toCharArray();		//Convert to array of chars 
		java.util.Arrays.sort(c);			//Sort
		String newString = new String(c);	//Convert back to String
		return newString;
	}

	// loader for combinations algorithm
	public static void combinations(String s, int minLength, int maxLength) { combinations("", s, minLength, maxLength); }

	// combination algorithm, give all unique combinations of given letters
	// filtered by desired length
	private static void combinations(String prefix, String s, int minLength, int maxLength) {
		if (s.length() > 0) {
			String partialCombination = prefix + s.charAt(0);
			if( partialCombination.length() >= minLength
					&& partialCombination.length() <= maxLength
					&& indexedDictionary.containsKey(partialCombination))
			{
				allAnagrams.addAll(indexedDictionary.get(partialCombination))		;
			}
			combinations(prefix + s.charAt(0), s.substring(1), minLength, maxLength);
			combinations(prefix,               s.substring(1), minLength, maxLength);
		}
	}


	// get single letters that fit in front or in back of words
	// receive word to check and letters from rack
	// first element of return string are left hooks
	// and second element are right hooks
	
	// hooks in front of word
	public String getHooksOnFront(String word, String rackLetters) {
		String hooks ="";
		for (int i = 0; i< rackLetters.length(); i++) {
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(word+rackLetters.charAt(i),
					word.length()+1,word.length()+1);
			if (allHookAnagrams.contains(rackLetters.charAt(i) + word))
				hooks=hooks+rackLetters.charAt(i);	
			allHookAnagrams.clear();
		}
		return hooks;
	}
	
	//hooks in back of word
	public String getHooksOnBack(String word, String rackLetters) {
		String hooks ="";
		for (int i = 0; i< rackLetters.length(); i++) {
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(word+rackLetters.charAt(i),
					word.length()+1,word.length()+1);
			if (allHookAnagrams.contains(word + rackLetters.charAt(i)))
				hooks=hooks+rackLetters.charAt(i);
			allHookAnagrams.clear();
		}
		return hooks;
	}
	
	//hooks between 2 words
	public String getHooksOnMiddle(String wordFirst, String wordSecond, String rackLetters) {
		String hooks ="";
		for (int i = 0; i< rackLetters.length(); i++) {
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(wordFirst+wordSecond+rackLetters.charAt(i),
					wordFirst.length()+wordSecond.length()+1
					,wordFirst.length()+wordSecond.length()+1);
			if (allHookAnagrams.contains(wordFirst + rackLetters.charAt(i)+wordSecond))
				hooks=hooks+rackLetters.charAt(i);
			allHookAnagrams.clear();
		}
		return hooks;
	}
}
