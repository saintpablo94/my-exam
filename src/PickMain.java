import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// http://ykcb.tistory.com/entry/n%EA%B0%9C%EC%9D%98-%EC%9B%90%EC%86%8C-%EC%A4%91-m%EA%B0%9C%EB%A5%BC-%EA%B3%A0%EB%A5%B4%EB%8A%94-%EB%AA%A8%EB%93%A0-%EC%A1%B0%ED%95%A9%EC%9D%84-%EC%B0%BE%EB%8A%94-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98

public class PickMain {

	static private BufferedReader br;
	static private StringTokenizer st;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");

		int first = 0;
		int second = 0;

		while (st.hasMoreTokens()) {
			first = Integer.parseInt(st.nextToken());
			second = Integer.parseInt(st.nextToken());

		}

		ArrayList<Integer> arrInteger = new ArrayList<Integer>();
		pick(first, arrInteger, second);
		
	}

	private static void pick(int total, ArrayList<Integer> picked, int toPick) {
		//System.out.println("function");
		
		if(toPick == 0 ){
			System.out.println(picked);
			return;
		}
		
		int smallest = picked.isEmpty() ? 0 : picked.get(picked.size()-1)+1;
		
		for(int next = smallest ; next < total ; next++){
			picked.add(next);
			pick(total, new ArrayList<Integer>(picked), toPick-1);
			picked.remove(picked.size()-1);
		}
		
	}

}
