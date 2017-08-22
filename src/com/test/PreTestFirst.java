package com.test;

import java.util.Scanner;

public class PreTestFirst {

	public static void main(String[] args) {
		// system in 은 간단하게
		Scanner sc = new Scanner(System.in);
		System.out.println("width = ");
		int width = sc.nextInt();
		System.out.println("height = ");
		int height = sc.nextInt();

		// 초기화
		int up = 0, y = 0, odds = 0;

		// 재귀함수 고고
		System.out.println(search(width, height, up, y, odds));

	}

	// row를 뒤지자
	private static int search(int width, int height, int up, int y, int odds) {

		// 비트 반전 width가 1이면 3 나오게..
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

		System.out.println("row :" + row + ", up :" + up + ", y :" + y + ", XOR_ROW :" + XOR_ROW);

		// 맨 마지막 행은 체크 후 STOP
		if (y == height) {

			// 홀수카운트
			// ruby 면 odds += (row ^ up).to_s(2).count("1") 면 되는데..젠장..
			int fromIndex = -1;
			while ((fromIndex = Integer.toBinaryString(row ^ up).indexOf("1", fromIndex + 1)) >= 0) {
				odds += 1;
			}

			// 0 또는 2는 대상
			if ((odds == 0) || (odds == 2)) {
				return 1;
			}

			return 0;

		}

		// 리턴값 초기화
		int cnt = 0;

		// ruby 면 (1 << W).times{|a| 면되는데..
		// 첫번쨰 사선 \ 뒤지기
		for (int a = 0; a < (1 << width); a++) {
			// 두번쨰 사선 / 뒤지기
			for (int b = 0; b < (1 << width); b++) {
				int lieOddsCnt = 0;
				int fromIndex = -1;
				// << 우선 ^ 은 나중.
				while ((fromIndex = Integer.toBinaryString(row ^ up ^ a << 1 ^ b).indexOf("1", fromIndex + 1)) >= 0) {
					lieOddsCnt++;
				}
				// 재귀호출 값커지면 젠장..width 5 height 5 초과부터 겁나 걸린다 .ㅠㅠ 이게 한게인듯..
				// y+1 하자 그리고 odds + lieOddsCnt 이걸 빼먹다니..졸라 고생했다
				cnt += search(width, height, a ^ b << 1, y + 1, odds + lieOddsCnt);
			}
		}

		return cnt;
	}

}
