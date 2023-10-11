package algorithms;

import java.util.Scanner;



public class PermutationBitTest {
	static int dataCount;
	static int permLen;
	static int[] input;
	static int[] numbers;
	
	
	private static void permutation(int cnt, int flag) {
		
		if(cnt == permLen) {
			//System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 0; i < dataCount; i++) {
			// 중복 체크
			if((flag & 1 << i) != 0) continue;
			// 수 선택
			numbers[cnt] = input[i];
			// 다음 자리수 뽑기
			permutation(cnt+1, flag | 1<<i);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		dataCount = sc.nextInt();
		permLen = sc.nextInt();
		input = new int[dataCount];
		numbers = new int[permLen];
		
		for(int i = 0; i < dataCount; i++)
			input[i] = sc.nextInt();
		
		long start = System.nanoTime();
		permutation(0, 0);
		long end = System.nanoTime();
		System.out.println((end-start)/1_000_000_000.0);
		
		sc.close();
	}
}
