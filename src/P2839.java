import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2839 {

	private static BufferedReader br;
		
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int sugar = 0 ;
		try {
			sugar = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		int FIVE = sugar/5;
		int THREE = 0; 

		sugar %= 5 ;
		
		while(FIVE >= 0 ){
			
			if(sugar % 3 == 0 ){
				THREE = sugar / 3;
				sugar %=3 ;
				break;
			}
			FIVE --;
			sugar +=5 ;
			
		}
		
		System.out.println(sugar==0?(FIVE+THREE):-1);
	}

}
