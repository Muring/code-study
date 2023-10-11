package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {
	static int range;			// 숫자 범위  3이면 1~3
	static int size;			// 집합 요소 개수
	static BufferedReader br;	// 입력
	static StringBuilder sb;	// 출력
	static int[] arr;			// 조합 저장할 배열
	
	// 조합
	// 조합을 저장할 배열과, 재귀 횟수, 조합을 구하는 시작 지점
	public static void combination(int[] arr, int count, int start) {
		// 종료문
		if(count == size) {
			for(int i = 0; i < arr.length; i++)
				sb.append(arr[i]).append(" ");			// 결과 저장
			return;
		}
		
		// 조합을 구하는 시작 지점부터 집합의 끝까지 반복한다.
		for(int idx = start; idx <= range; idx++) {
			arr[count] = idx;							// 조합 배열에 집합 요소 저장 -> count는 몇 번째 요소인지
			
			combination(arr, count + 1, idx + 1);		// 재귀로 다음 숫자를 확인하러 들어간다.
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 데이터 입력
		range = Integer.parseInt(st.nextToken());
		size = Integer.parseInt(st.nextToken());
		arr = new int[size];
		
		combination(arr, 0, 1);		// 조합 실행
		System.out.println(sb);		// 결과 출력
	}
}
