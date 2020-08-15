import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * this class allows you to define keywords of your language 
 * you will store the keywords in a hash table for easier access 
 *( you can use a different data structure if you want).
 * each keyword needs a description on what it does
 * */


public class KeyWords {
	
	private ArrayList<Token> KeywordList;
	private Scanner filescanner;
	
	//Reads through a file listing all the keywords and their description
	//creates a token for each keyword and adds it to an array list
	//file formating: keyword,type,description
	public KeyWords(String keywordsFile) throws FileNotFoundException {
		filescanner= new Scanner(new File(keywordsFile));
		KeywordList= new ArrayList<Token>();
		while(filescanner.hasNextLine()) {
			String keyword= filescanner.nextLine();
			String[] components= keyword.split(",");			
			String value= components[0];
			String type= components[1];
			String desc= components[2];
			Token newT= new Token(value,type,desc);
			KeywordList.add(newT);
		}
	}
	
	//Prints the keywords
	public void printKeywords() {
		Iterator<Token> list= KeywordList.iterator();
		while(list.hasNext()){
			System.out.println(list.next().tokenString());
		}
	}
	
	//Returns the ArrayList of keywords
	public ArrayList<Token> getKeywords() {
		return KeywordList;
	}
	
}
