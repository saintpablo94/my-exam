package inflearn.algorithm;

//recursion
public class Code01 {

	public static void main(String[] args) {
		int m = 9;
		int n = 33;
		System.out.println(func(n));
		System.out.println(factorialFunc(n));
		System.out.println(fibonaciFunc(n));
		System.out.println(euclidFunc(m, n));
		System.out.println(euclidFuncSecond(m, n));
		
	}

	private static int euclidFuncSecond(int m, int n) {
		if( n == 0 ){
			return m;
		}else{
			System.out.println("m : "+m+" n :"+n+" m%n :"+m%n);
			return euclidFuncSecond(n, m%n);
		}
	}

	private static double euclidFunc(int m, int n) {
		if(m <n){
			int tmp=m; m=n; n=tmp; //swap
		}
		
		if(m%n == 0 ){
			return n;
		}else{
			System.out.println("m%n :"+m%n);
			return euclidFunc(n, m%n);
		}
		
	}

	private static int fibonaciFunc(int n) {
		if(n < 2){
			return n;
		}else{
			return fibonaciFunc(n-1)+fibonaciFunc(n-2);
		}
	}

	private static int factorialFunc(int n) {
		if(n == 0 ){
			return 1;
		}else{
			return n*factorialFunc(n-1);
		}
		
	}

	private static int func(int n) {
		//Base Case 
		if(n <= 0){
			return 0;
		}
		else{
			//Recursive Case
			return n+func(n-1);
		}
		
	}

}
