package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author SeHyeon.Eom
 * 
 * 1~N까지의 자연수 중에서 중복 없이 M개를 고른 수열을 출력하라.
 * 
 * 1. N과 M을 입력 받는다. 이때, N = maxNum, M = permLen으로 받는다.
 * 2. 입력 받은 문자를 토대로 수열 출력
 */
public class BOJ_15649 {
	// 버퍼드 리더를 쓰기 위한 static 호출
	static BufferedReader bf;
	
	//출력을 위한 StringBuilder 호출
	static StringBuilder sb;

	// 입력 받을 데이터
	static int maxNum;		// 수열의 범위 ( 최대 수 )
	static int permLen;		// 출력될 수열의 길이
	
	// 순열을 구현할 메서드
	// 각 배열을 저장할 배열, 어느 부분을 샤용했는지 체크할 visited, 재귀 횟수를 셀 count
	public static void perm(int[] arr, boolean[] visited, int count) {
		// 종료 조건
		// 재귀한 횟수가 총 재귀 횟수에 도달하면 종료
		if(count == permLen) {
			// String output에 완성된 순열 저장
			String output = Arrays.toString(arr);	
			output = output.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
			sb.append(output + '\n');
			// 출력문에서 중괄호 빼기
//			System.out.println(output);		
			return;
		}
		
		// 함수 기능
		for(int permIdx = 0; permIdx < maxNum; permIdx++) {
			// 해당 숫자가 사용되지 않았으면 실행
			if(visited[permIdx] != true) {
				visited[permIdx] = true;	// 해당 숫자가 사용되었다고 표시
				arr[count] = permIdx + 1;	// 해당 숫자를 순열 배열에 저장
				perm(arr, visited, count + 1);
				visited[permIdx] = false;	// 출력하고 돌아오면 사용 표시를 비사용으로 바꿈
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// 입력 객체 생성
		bf = new BufferedReader(new InputStreamReader(System.in));
		
		// 출력 객체 생성
		sb = new StringBuilder();
		
		// input 데이터를 입력 받고 각각 maxNum과 permLen에 저장
		String[] input = bf.readLine().trim().split(" ");
		maxNum = Integer.parseInt(input[0]);
		permLen = Integer.parseInt(input[1]);
	
		// 순열을 저장할 arr와 숫자가 사용됐는지 확인할 visited
		int[] arr = new int[permLen];
		boolean[] visited = new boolean[maxNum];
		
		// 순열 실행
		perm(arr, visited, 0);
		
		// 결과 출력
		System.out.println(sb);
	}

}
