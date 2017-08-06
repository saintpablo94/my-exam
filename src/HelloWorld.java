import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HelloWorld {

	private static BufferedReader br;
	private static StringTokenizer st;

	private static int firstNum = 0;
	private static int secondNum = 0;

	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			st = new StringTokenizer(br.readLine(), "\\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (st.hasMoreTokens()) {
			firstNum = Integer.parseInt(st.nextToken());
			secondNum = Integer.parseInt(st.nextToken());
		}
		
		//A+B
		System.out.println(firstNum + secondNum);
		//A-B
		System.out.println(firstNum - secondNum);
		//A*B
		System.out.println(firstNum * secondNum);
		//A/B(¸ò)
		System.out.println(firstNum / secondNum);
		//A%B(³ª¸ÓÁö)
		System.out.println(firstNum % secondNum);
	}

}
