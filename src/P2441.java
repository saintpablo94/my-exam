import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2441 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int hopeNum = 0;
		try {
			hopeNum = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		int startNum = 0;
		starPrint(hopeNum, startNum);
	}

	private static void starPrint(int hopeNum, int startNum) {

		if (hopeNum == 0) {
			return;
		}
		
		int initNum = (startNum == 0) ? 0 : startNum+1;
		System.out.println("initNum :"+initNum);

		for ( int i = initNum ; i < hopeNum ; i++) {
			System.out.print("*");
			System.out.println("");
			initNum++;
			starPrint(hopeNum - 1, initNum);
		}
		System.out.println("");
		

	}

}
