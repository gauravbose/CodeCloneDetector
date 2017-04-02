
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * This class Tokenizes the given file creates a String of tokens as an output. It will also ignore the comments before tokenizing.
 * The assumption here is that the code will be well formatted , that is the operators and the identifiers are separated by spaces.
 */
public class Tokenizer {

	Map<String, String> tokenList = new HashMap<String, String>();
	Tokens tokens = new Tokens();
	String delimiters = "\" : ~ ? ! // /* */ ; { } [ ] ( ) =  ";
	String seperator = "|";
	String tokenStream = null;

	public Tokenizer() {
		tokenList.put(Tokens.ABSTRACT, "tok_ABSTRACT");
		tokenList.put(Tokens.ASSERT, "tok_ASSERT");
		tokenList.put(Tokens.BOOLEAN, "tok_BOOLEAN");
		tokenList.put(Tokens.BREAK, "tok_BREAK");
		tokenList.put(Tokens.BYTE, "tok_BYTE");
		tokenList.put(Tokens.BYTE_CLASS, "tok_BYTE");
		tokenList.put(Tokens.CASE, "tok_CASE");
		tokenList.put(Tokens.CATCH, "tok_CATCH");
		tokenList.put(Tokens.CHAR, "tok_CHAR");
		tokenList.put(Tokens.CLASS, "tok_CLASS");
		tokenList.put(Tokens.CONSTANT, "tok_CONSTANT");
		tokenList.put(Tokens.CONTINUE, "tok_CONTINUE");
		tokenList.put(Tokens.DEFAULT, "tok_DEFAULT");
		tokenList.put(Tokens.DO, "tok_DO");
		tokenList.put(Tokens.DOUBLE, "tok_DOUBLE");
		tokenList.put(Tokens.DOUBLE_CLASS, "tok_DOUBLE");
		tokenList.put(Tokens.ELSE, "tok_ELSE");
		tokenList.put(Tokens.ENUM, "tok_ENUM");
		tokenList.put(Tokens.EXTENDS, "tok_EXTENDS");
		tokenList.put(Tokens.FALSE, "tok_FALSE");
		tokenList.put(Tokens.FINAL, "tok_FINAL");
		tokenList.put(Tokens.FINALLY, "tok_FINALLY");
		tokenList.put(Tokens.FLOAT, "tok_FLOAT");
		tokenList.put(Tokens.FLOAT_CLASS, "tok_FLOAT");
		tokenList.put(Tokens.FOR, "tok_FOR");
		tokenList.put(Tokens.GOTO, "tok_GOTO");
		tokenList.put(Tokens.IF, "tok_IF");
		tokenList.put(Tokens.IMPLEMENTS, "tok_IMPLEMENTS");
		tokenList.put(Tokens.IMPORT, "tok_IMPORT");
		tokenList.put(Tokens.INSTANCEOF, "tok_INSTANCEOF");
		tokenList.put(Tokens.INT, "tok_INT");
		tokenList.put(Tokens.INT_CLASS, "tok_INT");
		tokenList.put(Tokens.INTERFACE, "tok_INTERFACE");
		tokenList.put(Tokens.LONG, "tok_LONG");
		tokenList.put(Tokens.NATIVE, "tok_NATIVE");
		tokenList.put(Tokens.NEW, "tok_NEW");
		tokenList.put(Tokens.NULL, "tok_NULL");
		tokenList.put(Tokens.PACKAGE, "tok_PACKAGE");
		tokenList.put(Tokens.PRIVATE, "tok_PRIVATE");
		tokenList.put(Tokens.PROTECTED, "tok_PROTECTED");
		tokenList.put(Tokens.PUBLIC, "tok_PUBLIC");
		tokenList.put(Tokens.RETURN, "tok_RETURN");
		tokenList.put(Tokens.SHORT, "tok_SHORT");
		tokenList.put(Tokens.STATIC, "tok_STATIC");
		tokenList.put(Tokens.STRING, "tok_String");
		tokenList.put(Tokens.SUPER, "tok_SUPER");
		tokenList.put(Tokens.SYNCHRONIZED, "tok_SYNCHRONIZED");
		tokenList.put(Tokens.SWITCH, "tok_SWITCH");
		tokenList.put(Tokens.THIS, "tok_THIS");
		tokenList.put(Tokens.THROW, "tok_THROW");
		tokenList.put(Tokens.THROWS, "tok_THROWS");
		tokenList.put(Tokens.TRANSIENT, "tok_TRANSIENT");
		tokenList.put(Tokens.TRUE, "tok_TRUE");
		tokenList.put(Tokens.TRY, "tok_TRY");
		tokenList.put(Tokens.VOID, "tok_VOID");
		tokenList.put(Tokens.VOLATILE, "tok_VOLATILE");
		tokenList.put(Tokens.WHILE, "tok_WHILE");
		tokenList.put(Tokens.SEM_CLN, "tok_SEMCLN");
		tokenList.put(Tokens.CLS_BRCT, "tok_CLS_BRCT");
		tokenList.put(Tokens.CLS_CURL, "tok_CLS_CURL");
		tokenList.put(Tokens.CLS_SQR, "tok_CLS_SQR");
		tokenList.put(Tokens.OPN_BRCT, "tok_OPN_BRCT");
		tokenList.put(Tokens.OPN_CURL, "tok_OPN_CURL");
		tokenList.put(Tokens.OPN_SQR, "tok_OPN_SQR");
		tokenList.put(Tokens.EQLS, "tok_EQLS");
		tokenList.put(Tokens.PERIOD, "tok_PERIOD");

		tokenList.put(Tokens.PLUS_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.MINUS_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.DIV_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.MUL_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.AND_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.OR_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.POW_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.PER_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.TRPL_GRTR_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.DBL_GRTR_EQLS, "tok_EQLS_OP");
		tokenList.put(Tokens.DBL_LESS_EQLS, "tok_EQLS_OP");

		tokenList.put(Tokens.PLUS, "tok_ARTH_OP");
		tokenList.put(Tokens.MINUS, "tok_ARTH_OP");
		tokenList.put(Tokens.DIVISION, "tok_ARTH_OP");
		tokenList.put(Tokens.MULTIPLICATION, "tok_ARTH_OP");
		tokenList.put(Tokens.AMPERSAND, "tok_ARTH_OP");
		tokenList.put(Tokens.PERCENT, "tok_ARTH_OP");
		tokenList.put(Tokens.POWER, "tok_ARTH_OP");
		tokenList.put(Tokens.DBL_LESS, "tok_ARTH_OP");
		tokenList.put(Tokens.DBL_GRTR, "tok_ARTH_OP");
		tokenList.put(Tokens.TRPL_GRTR, "tok_ARTH_OP");

		tokenList.put(Tokens.LESSTHAN, "tok_LOG_OP");
		tokenList.put(Tokens.GRTRTHAN, "tok_LOG_OP");
		tokenList.put(Tokens.DBL_EQLS, "tok_LOG_OP");
		tokenList.put(Tokens.LESS_EQLS, "tok_LOG_OP");
		tokenList.put(Tokens.GRTR_EQLS, "tok_LOG_OP");
		tokenList.put(Tokens.NOT_EQLS, "tok_LOG_OP");
		tokenList.put(Tokens.AND, "tok_LOG_OP");
		tokenList.put(Tokens.OR, "tok_LOG_OP");

		tokenList.put(Tokens.COLON, "tok_COLON");
		tokenList.put(Tokens.TILDA, "tok_TILDA");
		tokenList.put(Tokens.QUESTION, "tok_QUESTION");
		tokenList.put(Tokens.NOT, "tok_NOT");

	}

