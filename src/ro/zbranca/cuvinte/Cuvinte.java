package ro.zbranca.cuvinte;

import java.util.Set;

public class Cuvinte {

	public static void main(String[] args) {
		Cuvinte cuvinte = new Cuvinte();
		cuvinte.go();
	}

	public void go() {

		Table table = new Table();
		Dictionary dictionary = new Dictionary();

		// TODO
		// all this will be initialized null when tour engine will start

		// all letters from dictionary, with letters that stock don't have, that
		// will be possibly covered with jokers
		String letterList = "abcdefghijklmnopqrstuvwxyz";

		// real scrabble tiles letters
		String letterOnFirstStock = "abcdefghijlmnopqrstuvxz";

		// what letteres we have now, for jokers possible values and for "joker"
		// variant of game
		String letterOnCurrentStock = "ghijlmno"; // for joker info

		// letters on rack now
		String lettersOnRack = "retrode";

		// load dictionary
		String dictionaryFilePath = "loc5.txt";
		dictionary.loadDictionaryFromFile(dictionaryFilePath);

		// Searching can be global, on all positions of table with just letters
		// from rack, or specific, adding constrains of position

		table.loadCurrentTable();

		// TODO delete those 3 variables
		// mimic initialized search of cell
		int pozX = 6;
		int pozY = 8;
		int lettersOfTour = lettersOnRack.length();
		String[] currentCellTemplate = table.getCellTemplate(pozX, pozY,
				lettersOfTour);

		System.out.println("template from table:");
		for (int i = 0; i < currentCellTemplate.length; i++) {
			System.out.println(currentCellTemplate[i]);
		}

		// search with template
		Set<String> allCellAnagrams = dictionary.getCellAnagrams(lettersOnRack,
				currentCellTemplate);
		
		// letterStock.put("a" ,1);
		// String letterValueList = "19121899190141120111180909";
	}
}