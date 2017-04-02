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

	Map<String, String> tokenList;
	String delimiters = "\" : ~ ? ! // /* */ ; { } [ ] ( )  ";
	String seperator = "|";
	String tokenStream;

	public Tokenizer() {
		StringToTokenMap map = new StringToTokenMap();
		this.tokenList = map.tokenList;
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
					s = s.replaceAll("\\s", "");
					if (s.compareTo(" ") == 0 || s.length() == 0) {
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

					// To handle
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
		if(tokenStream ==null){
			this.tokenStream = token + seperator;
		}
		else{
			this.tokenStream = tokenStream + token + seperator;
		}
	}

}
