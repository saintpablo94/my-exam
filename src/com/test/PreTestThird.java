package com.test;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.exit;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PreTestThird {

	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;
	static int test;

	static final int PRIMES[] = new int[64];
	static {
		i: for (int i = 0, p = 2; i < PRIMES.length; p++) {
			for (int j = 2; j * j <= p; j++)
				if (p % j == 0)
					continue i;
			PRIMES[i++] = p;
		}
	}

	static final long MASKS[] = new long[256];
	static {
		MASKS[0] = -1;
		for (int i = 1; i < MASKS.length; i++) {
			int cur = i;
			long curMask = 0;
			for (int j = 0; j < PRIMES.length && cur > 1; j++)
				while (cur % PRIMES[j] == 0) {
					cur /= PRIMES[j];
					curMask |= 1L << j;
				}
			if (cur != 1)
				throw new AssertionError();
			MASKS[i] = curMask;
		}
	}

	static int n;
	static int a[];
	static int s[];

	static void solve() throws Exception {
		n = nextInt();
		int k = nextInt();
		a = new int[n];
		s = new int[n + 1];
		int ans = 0;
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			ans += (k - a[i] % k) % k;
			a[i] += (k - a[i] % k) % k;
			a[i] /= k;
		}
		sort(a);
		// System.out.println(Arrays.toString(a));
		// System.out.println(ans);
		for (int i = n - 1; i >= 0; i--)
			s[i] = s[i + 1] + a[i];

		// System.out.println(Arrays.toString(s));
		for (int rem = 0;; rem++) {
			// System.out.println(rem);
			if (go(0, 0, 0, rem)) {
				// System.out.println(ans+"+"+k+"*"+rem);
				printCase(ans + k * rem);
				return;
			}
		}
	}

	static boolean go(int i, int v, long mask, int rem) {
		if (i >= n)
			return true;
		int top = a[i] + rem;
		int limit = min(top, (s[i] + rem) / (n - i));
		for (int cur = max(a[i], v); cur <= limit; cur++) {
			long curMask = MASKS[cur];
			if ((mask & curMask) != 0)
				continue;
			if (go(i + 1, cur, mask | curMask, top - cur))
				return true;
		}
		return false;
	}

	static void printCase(int result) {
		System.out.println("Test Case " + test + ": " + result);
	}

	static int nextInt() throws IOException {
		return parseInt(sc.next());
	}

	static String next() throws IOException {
		while (tok == null || !tok.hasMoreTokens())
			try {
				tok = new StringTokenizer(in.readLine());
			} catch (IOException e) {

			}

		return tok.nextToken();
	}

	public static void main(String[] args) throws IOException {

		try {
			// 입력받기때문에 파일로 받을때는 주석처리
			sc.init();

			// 입력 귀찮다 그냥 파일로 받자
			// System.setIn(new FileInputStream("CandySample.txt"));
			// in = new BufferedReader(new InputStreamReader(System.in));

			// 결과도 파일 로 받아보자. 주석처리하면 SYSOUT으로
			// System.setOut(new PrintStream(new
			// FileOutputStream("CandyResult.txt")));
			// out = new PrintWriter(new OutputStreamWriter(System.out));

			// test case total
			int tests = nextInt();

			// 함수소요 시간 start
			long startTime = System.nanoTime();

			for (test = 1; test <= tests; test++) {
				solve();
			}

			// 함수소용 시간 end
			double timeSpent = System.nanoTime() - startTime;
			System.out.println("Time Spend : " + timeSpent + " ns");

		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		} finally {
			in.close();
			out.close();
		}
	}

	static class sc {
		private static BufferedReader br;
		private static StringTokenizer st;

		static void init() {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer("");
		}

		static String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
				}
			}
			return st.nextToken();
		}

		static int nextInt() {
			return Integer.parseInt(next());
		}

	}
}