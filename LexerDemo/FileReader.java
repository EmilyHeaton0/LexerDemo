import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//class used to read in code
public class FileReader {
	
	private Scanner filescanner; //scanner used to read file
	private String filename; //filename associated with the scanner
	
	private String Code; //code read from the scanner
	

	public FileReader(String name) throws FileNotFoundException {
		filescanner= new Scanner(new File(name));
		filename= name;
		readCode();
	}
	
	public String getCode(){
		return Code;
	}
	

	public void readCode() {
		while(filescanner.hasNextLine()) {
			Code+=filescanner.nextLine();
		}
		Code=Code.substring(4,(Code.length())); //remove the "null" that gets added to the beginning of Code
		Code=Code.replaceAll("\\s+",""); //remove extra spaces
	}

}
