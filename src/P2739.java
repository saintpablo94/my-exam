import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2739 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int hopeNum = 0;
		try {
			hopeNum = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 1 ; i < 10 ; i++){
			System.out.println(hopeNum+" * "+i+" = "+hopeNum*i);
		}
		
	}

}
