package algorithms;

import java.util.Scanner;

public class CombinationNPTest {
	
	private static boolean np(int[] arr) {	// arr : 다음 순열을 원하는 기존 순열의 배열
		// 1. 맨 뒤쪽부터 꼭대기 찾기
		int elementCount = arr.length;
		int findPeakIdx = elementCount - 1;
		
		while(findPeakIdx > 0 && arr[findPeakIdx - 1] >= arr[findPeakIdx])
			--findPeakIdx;
		
		// 다음 순열은 없음 ( 가장 큰 순열의 형태 )
		if(findPeakIdx == 0) return false;
		
		// 2. 꼭대기 직전 (idx - 1)위치에 교환할 한 단계 큰 수를 뒤쪽부터 찾기
		int changeIdx = elementCount - 1;
		
		while(arr[findPeakIdx - 1] >= arr[changeIdx]) --changeIdx;
		
		// 3. 꼭대기 직전 (idx - 1)위치의 수와 한 단계 큰 수를 교환하기
		swap(arr, findPeakIdx - 1, changeIdx);
		
		// 4. 꼭대기 자리부터 맨 뒤까지의 수를 오름차순의 형태로 바꿈
		int sortIdx = elementCount - 1;
		while(findPeakIdx < sortIdx)
			swap(arr, findPeakIdx++, sortIdx--);
		
		
		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int R = sc.nextInt();
		int[] input = new int[N];
		int[] p = new int[N];
		
		for(int i = 0; i < N; i++)
			input[i] = sc.nextInt();
		
		int count = 0;
		while(++count <= R)
			p[N-count] = 1;
		
		do {
			// p배열을 이용한 조합 확인
			for(int i = 0; i < N; i++) {
				if(p[i] == 0) continue;
				System.out.print(input[i] + "\t");
			}
			System.out.println();
		}while(np(p));
		
		sc.close();
	}
	
}
