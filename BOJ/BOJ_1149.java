package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * RGB거리
 * 
 * 집이 N개 있다.
 * 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번집까지 있다.
 * 집은 빨강 초록 파랑 중 하나의 색으로 칠해야 한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때 규칙을 만족하며 모든 집을 칠하는 비용의 최솟값을 구하라.
 * - 1번 집의 색은 2번집의 색과 같지 않아야 한다.
 * - N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * - i(2 <= i <= N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * [입력]
 * 첫째 줄에 집의 수 N이 주어진다.
 * 둘째 줄부터 N개의 줄에는 각 집을 빨강 초록 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
 * 집을 칠하는 비용은 1000보다 작거나 같은 수이다.
 * 
 * [출력]
 * 첫재 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 * 
 * 1. 전체 집의 수 입력
 * 2. 각 집의 색깔별 비용 입력
 * 3. 색칠 시작
 * 	- 비용이 저장된 배열과 같은 공간의 dp공간 생성
 *  - 해당 dp 공간에 각 최소 비용 값 저장
 *  - 마지막 행에 저장된 결과 중 최소 값 반환
 * 
 * @author sehyeon.eom
 *
 */

public class BOJ_1149 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int houseCount;		// 전체 집 개수
	static int[][] costList;	// R G B
	static int[][] costSumList;	// dp - 0행부터 각 경우의 수 마다의 최소 페인트 비용 값 저장 배열
	
	static final int COLOR_COUNT = 3;
	static final int[][] colorCheckArr = {
			{ 1, 2 },	// R
			{ 0, 2 },	// G
			{ 0, 1 }	// B
	};
	
	private static void paintStart() {
		// 비용이 저장된 배열과 같은 공간의 dp공간 생성
		costSumList = new int[houseCount][COLOR_COUNT];
		costSumList = copy();
		
		// 해당 dp 공간에 각 최소 비용 값 저장
		for(int houseIdx = 1; houseIdx < houseCount; houseIdx++) {
			for(int colorIdx = 0; colorIdx < COLOR_COUNT; colorIdx++) {
				// 각 행의 각 칸에 들어갈 수 있는 최소의 합을 넣어 저장한다.
				costSumList[houseIdx][colorIdx]
						+= Math.min(costSumList[houseIdx - 1][colorCheckArr[colorIdx][0]],
								costSumList[houseIdx - 1][colorCheckArr[colorIdx][1]]);
			}
		}
		
		// 마지막 행에 저장된 결과 중 최소 값 반환
		int answer = costSumList[houseCount - 1][0];
		for(int idx = 1; idx < COLOR_COUNT; idx++) {
			answer = Math.min(costSumList[houseCount - 1][idx], answer);
		}
		System.out.println(answer);
	}
	
	private static int[][] copy() {
		int copyList[][] = new int[houseCount][COLOR_COUNT];
		
		for(int row = 0; row < houseCount; row++) {
			copyList[row] = Arrays.copyOf(costList[row], COLOR_COUNT);
		}
		return copyList;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 집의 수 입력
		houseCount = Integer.parseInt(br.readLine().trim());
		
		// 각 집의 색깔별 비용 입력
		costList = new int[houseCount][COLOR_COUNT];
		for(int idx = 0; idx < houseCount; idx++) {
			st = new StringTokenizer(br.readLine());
			costList[idx][0] = Integer.parseInt(st.nextToken());
			costList[idx][1] = Integer.parseInt(st.nextToken());
			costList[idx][2] = Integer.parseInt(st.nextToken());
		}
		
		// 색칠 시작
		paintStart();
	}
}
