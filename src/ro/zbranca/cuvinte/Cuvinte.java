package ro.zbranca.cuvinte;

import java.util.Set;
import java.util.Arrays;

public class Cuvinte {

	public static void main(String[] args) {
		Cuvinte cuvinte = new Cuvinte();
		cuvinte.go();
	}

	public void go() {

		Table table = new Table();
		Dictionary dictionary = new Dictionary();

		String letterList = "abcdefghijklmnopqrstuvwxyz";
		String letterOnFirstStock = "abcdefghijlmnopqrstuvxz";
		String letterOnCurrentStock = "ghijlmno";

		// load dictionary
		String dictionaryFilePath = "loc5.txt";
		dictionary.loadDictionaryFromFile(dictionaryFilePath);

		// Searching can be global, on all positions of table with just letters
		// from rack, or specific, adding constrains of position
		String lettersOnRack = "retrode";
		Set<String> allAnagrams = Dictionary
				.getAllAnagrams(lettersOnRack, 4, 7);
		System.out.println(allAnagrams.size() + " anagrams from Dictionary :"
				+ allAnagrams);

		table.loadCurrentTable();

		String[] cellTemplatePrint = table.getCellTemplate(6, 8, 7);
		for (int i = 0; i < cellTemplatePrint.length; i++) {
			System.out.println(cellTemplatePrint[i]);
		}

		// letterStock.put("a" ,1);
		// String letterValueList = "19121899190141120111180909";
	}
}