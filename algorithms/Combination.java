package algorithms;

import java.util.Arrays;

/**
 * 
 * @author sehyeon.eom
 * 
 * 조합과 중복 조합 코드
 * 
 */

public class Combination {
	static final int[] nums = { 1, 2, 3, 4 };	// 기본 집합
	static int[] arr;							// 각 조합 저장 배열
	static final int combLen = 3;				// 조합의 길이
	
	// 조합
	// 조합을 저장할 배열과, 재귀 횟수, 조합을 구하는 시작 지점
	public static void combination(int[] arr, int count, int start) {
		// 종료문
		if(count == combLen) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		// 조합을 구하는 시작 지점부터 집합의 끝까지 반복한다.
		for(int idx = start; idx < nums.length; idx++) {
			arr[count] = nums[idx];						// 조합 배열에 집합 요소 저장 -> count는 몇 번째 요소인지
			combination(arr, count + 1, idx + 1);		// 재귀로 다음 숫자를 확인하러 들어간다.
		}
	}
	
	// 중복 조합
	// 조합을 저장할 배열과, 재귀 횟수, 조합을 구하는 시작 지점
	public static void repeatCombination(int[] arr, int count, int start) {
		// 종료문
		if(combLen == count) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		// 조합을 구하는 시작 지점부터 집합의 끝까지 반복한다.
		for(int idx = start; idx < nums.length; idx++) {
			arr[count] = nums[idx];						// 조합 배열에 집합 요소 저장
			repeatCombination(arr, count + 1, start + idx);	// 재귀로 다음 숫자를 확인하러 들어간다.
		}
	}
	
	public static void main(String[] args) {
		// 객체 생성
		arr = new int[combLen];							// 조합을 저장할 배열이기 때문에 조합 길이만큼 생성
		
		combination(arr, 0, 0);
		System.out.println("-------------------------*");
		repeatCombination(arr, 0, 0);
	}
}
