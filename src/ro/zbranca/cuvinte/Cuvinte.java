package ro.zbranca.cuvinte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cuvinte {

	public static void main(String[] args) {	
		Dictionary dictionary = new Dictionary();
		String dictionaryFilePath = "loc5.txt";
		HashMap<String,ArrayList<String>> indexedDictionary = dictionary.loadDictionaryFromFile(dictionaryFilePath);

		//String word = new String("aerisit");
		Scanner sc = new Scanner(System.in);
		System.out.println("'x' for close scanner");
		String word = "";
		while (!word.equalsIgnoreCase("x")){
		System.out.println("Enter some letters, then hit Enter : ");
		word = sc.nextLine();
		String sortedword = dictionary.sortLetters(word);
		if(indexedDictionary.containsKey(sortedword.toLowerCase())){ //used lowercase to make the words case sensitive
			System.out.println("anagrams from Dictionary   :  " + indexedDictionary.get(sortedword));
			System.out.println("");
		} else {
			System.out.println("No anagrams in dictionary");
		}
		}
		System.out.println("By");
		sc.close();
		System.exit(0);

	}


}
