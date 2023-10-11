package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * Longest Increasing Subsequence
 * 
 * 1. 테스트 케이스 수가 주어진다.
 * 2. 수열의 길이가 주어진다.
 * 3. 수열의 원소 N개가 공백을 사이에 두고 주어진다.
 * 
 * 각 테스트 케이스마다 ‘#T’(T는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고, 최대 증가 부분 수열의 길이를 출력한다.
 *
 */
public class SWEA_3307 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[] subseq;
	static int[] dp;
	static int maxLen;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 테스트 케이스가 주어진다.
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			// 매 태케마다 초기화 해줘야한다.
			maxLen = 0;
			
			// 수열의 길이가 주어진다.
			int subLen = Integer.parseInt(br.readLine().trim());
			
			// 수열을 저장할 배열
			subseq = new int[subLen];
			st = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < subLen; idx++) {
				subseq[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 최장 증가 수열을 구하자.
			// dp 배열을 만들어 수행한다.
			// 앞에서부터 진행하면서 선택된 숫자보다 더 작은 숫자가 앞에 있으면 해당 숫자를 인덱스로하는 배열값 + 1을 한 값을 선택된 숫자 배열 값에 저장
			dp = new int[subLen];
			// outerIdx = 현재 선택된 수열 인덱스
			for(int outerIdx = 0; outerIdx < subLen; outerIdx++) {
				// 가장 첫번 째 숫자이면 앞에 다른 수가 없다.
				if(outerIdx == 0) {
					dp[outerIdx] = 1;
				}
				else {
					int currentSum = 0;
					// innerIdx = 현재 선택된 수열과 비교하는 숫자의 인덱스
					for(int innerIdx = 0; innerIdx < outerIdx; innerIdx++) {
						// 현재 선택된 수보다 앞에 더 작은 숫자가 있다면
						if(subseq[outerIdx] > subseq[innerIdx]) {
							// 값을 더해주고
							currentSum = dp[innerIdx] + 1;
							// dp값보다 더 큰지 아닌지 비교
							if(dp[outerIdx] < currentSum) {
								dp[outerIdx] = currentSum;
								// 그리고 최대 길이를 갱신한다.
								if(maxLen < currentSum) {
									maxLen = currentSum;
								}
							}
						}
					}
					// 앞에 더 작은 수가 없어서 아무런 동작이 일어나지 않는다면 1로 채워준다.
					if(dp[outerIdx] == 0) dp[outerIdx] = 1;
					
				}
			}	// for end
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
		} // tc end
		// 결과 출력
		System.out.println(sb);
	}
}