	// this method returns the appropriate token for the input string.
	public String getTokens(String input) {
		return tokenList.get(input);
	}

	public String Tokenize(String fileName) {
		this.tokenStream = null;
		BufferedReader br = null;
		FileReader fr = null;
		int comment_flag = 0;
		int string_flag = 0;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			sCurrentLine = br.readLine();
			while (sCurrentLine != null) {
				if (sCurrentLine.isEmpty()) {
					sCurrentLine = br.readLine();
					continue;
				}
				StringTokenizer tokenizer = new StringTokenizer(sCurrentLine, delimiters, true);

				while (tokenizer.hasMoreTokens()) {
					String s = tokenizer.nextToken();
					if (s.compareTo(" ") == 0) {
						continue;
					}
					System.out.print("TOK: " + s + " ");

					if (string_flag == 1 && s.compareTo("\"") != 0) {
						continue;
					}

					if (comment_flag == 1) {
						if (s.compareTo("*") == 0) {
							String s1 = tokenizer.nextToken();
							if (s1.compareTo("/") == 0) {
								comment_flag = 0;
							} else {
								addToken(s);
								addToken(s1);
							}
						}
						continue;
					}

					if (s.compareTo("/") == 0) {
						String s1 = tokenizer.nextToken();
						if (s1.compareTo("/") == 0) {
							break;
						} else if (s1.compareTo("*") == 0) {
							comment_flag = 1;
							continue;
						} else {
							addToken(s);
							addToken(s1);
						}
					}

					// To handle strings in the file , we will just add an id
					// token for the entire string.
					if (s.compareTo("\"") == 0) {
						if (string_flag == 0) {
							string_flag = 1;
						} else if (string_flag == 1) {
							string_flag = 0;
							continue;
						}
					}

					addToken(s);

				}

				System.out.println();

				sCurrentLine = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return tokenStream;
	}

	/*
	 * This method prints all the tokens obtained from a line in the file.
	 */
	public void printToken(String[] tok) {
		for (String s : tok) {
			System.out.print("TOK: " + s);
		}
		System.out.println();
	}

	/*
	 * This method will find the token corresponding to the given string and add
	 * it to the token stream string.
	 */
	public void addToken(String s) {
		s = s.trim();
		String token = getTokens(s);
		if (token == null) {
			token = Tokens.ID;
		}
		this.tokenStream = tokenStream + token + seperator;
	}

}
