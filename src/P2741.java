import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2741 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int startNum = 0;

		try {
			startNum = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		//P2741
		for (int i = 1; i <= startNum; i++) {
			System.out.println(i);
		}
		
		//2742
		for (int i = startNum ; i > 0 ; i--) {
			System.out.println(i);
		}

	}

}
