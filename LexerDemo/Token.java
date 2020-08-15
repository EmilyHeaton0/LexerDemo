	/* Token class is needed for you to be able to create token object
	 * you need to have a type and a value for each token.
	 * you need to be able to st and get the types and values of each token
	 * */

public class Token {
	
	private String value;	
	private String type;
	private String description;
	
	//Tokens with a description
	public Token(String v,String t,String d) {
		value=v;
		type=t;
		description=d;
	}
	
	//Tokens without a description
	public Token(String v,String t) {
		value=v;
		type=t;
	}
	
	public String getValue() {
		return value;
	}
	public String getType() {
		return type;
	}
	public String getDesc() {
		return description;
	}
	
	public void setType(String t) {
		type=t;
	}
	public void setDesc(String d) {
		description=d;
	}
	
	//Compares Tokens, used to compare detected keywords to the keyword list
//	public boolean equals(Token T2) {
//		if(this.value.equals(T2.getValue())){
//			return true;
//		}
//		return false;
//	}
	
	//Compares Default Tokens (the Keywords) to String values found in the code
	public boolean equalsT(String T2) {
		if(this.value.equals(T2)){
			return true;
		}
		return false;
	}
	
	//Token toString method
	public String tokenString() {
		if(description!=null) {
			return("Value: "+value+", Type: "+type+", Description: "+description);
		}
		return("Value: "+value+", Type: "+type);
	}
	
}


