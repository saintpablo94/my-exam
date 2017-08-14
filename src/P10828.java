import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10828 {

	public static void main(String[] args) {
		
		sc.init();
		new P10828().solve();
	}
	
	private void solve() {
		String input = null;
		StringBuilder sb = new StringBuilder();
		
		while((input = sc.readLine()) != null) {
			String[] s = input.split(" ");
			System.out.println(sc.nextInt());
				
		}
		System.out.println(sb.toString());
		
	}

	static class sc {
		private static BufferedReader br;
		private static StringTokenizer st;

		static void init() {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer("");
		}

		static String readLine() {
			try {
				
				System.out.println("readLine() "+br.readLine());
				return br.readLine();
			} catch (IOException e) {
			}
			return null;
		}

		static String readLineReplace() {
			try {
				System.out.println("readLineReplace() "+br.readLine().replaceAll("\\s+", ""));
				return br.readLine().replaceAll("\\s+", "");
			} catch (IOException e) {
			}
			return null;
		}

		static String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
					System.out.println("next() "+st);
				} catch (IOException e) {
				}
			}
			return st.nextToken();
		}

		static long nextLong() {
			return Long.parseLong(next());
		}

		static int nextInt() {
			return Integer.parseInt(next());
		}

		static double nextDouble() {
			return Double.parseDouble(next());
		}
	}

}
