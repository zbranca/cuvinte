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

	//	load dictionary from file and fill hashMap 
	public static void loadDictionaryFromFile (String filePath) {

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

	public static Set<String> getAllAnagrams (String lettersOnRack){
		allAnagrams.clear();
		combinations(sortLetters(lettersOnRack));
		return allAnagrams;
	}


	//	sort letters alphabetically in a string, for key of Map
	//	and to search Map by same form of key
	public static String sortLetters (String word) { 
		char[] c = word.toCharArray();		//Convert to array of chars 
		java.util.Arrays.sort(c);			//Sort
		String newString = new String(c);	//Convert back to String
		return newString;
	}

	// print all subsets of the characters in s
	public static void combinations(String s) { combinations("", s); }

	// print all subsets of the remaining elements, with given prefix 
	private static void combinations(String prefix, String s) {
		if (s.length() > 0) {
			if( indexedDictionary.containsKey(prefix + s.charAt(0))){
				allAnagrams.addAll(indexedDictionary.get(prefix + s.charAt(0)));
			}
			combinations(prefix + s.charAt(0), s.substring(1));
			combinations(prefix,               s.substring(1));
		}
	} 

}
