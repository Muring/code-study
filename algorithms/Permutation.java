package algorithms;

import java.util.Arrays;

/**
 * @author SeHyeon.Eom
 * 
 *         순열을 구현한다. 모든 경우의 수를 출력하게 한다. 순열을 위한 재귀함수를 만든다.
 *         순열을 구현하는데 무엇이 필요한가?
 *         1. 가장 처음 각 요소들을 하나씩 찢어 새로운 배열을 만든다
 *         2. 찢어진 배열들의 다음 요소를 위해 남아있는 요소들 만큼 다시 찢어 새로운 배열을 만든다.
 *         3. 이 과정을 모든 데이터가 들어올 때까지 반복한다.
 *         4. 완료되면 해당 배열을 StringBuilder에 추가한다.
 *         5. 최종 출력
 */

public class Permutation {
	static final int[] nums = { 1, 2, 3, 4 };	// 순열의 토대가 될 데이터
	static final int depth = 3;				// 출력할 수열의 크기 = 총 재귀 횟수
	static int[] arr = new int[depth];		// 순열을 저장할 배열
	static boolean[] visited = new boolean[nums.length];	// 수열에서 해당 수를 썼는지 확인할 변수
	
	// 순열을 구현할 메서드
	// 각 배열을 저장할 배열, 어느 부분을 샤용했는지 체크할 visited, 재귀 횟수를 셀 count
	public static void perm(int[] arr, boolean[] visited, int count) {
		// 종료 조건
		// 재귀한 횟수가 총 재귀 횟수에 도달하면 종료
		if(count == depth) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		// 함수 기능
		for(int permIdx = 0; permIdx < nums.length; permIdx++) {
			// 해당 숫자가 사용되지 않았으면 실행
			if(visited[permIdx] != true) {
				visited[permIdx] = true;	// 해당 숫자가 사용되었다고 표시
				arr[count] = nums[permIdx];	// 해당 숫자를 순열 배열에 저장
				perm(arr, visited, count + 1);
				visited[permIdx] = false;	// 출력하고 돌아오면 사용 표시를 비사용으로 바꿈
			}
		}
	}
	
	// 중복 순열을 구현할 메서드
	// 중복 순열은 visited가 필요 없기에 각 배열을 저장할 배열, 재귀 횟수를 셀 count만 필요하다.
	public static void repeatPerm(int[] arr, int count) {
		// 종료 조건
		// 재귀한 횟수가 총 재귀 횟수에 도달하면 종료
		if(count == depth) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		// 함수 기능
		for(int permIdx = 0; permIdx < nums.length; permIdx++) {
			// 해당 숫자가 사용되지 않았으면 실행
				arr[count] = nums[permIdx];	// 해당 숫자를 순열 배열에 저장
				repeatPerm(arr, count + 1);
		}
	}
	
	public static void main(String[] args) {
		// 순열 함수 호출
		perm(arr, visited, 0);
		System.out.println("---------------");
		// 중복 순열 함수 호출
		repeatPerm(arr, 0);
	}
}
