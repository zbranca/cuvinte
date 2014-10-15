package ro.zbranca.cuvinte;

import java.util.Set;

public class Cuvinte {


	public static void main(String[] args) {

		//load dictionary
		String dictionaryFilePath = "loc5.txt";
		Dictionary.loadDictionaryFromFile(dictionaryFilePath);

		String lettersOnRack = "nenoroc";
		Set<String> allAnagrams = Dictionary.getAllAnagrams(lettersOnRack ,4,7);		
		System.out.println(allAnagrams.size() + " anagrams from Dictionary   :  " + allAnagrams);


		//     	letterStock.put("a" ,1);
		//		String letterList		= "abcdefghijklmnopqrstuvwxyz";
		//		String letterValueList	= "19121899190141120111180909";

	}

}