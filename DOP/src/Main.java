
public class Main {

	public static void main(String[] args) {
		String seperator = "|";
		Tokenizer tokenizer = new Tokenizer();

		String test = tokenizer.Tokenize("test2.java");
		String[] output = test.split(seperator);
		System.out.println("OUTPUT: " + test);
	}

}
