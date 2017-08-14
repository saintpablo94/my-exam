package inflearn.algorithm;

public class Code02 {
	public static void main(String[] args){
		String str = "abcd";
		System.out.println(characterLen(str));
		printChars(str);
		System.out.println("");
		System.out.println("-----");
		System.out.println("");
		printCharsReverse(str);
		
	}

	private static void printCharsReverse(String str) {
		if(str.length() == 0){
			return;
		}else{
			printCharsReverse(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}

	private static void printChars(String str) {
		if(str.length() == 0){
			return ;
		}else{
			System.out.print(str.charAt(0));
			printChars(str.substring(1));
		}
		
	}

	private static int characterLen(String str) {
		if(str.equals("")){
			return 0;
		}else{
			return 1+characterLen(str.substring(1));
		}
	}
}
