import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	private static BufferedReader in;

	public static void main(String[] args) throws IOException {

		in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while( (line=in.readLine())!=null){
			System.out.println(line);
		}
		
		in.close();
	}
}
