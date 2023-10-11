package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * 첫째 줄에 몇 일 뒤에 퇴사할건지 입력받는다.
 * 둘째 줄부터 그 날부터 시작되는 상담이 걸리는 일 수와 이익을 입력받는다.
 * 이후 퇴소 전 얻을 수 있는 최대 이익을 출력한다.
 * 
 * 최대 이익을 출력해야하는 문제이다.
 * 첫날부터 계산을 시작하여 마지막날까지 얻을 수 있는 이익과
 * 다음날부터 계산하여 마지막날까지 얻을 수 있는 이익 등을 다 비교한다.
 * 이후 나온 maxProfit이 정답이므로 출력한다.
 *
 */

public class BOJ_14501 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
		
	static int time[];				// 걸리는 일 수
	static int money[];				// 이익
	static int dp[];				// dp 배열
	static int leftDays;			// 남은 날짜
	
	// 커스텀 조합을 만들어?
	
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫 줄 입력
		leftDays = Integer.parseInt(br.readLine().trim());
		
		time = new int[leftDays + 1];
		money = new int[leftDays + 1];
		dp = new int[leftDays + 2];		
		
		// 이후 스케쥴과 이익 입력
		for(int idx = 1; idx <= leftDays; idx++) {
			 st = new StringTokenizer(br.readLine());
			 time[idx] = Integer.parseInt(st.nextToken());
			 money[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 계산 시작
		for(int idx = leftDays; idx > 0; idx--) {
			int next = idx + time[idx];	// 다음 날짜
			
			// 일 할 수 있는 날짜를 넘어가는 경우
			if(next > leftDays + 1) {
				// 일을 하지 못하므로 바로 다음 DP값(더 앞쪽의 날짜)로 설정
				dp[idx] = dp[idx + 1];
			}
			// 일을 할 수 있는 날짜의 경우
			else {
				// 1. 일을 하지 않았을 때 (dp[idx + 1])
				// 2. 일 했을 때 총 벌 수 있는 금액(moeny[idx] + dp[next])
				// 위 두 경우 중 더 큰 값을 dp에 넣어준다.
				dp[idx] = Math.max(dp[idx + 1], money[idx] + dp[next]);
			}
		}
		sb.append(dp[1]);
		System.out.println(sb);
	}	// main end
}
