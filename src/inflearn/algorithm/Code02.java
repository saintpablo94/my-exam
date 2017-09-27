package inflearn.algorithm;

import java.util.Scanner;

public class Code02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();
		int inputInt = sc.nextInt();

//		System.out.println(characterLen(str));
//		System.out.println("\n characterLen-----");
//
//		printChars(str);
//		System.out.println("\n printChars-----");
//
//		printCharsReverse(str);
//		System.out.println("\n printCharsReverse-----");

		printInBinary(inputInt);
		System.out.println("\n printInBinary-----");
		
	}


	private static void printInBinary(int inputInt) {
		if (inputInt < 2) {
			System.out.print(inputInt);
		} else {
			printInBinary(inputInt / 2);
			System.out.print(inputInt % 2);
		}
	}

	private static void printCharsReverse(String str) {
		if (str.length() == 0) {
			return;
		} else {
			printCharsReverse(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}

	private static void printChars(String str) {
		if (str.length() == 0) {
			return;
		} else {
			System.out.print(str.charAt(0));
			printChars(str.substring(1));
		}

	}

	private static int characterLen(String str) {
		if (str.equals("")) {
			return 0;
		} else {
			return 1 + characterLen(str.substring(1));
		}
	}
}
