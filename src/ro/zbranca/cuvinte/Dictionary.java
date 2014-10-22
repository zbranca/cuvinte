package ro.zbranca.cuvinte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary {

	// dictionary is loaded in a hash map one time for entire session
	// every word is first sorted alphabetically, resulting the key,
	// this key is added to map with word added as item. A key
	// can have multiple items, representing anagrams for that key
	public static HashMap<String, ArrayList<String>> indexedDictionary;

	// this is the set where at each search in dictionary founded words
	// are added, cleared at every calling
	public static Set<String> allAnagrams;

	// load dictionary from text source file and fill hashMap
	// if key resulted sorting letters is found, add words,
	// if not found, create key and add words
	public void loadDictionaryFromFile(String filePath) {

		indexedDictionary = new HashMap<String, ArrayList<String>>();
		String lineFromTextDictionary; // aspect every word as new line in file
		BufferedReader textDictionary;

		try {
			textDictionary = new BufferedReader(new FileReader(filePath));

			while ((lineFromTextDictionary = textDictionary.readLine()) != null) {
				String sortedDictionaryLine = sortLetters(lineFromTextDictionary);
				if (!indexedDictionary.containsKey(sortedDictionaryLine)) {
					// if key is missing, make an array to keep words as items
					ArrayList<String> initiedArray = new ArrayList<String>();
					indexedDictionary.put(sortedDictionaryLine, initiedArray);
				}
				// if key exist, add new word
				indexedDictionary.get(sortedDictionaryLine).add(
						lineFromTextDictionary);
			}
			textDictionary.close();

		} catch (IOException e) {
			System.out.println("Dictionary file not found or is not accesible");
		}
		// TODO
		// remove this after testing phase
		int numOfkeys = indexedDictionary.size();
		System.out.println("We have " + numOfkeys + " keys in Dictionary");
	}

	// simple alphabetically sort of letters as a string
	// for search anagrams or make key for indexedDictionary
	public static String sortLetters(String word) {
		char[] c = word.toCharArray(); // Convert to array of chars
		java.util.Arrays.sort(c); // Sort
		String newString = new String(c); // Convert back to String
		return newString;
	}

	// Return all words from dictionary possible to form with letters
	// from rack. Min and max of length of words for return is give.
	// This method prepare a empty Set of strings to be filled with
	// words extracted from dictionary.
	// used for hooks
	public Set<String> getAllAnagrams(String lettersOnRack,
			int minLength, int maxLength) {
		if (allAnagrams == null) {
			allAnagrams = new TreeSet<String>();
		}
		allAnagrams.clear();
		// launch combination algorithm with an empty prefix
		combination("", sortLetters(lettersOnRack), minLength, maxLength);
		return allAnagrams;
	}

	// combination algorithm, make all unique combinations of given letters
	// taking a prefix and then divide the rest of letters recurrent
	// Before sending combination to search in indexed dictionary,
	// a filter of desired length is applied, to block undesired length
	private static void combination(String prefix, String s, int minLength,
			int maxLength) {
		if (s.length() > 0) {
			String partialCombination = prefix + s.charAt(0);
			if (partialCombination.length() >= minLength
					&& partialCombination.length() <= maxLength
					&& indexedDictionary.containsKey(partialCombination)) {
				allAnagrams.addAll(indexedDictionary.get(partialCombination));
			}
			combination(prefix + s.charAt(0), s.substring(1), minLength,
					maxLength);
			combination(prefix, s.substring(1), minLength, maxLength);
		}
	}

	// the hook is a letter that can be put in front or at back
	// of a word to make new legal word by adjacency. this methods
	// receive word to check for hooks and letters from rack

	// hooks in front of word
	public String getHooksOnFront(String word, String rackLetters) {
		String hooks = "";
		for (int i = 0; i < rackLetters.length(); i++) {
			if( hooks.contains("" + rackLetters.charAt(i)))
				continue;
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(word + rackLetters.charAt(i),
					word.length() + 1, word.length() + 1);
			if (allHookAnagrams.contains(rackLetters.charAt(i) + word))
				hooks = hooks + rackLetters.charAt(i);
			allHookAnagrams.clear();
		}
		return hooks;
	}

	// hooks in back of word
	public String getHooksOnBack(String word, String rackLetters) {
		String hooks = "";
		for (int i = 0; i < rackLetters.length(); i++) {
			if( hooks.contains("" + rackLetters.charAt(i)))
				continue;
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(word + rackLetters.charAt(i),
					word.length() + 1, word.length() + 1);
			if (allHookAnagrams.contains(word + rackLetters.charAt(i)))
				hooks = hooks + rackLetters.charAt(i);
			allHookAnagrams.clear();
		}
		return hooks;
	}

	// hooks between 2 words
	public String getHooksOnMiddle(String wordFirst, String wordSecond,
			String rackLetters) {
		
		String hooks = "";
		for (int i = 0; i < rackLetters.length(); i++) {
			if( hooks.contains("" + rackLetters.charAt(i)))
				continue;
			Set<String> allHookAnagrams;
			allHookAnagrams = getAllAnagrams(wordFirst + wordSecond
					+ rackLetters.charAt(i),
					wordFirst.length() + wordSecond.length() + 1,
					wordFirst.length() + wordSecond.length() + 1);
			if (allHookAnagrams.contains(wordFirst + rackLetters.charAt(i)
					+ wordSecond))
				hooks = hooks + rackLetters.charAt(i);
			allHookAnagrams.clear();
		}
		return hooks;
	}

	// search for anagrams from given cell template and rack letters
	// keep valid anagrams in allCellAnagrams for return
	public Set<String> getCellAnagrams(String lettersOnRack,
			String[] template) {
		
		Set<String> allCellAnagrams = null;
		int numberOfLetters = lettersOnRack.length();

		// work cell template to replace words with letters in anchor position
		String[] dictionaryCellTemplate = replaceHooksInTemplate(lettersOnRack, template);
		
		for (int i = 0; i< dictionaryCellTemplate.length; i++){
			System.out.println(dictionaryCellTemplate[i]);
		}

		return allCellAnagrams;
	}

	// here replace anchor words with hook letters in cellTemplate
	public String[] replaceHooksInTemplate(String lettersOnRack,
			String[] cellTemplate) {

		// anchor words begin with position 1 on template array
		int position = 1;

		// look at every letter in template
		for (int y = 0; y < cellTemplate[0].length(); y++) {

			// isolate one letter
			
			String templatePositionContent = "" + cellTemplate[0].charAt(y);

			// test if it's anchor position

			// if it's top anchor position
			if (templatePositionContent.equals("<")) {
				cellTemplate[position] = getHooksOnBack(
						cellTemplate[position], lettersOnRack);
				position++; // advance in array template position
			}

			// if it's bottom anchor position
			if (templatePositionContent.equals(">")) {
				cellTemplate[position] = getHooksOnFront(
						cellTemplate[position], lettersOnRack);
				position++; // advance in array template position
			}

			// if it's middle anchor position
			if (templatePositionContent.equals("@")) {
				String[] words = cellTemplate[position].split("@");
				cellTemplate[position] = getHooksOnMiddle(words[0],
						words[1], lettersOnRack);
				position++; // advance in array template position
			}

			// if isn't anchor position, let it go
		}

		return cellTemplate;
	}

}
