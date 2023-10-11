package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * 1. 전체 테스트 케이스가 주어진다.
 * 2. 각 테스트 케이스의 첫 번째 줄에는 재료의 수, 제한 칼로리를 입력 받는다.
 * 3. 이후 재료의 수 만큼 재료와 각각의 칼로리를 입력받는다.
 * 4. 주어진 제한 칼로리 이하의 조합 중 가장 맛에 대한 점수가 높은 것을 출력
 * 
 * 조합, 부분집합의 방법으로 각각 푼다.
 *
 */
public class SWEA_5215 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ingreCount;		// 전체 재료 수
	static int maxCal;			// 최대 칼로리
	static int[] tastes;		// 전체 재료 맛 점수 목록
	static int[] calories;		// 전체 칼로리 목록
	static int[] comb;			// 재료 조합의 인덱스를 저장하는 공간
	static int maxTaste;		// 최대 맛 수치
	static boolean[] visited;	// 해당 숫자가 사용 됐는지 안됐는지 확인
	static int[] dp;			// dp로 풀때 사용할 dp배열
	
	// 조합
	private static void combination(int count, int start) {
		// 종료 조건
		// 현재 조합의 길이가 정해진 조합의 길이와 같다면 맛을 구하고 종료
		if(count == comb.length) {
			getMaxTaste();
//			System.out.println(Arrays.toString(comb));
			return;
		}
		
		// 칼로리 조합의 인덱스를 저장해놓으면 해당하는 맛도 불러올 수 있기 떄문에 인덱스를 저장한다.
		for(int idx = start; idx < ingreCount; idx++) {
			comb[count] = idx;
			combination(count + 1, idx + 1);
		}
	}

	// 부분집합
	private static void subSet(int count, int sumTaste, int sumCal) {
		if(count == comb.length) {
			if(sumCal <= maxCal && sumTaste > maxTaste)
				maxTaste = sumTaste;
			return;
		}
		
		visited[count] = true;
		subSet(count + 1, sumTaste + tastes[count], sumCal + calories[count]);
		visited[count] = false;
		subSet(count + 1, sumTaste, sumCal);
		
	}
	
	// dp - Knapsack 
	private static void Knapsack() {
		// dp 선언
		dp = new int[maxCal + 1];
		
		// 1부터 최대 칼로리까지의 모든 범위만큼 반복한다.
		// limit는 현재 허용 가능한 칼로리를 의미한다.
		for (int idx = 0; idx < ingreCount; idx++) {
			for (int limit = maxCal; limit > -1; limit--) { // 재료는 하나씩만 들어가므로 제한 칼로리부터 내려감
				if (calories[idx] <= limit) { // 만약 현재 재료의 칼로리가 들어갈 수 있다면 수행
					// 현 위치의 dp값을 현재 재료가 들어갔을 때의 점수의 최댓값과 현재값 중 큰 값으로 갱신
					dp[limit] = Math.max(dp[limit], dp[limit - calories[idx]] + tastes[idx]);
				}
			}
		}
	}
	
	// 맛의 합을 구하는 메서드
	// 해당 메서드에서는 조건이 두 개가 필요하다.
	// 1. 제한 칼로리를 넘지 않을 것
	// 2. 그 중에서 맛 점수가 제일 높은 것
	private static void getMaxTaste() {
		// 우선 제한 칼로리가 넘지 않는지 확인한다.
		int sumTaste = 0;	// 총 맛 점수
		int sumCal = 0;		// 총 칼로리 양
		
		// 해당 조합의 인덱스를 따라 맛과 칼로리의 합을 구한다.
		for(int idx = 0; idx < comb.length; idx++) {
			sumTaste += tastes[comb[idx]];
			sumCal += calories[comb[idx]];
		}
		
		if(sumCal <= maxCal && sumTaste > maxTaste)
			maxTaste = sumTaste;
//		System.out.println(maxTaste);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 총 테스트 케이스 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		// 총 테스트 케이스만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			// 테스트 케이스마다 초기화
			maxTaste = 0;
			
			// 전체 재료 수와 최대 칼로리 입력
			st = new StringTokenizer(br.readLine());
			ingreCount = Integer.parseInt(st.nextToken());
			maxCal = Integer.parseInt(st.nextToken());
			
			// 각각의 재료 정보 입력
			tastes = new int[ingreCount];
			calories = new int[ingreCount];
			
			for(int idx = 0; idx < ingreCount; idx++) {
				st = new StringTokenizer(br.readLine());
				tastes[idx] = Integer.parseInt(st.nextToken());
				calories[idx] = Integer.parseInt(st.nextToken());
			}
			
			// ------------------------------------------------------------
			// 정보 처리 끝
			// 저장된 정보로 결과를 도출하자.
			// 최대 칼로리 이하의 모든 조합을 구한다.
			// 해당 조합에 따른 맛도 구한다. -> 모든 조합의 경우마다 맛을 다 구한다.
			// 최대 맛과 비교하여 최대 맛 값을 출력한다.
			
			// 조합으로 구하기 -------------------------------------------------
			// 최대 재료의 수 만큼 개수를 달리해가며 조합을 구한다.
//			for(int idx = 1; idx <= ingreCount; idx++) {
//				comb = new int[idx];
//				combination(0, 0);
//			}
			
			// 부분집합으로 구하기 -----------------------------------------------
//			visited = new boolean[ingreCount];
//			for(int idx = 1; idx <= ingreCount; idx++) {
//				comb = new int[idx];
//				subSet(0, 0, 0);
//			}
			
			// 결과 저장
//			sb.append("#").append(tc).append(" ").append(maxTaste).append("\n");
			
			// dp로 구하기 ----------------------------------------------------
			Knapsack();
			sb.append("#").append(tc).append(" ").append(dp[maxCal - 1]).append("\n");
			
			
			
		}	// tc end
		// 결과 출력
		System.out.print(sb);
	}
}
