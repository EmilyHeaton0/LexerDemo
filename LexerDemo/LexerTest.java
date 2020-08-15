import java.io.FileNotFoundException;

public class LexerTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		Lexer myLexer= new Lexer("KeywordList.txt","FinalDemoCode.txt");
		myLexer.defineKeywords();
		myLexer.printLexemes();

	}

}
