import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * the lexer class is used to extract the tokens (lexemes) from the input String
 * you will define what tokens are valid in your language.
 * you will need some kind of data structure, probably some kind of a list
 * */

public class Lexer {

	private FileReader codeReader; // list of all code
	private ArrayList<Token> myTokens; // list of all identified tokens
	private KeyWords keywords; // list of all default keywords
	
	private String keywordsfilename;
	private String codefilename;
	
	private char[] Operators= {';','+','-','/','*',':','=','!','<','>'};
	private char[] Symbols= {'@','#','$','%','^','&','*','(',')','`','~','_','?','"','\''};
	private char[] Numbers= {'1','2','3','4','5','6','7','8','9','0','.'};
	
	public Lexer(String keywordfile, String codefile) throws FileNotFoundException {
		keywords= new KeyWords(keywordfile);
		codeReader= new FileReader(codefile);
		myTokens= new ArrayList<Token>();
	}
				
	//Prints the Keywords for the lexer	
	public void printKeywords() {
		keywords.printKeywords();
	}
	
	public void printCode() {
		System.out.println(codeReader.getCode());
	}
	
	// This method prints the Tokens (Lexemes), you can use another method if you need to
	public void printLexemes() {
		Iterator<Token> list= myTokens.iterator();
		while(list.hasNext()){
			System.out.println(list.next().tokenString());
		}
	}
	
	public String reverseString(String n) {
		String r="";
		for(int i=n.length()-1; i>=0; i--) {
			r+=n.charAt(i);
		}
		return r;
	}
	
	public boolean isOperator(char o) {
		for(int i=0; i<Operators.length; i++) {
			if(o==Operators[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSymbol(char s) {
		for(int i=0; i<Symbols.length; i++) {
			if(s==Symbols[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNumber(char n) {
		for(int i=0; i<Numbers.length; i++) {
			if(n==Numbers[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isNumberVariable(String id) {
		int tally=0;
		int pointTally=0;
		for(int i=0; i<id.length(); i++) {
			if(isNumber(id.charAt(i))) {
				tally++;
			}
			if(id.charAt(i)=='.') {
				pointTally++;
			}
		}
		if(tally==id.length() && pointTally<=1) {
			return true;
		}
		return false;
	}
	
	public boolean isID(String id) {
		for(int i=0; i<id.length(); i++) {
			if(isSymbol(id.charAt(i)) || isOperator(id.charAt(i))) {
				return false;
			}
		}
		if(id.equals("") || isNumber(id.charAt(0))) {
			return false;
		}
		return true;
	}
	
	// This method defines the keywords of your language
	public void defineKeywords() {
		
		ArrayList<Token> syntax= keywords.getKeywords(); //List of keywords from the file
		
		String c= codeReader.getCode();
		char[] code= c.toCharArray();
		
		String subsec="";  //current section of code being examined
		int sublength=0;
		for(int i=0; i<code.length; i++) {
			subsec+=code[i]; //Substring
			sublength= subsec.length(); //Substring length
						
			//Checking for Keywords, Operators and End of Statement
			Iterator<Token> syntaxList= syntax.iterator();
			while(syntaxList.hasNext()){
				Token temp= syntaxList.next();				
				if(temp.equalsT(subsec)){
					myTokens.add(new Token(subsec,temp.getType(),temp.getDesc())); //add new token to list
					//clear subsection for next iteration
					subsec="";
					sublength=0;
				}
			}
			
			//Checking for ID
			if(i+1<code.length && isOperator(code[i+1]) && isID(subsec)) {
				myTokens.add(new Token(subsec,"ID"));
				//clear subsection for next iteration
				subsec="";
				sublength=0;
			}
						
			//Checking for Strings
			if(sublength>0 && subsec.charAt(0)=='\'' && subsec.charAt(sublength-1)=='\'' && sublength!=1 ) {
				subsec=subsec.replaceAll("_"," "); //replace underscores with spaces
				myTokens.add(new Token(reverseString(subsec),"String")); //add string to the token list
				//clear subsection for next iteration
				subsec="";
				sublength=0;
			}
			
			//Checking for Comments
			if(sublength>0 && subsec.charAt(0)=='"' && subsec.charAt(sublength-1)=='"' && sublength!=1) {
				subsec=subsec.replaceAll("_"," "); //replace underscores with spaces
				myTokens.add(new Token(subsec,"Comment"));
				//clear subsection for next iteration
				subsec="";
				sublength=0;
			}
			
			//Checking for Integers and Decimals
			if(isNumberVariable(subsec) && i+1<code.length && isOperator(code[i+1]) && subsec!="") {
				myTokens.add(new Token(subsec,"Number"));
				//clear subsection for next iteration
				subsec="";
				sublength=0;
			}	
		}
	}
	
}
