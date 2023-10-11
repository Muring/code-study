package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659 {
	static BufferedReader br;	// 입력
	static StringBuilder sb;	// 출력
	static int totalNumCount;	// 전체 숫자 개수
	static int totalAddCount;	// 전체 더할 횟수
	static int[] sumArr;		// 구간 내 수의 합
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 데이터 입력
		totalNumCount = Integer.parseInt(st.nextToken());
		totalAddCount = Integer.parseInt(st.nextToken());
		
		// 숫자 배열 생성 및 입력
		int sum = 0;							// 총합
		sumArr = new int[totalNumCount + 1];	// 0의 값을 가질 배열 한칸 더 생성
		
		st = new StringTokenizer(br.readLine());
		for(int idx = 1; idx <= totalNumCount; idx++) {
			sum += Integer.parseInt(st.nextToken());	// 숫자가 들어올 때마다 합을 더한다.
			sumArr[idx] = sum;							// 해당 합을 다음 sumArr에 저장
		}
			
		// 구간의 입력 및 합 계산
		int start, end, result;
		for(int addCount = 0; addCount < totalAddCount; addCount++) {
			// 해당 줄 입력 받아 범위 계산
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());	// 시작 인덱스
			end = Integer.parseInt(st.nextToken());		// 끝 인덱스

			result = sumArr[end] - sumArr[start - 1];
			
			sb.append(result).append("\n");					// 결과 저장
		}
		
		System.out.println(sb);								// 결과 출력
	}
}
