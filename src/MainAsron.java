import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainAsron {

	private void solve() {

		double totalDistance;

		Map<Integer, List<Object>> mapp = new HashMap<Integer, List<Object>>();

		totalDistance = Before.hasDouble();
		System.out.println(totalDistance);
		
		List<Object> list = new ArrayList<Object>();
		mapp.put(0,list);
		list.add(Before.hasFloat());
		list.add(Before.hasFloat());
		
		System.out.println(mapp.get(0));
		
	}

	public static void main(String[] args) {
		Before.init();
		new MainAsron().solve();
	}

	static class Before {
		private static BufferedReader br;
		private static StringTokenizer st;

		static void init() {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer("");
		}

		static String readLine() {
			try {
				return br.readLine();
			} catch (IOException e) {

			}
			return null;
		}

		static String readLineReplace() {
			try {
				return br.readLine().replaceAll("\\s", "");
			} catch (IOException e) {

			}
			return null;
		}

		static String next() {
			while (!st.hasMoreTokens()) {

				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {

				}
			}
			return st.nextToken();
		}

		static boolean hasNext() {
			return st.hasMoreTokens();
		}

		static int hasInt() {
			return Integer.parseInt(next());
		}

		static float hasFloat() {
			return Float.parseFloat(next());
		}

		static double hasDouble() {
			return Double.parseDouble(next());
		}

	}
}
