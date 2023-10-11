package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 외판원 순회 2
 * 
 * 도시의 여행 순서를 순열로 모두 구하고 해당 순열들로 여행비용 최소값을 구한다.
 * @author sehyeon.eom
 *
 */

public class BOJ_10971 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int cityCount;
	static int cityMap[][];
	static int minCost;
	static int cityComb[];
	static boolean[] visited;
	
	// 나올 수 있는 도시의 모든 경우의 수를 구한다.
	private static void getCityPerm(int start, int count) {
		if(count == cityCount) {
//			System.out.println(Arrays.toString(cityComb));
			travel();
			return;
		}
		
		for(int idx = start; idx < cityCount; idx++) {
			if(visited[idx] != true) {
				cityComb[count] = idx;
				visited[idx] = true;
				getCityPerm(start , count + 1);
				visited[idx] = false;
			}

		}
	}
	
	// 얻어낸 도시의 순서로 여행을 시작한다.
	private static void travel() {
		// 여행을 시작한다.
		int sum = 0;
		for(int idx = 0; idx < cityCount; idx++) {
			int start = cityComb[idx];
			int arrive = cityComb[(idx + 1) % cityCount];
			if(cityMap[start][arrive] == 0) return;
			sum += cityMap[start][arrive];
		}
		// 최소 여행비용 구하기
		minCost = minCost < sum ? minCost : sum;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		minCost = Integer.MAX_VALUE;
		
		cityCount = Integer.parseInt(br.readLine().trim());
		
		// 맵의 데이터 입력
		cityMap = new int[cityCount][cityCount];
		for(int row = 0; row < cityCount; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < cityCount; col++) {
				cityMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		cityComb = new int[cityCount];
		visited = new boolean[cityCount];
		getCityPerm(0, 0);
		
		System.out.println(minCost);
	}
}
