package ro.zbranca.cuvinte;

import java.util.Set;

public class Cuvinte {

	public static void main(String[] args) {
		Cuvinte cuvinte = new Cuvinte();
		cuvinte.go();
	}

	public void go(){

		Table table = new Table();
		Dictionary dictionary = new Dictionary();

		//load dictionary
		String dictionaryFilePath = "loc5.txt";
		dictionary.loadDictionaryFromFile(dictionaryFilePath);

		String lettersOnRack = "abcdefgijklmopqrstretrode";
		Set<String> allAnagrams = Dictionary.getAllAnagrams(lettersOnRack ,4,7);		
		System.out.println(allAnagrams.size() + " anagrams from Dictionary   :  " + allAnagrams);

		table.loadCurrentTable();
		System.out.println(" x" + table.getTableCellValue(7, 14)+"y");
		System.out.println(" x" + table.checkForAnchor(7, 15)+"y");

		System.out.println(dictionary.getHooksOnMiddle("ca","tat", "atprerts").toString());

		System.out.println(table.getCellTemplate(12, 10, 7)[0].toString()+table.getCellTemplate(12, 10, 7)[1].toString());

		//     	letterStock.put("a" ,1);
		//		String letterList		= "abcdefghijklmnopqrstuvwxyz";
		//		String letterValueList	= "19121899190141120111180909";

	}

}