package ro.zbranca.cuvinte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {


	HashMap<String,ArrayList<String>> indexedDictionary =new HashMap<String, ArrayList<String>>();

	//load dictionary from file and fill hashMap 

	public HashMap<String,ArrayList<String>> loadDictionaryFromFile (String filePath ) {

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
		int numOfkeys = indexedDictionary.size();
		System.out.println("We have " + numOfkeys + " keys");
		return indexedDictionary;
	}

	//sort letters alphabetically in a string, for key of Map
	//and to search Map by same form of key

	public String sortLetters (String word) {  
		char[] c = word.toCharArray();     //Convert to array of chars 
		java.util.Arrays.sort(c);          //Sort
		String newString = new String(c);  //Convert back to String
		return newString;    
	}

}
