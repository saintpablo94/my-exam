package com.test;

import java.util.Scanner;

public class PreTestFirst {

	// row를 뒤지자
	private static int search(int width, int height, int up, int y, int odds) {

		// 비트 반전 width가 2이면 7 .
		int XOR_ROW = (1 << (width + 1)) - 1;

		// 위의 행까지 홀수수가 2보다 크면 제외
		if (odds > 2) {
			return 0;
		}

		// 초기값 셑
		int row = 1 << width | 1;

		// 맨 처음 맨 마지막 반전
		if ((y == 0) || (y == height)) {
			row = XOR_ROW ^ row;
		}

		// System.out.println("row :" + row + ", up :" + up + ", y :" + y + ",XOR_ROW :" + XOR_ROW);

		// 맨 마지막 행은 체크 후 STOP
		if (y == height) {

			// 홀수카운트
			int fromIndex = -1;
			while ((fromIndex = Integer.toBinaryString(row ^ up).indexOf("1", fromIndex + 1)) >= 0) {
				odds += 1;
			}

			if ((odds == 0) || (odds == 2)) {
				return 1;
			}

			return 0;

		}

		int cnt = 0;

		// 첫번쨰 사선 \ 뒤지기
		for (int a = 0; a < (1 << width); a++) {
			// 두번쨰 사선 / 뒤지기
			for (int b = 0; b < (1 << width); b++) {

				// System.out.print("toBinaryString:"+Integer.toBinaryString(row ^ up ^ a << 1 ^ b));

				int lieOddsCnt = 0;
				int fromIndex = -1;
				// << 우선 ^ 은 나중.
				while ((fromIndex = Integer.toBinaryString(row ^ up ^ a << 1 ^ b).indexOf("1", fromIndex + 1)) >= 0) {
					lieOddsCnt++;
				}
				cnt += search(width, height, a ^ b << 1, y + 1, odds + lieOddsCnt);
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		Scanner inputValue = new Scanner(System.in);
		System.out.println("width = ");
		int width = inputValue.nextInt();
		System.out.println("height = ");
		int height = inputValue.nextInt();

		// 초기화
		int up = 0, y = 0, odds = 0;

		// 함수소요 시간 start
		long startTime = System.nanoTime();

		// 재귀함수 
		System.out.println(search(width, height, up, y, odds));

		// 함수소용 시간 end
		double timeSpent = System.nanoTime() - startTime;
		System.out.println("Time Spend : " + timeSpent + " ns");
		inputValue.close();
	}
}
