package ro.zbranca.cuvinte;

import java.util.ArrayList;
import java.util.Set;

public class Cuvinte {


	public static void main(String[] args) {

		//load dictionary
		String dictionaryFilePath = "loc5.txt";
		Dictionary.loadDictionaryFromFile(dictionaryFilePath);

		String lettersOnRack = new String("redecorare");
		Set<String> allAnagrams = Dictionary.getAllAnagrams(lettersOnRack);
		

		ArrayList<String> scrabbleCurrentTable = new ArrayList<String>();

		//                        012345678901234
		scrabbleCurrentTable.add("               "); //0
		scrabbleCurrentTable.add("               "); //1
		scrabbleCurrentTable.add("          o    "); //2
		scrabbleCurrentTable.add("         ti    "); //3
		scrabbleCurrentTable.add("         i     "); //4
		scrabbleCurrentTable.add("         n     "); //5      //LETTERS NOW ON THE BOARD
		scrabbleCurrentTable.add("       c t     "); //6
		scrabbleCurrentTable.add("       a i     "); //7
		scrabbleCurrentTable.add("    scapat     "); //8
		scrabbleCurrentTable.add("       a       "); //9
		scrabbleCurrentTable.add("       t       "); //10
		scrabbleCurrentTable.add("       a       "); //11
		scrabbleCurrentTable.add("      ai       "); //12
		scrabbleCurrentTable.add("      r        "); //13
		scrabbleCurrentTable.add("               "); //14 
		//                        012345678901234

		//        HashMap <String, Integer> letterStock = new HashMap<String, Integer>();
		//        letterStock.put("a" ,1);
		//		String letterList		= "abcdefghijklmnopqrstuvwxyz";
		//		String letterValueList	= "19121899190141120111180909";

		
			System.out.println(allAnagrams.size() + " anagrams from Dictionary   :  " + allAnagrams);
		

	}
}