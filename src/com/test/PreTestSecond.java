package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PreTestSecond {
	
	private static HashMap<String, Integer> resultMap = new HashMap<>();
	private static int width = 0;
	private static int height = 0;
	
	public static void main(String[] args) {
		// system in 은 간단하게 try catch 체크 스킵 무조건 정수 가정 
//		Scanner inputValue = new Scanner(System.in);
//		System.out.println("width = ");
//		int width = inputValue.nextInt();
		width = 3;
//		System.out.println("height = ");
//		int height = inputValue.nextInt();
		height = 4;
		int ALL = (1<<width)-1;
		List<Integer> humans = new ArrayList<Integer>();
		
		System.out.println(ALL);
		
		for(int i = 0 ; i <= ALL ; i++) {
			int fromIndex = -1;
			int cnt = 0;
			while ((fromIndex = Integer.toBinaryString(i).indexOf("1", fromIndex + 1)) >= 0) {
				cnt += 1;
			}
			humans.add(i, cnt);
		}
		System.out.println(humans);
		
		int total = 0 ;
		
		
		for (int i = 0; i < (1 << width); i++) {
			total += search(i, i, 1, humans.get(i));
		}
		System.out.println(total);
		
		int sum = 0;
		for(int i : resultMap.values()) {
			sum += i;
		}
		System.out.println(sum);
		
	}

	private static int search(int first, int second, int line, int human) {
		String key = ""+first+second+line+human;
		int result = 0 ;
		
		if(resultMap.get(key) != null) {
			System.out.println("key1 :"+key);
			return 0;
		}
		
		if(line >= height ) {
			resultMap.put(key, (human == width*height/2)?1:0);
			System.out.println("key2 :"+key);
		}
		
		if(line == height -1 ) {
			System.out.println("key3 :"+key);
		}else {
			System.out.println("key3 :" + key);
			
		}
			
		resultMap.put(key, 1);
	}

}
