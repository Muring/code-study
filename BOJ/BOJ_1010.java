package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다리 놓기
 * 
 * 도시를 동쪽과 서쩍오르 나누는 큰 일직선 모양의 강이 흐르고 있다.
 * 강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다.
 * 서쪽에는 N개의 사이트가 있고, 동쪽에는 M개의 사이트가 있다. (N <= M)
 * 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.
 * 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 다리를 지으려고 한다.
 * 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.
 * 
 * [입력]
 * - 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 * - 다음 줄부터 각각의 테스트 케이스에 대해 강의 서쪽과 동쪽에 있는 사이트 개수가 주어진다.
 * 
 * [출력]
 * 각 테스트 케이스에 대해 다리를 지을 수 있는 경우의 수를 출력
 * 
 * 1. 모든 조합의 경우의 수를 저장할 dp배열 생성
 * 2. 조합의 성질을 이용한 n==r, r==0일 떄의 조합 값 초기화를 한다.
 * 3. 나머지 모든 조합 값을 전부 계산하여 저장
 * 4. 입력받은 값에 따른 조합 값 가져와 출력
 * @author sehyeon.eom
 *
 */

public class BOJ_1010 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int westSiteCount;
	static int eastSiteCount;
	static int[][] dp;		// 모든 조합의 경우의 수를 저장할 dp배열
	
	static final int MAX_SITE = 30;	// 사이트의 최대 개수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		dp = new int[MAX_SITE][MAX_SITE];
		
		// 조합의 성질 기본 초기화
		for(int idx = 0; idx < MAX_SITE; idx++) {
			dp[idx][idx] = 1;
			dp[idx][0] = 1;
		}
		
		// dp배열에 모든 조합의 경우의 수 저장
		for(int westIdx = 2; westIdx < MAX_SITE; westIdx++) {
			for(int eastIdx = 1; eastIdx < MAX_SITE; eastIdx++) {
				dp[westIdx][eastIdx] = dp[westIdx - 1][eastIdx - 1] + dp[westIdx - 1][eastIdx];
			}
		}
		
		// 전체 테스트 케이스 수 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 0; tc < totalCase; tc++) {
			st = new StringTokenizer(br.readLine());
			westSiteCount = Integer.parseInt(st.nextToken());
			eastSiteCount = Integer.parseInt(st.nextToken());
			
			// 입력 받은 숫자대로 즉시 조합의 값을 dp 배열에서 받아온다.
			sb.append(dp[eastSiteCount][westSiteCount]).append("\n");
			
			
		}	// tc end
		System.out.println(sb);
		
	}	// main end
}
