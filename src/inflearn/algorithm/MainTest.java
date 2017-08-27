package inflearn.algorithm;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String character = sc.nextLine();
		
		printReverseChar(character);
	}

	private static void printReverseChar(String character) {
		if(character.length() == 0 ){
			return;
		}else{
			System.out.print(character.charAt(0));
			printReverseChar(character.substring(1));
		}
		
		
	}

}
