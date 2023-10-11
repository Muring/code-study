package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이동하기
 * 
 * 준규는 NxM 크기의 미로에 갇혀있다.
 * 미로의 각 방에는 사탕이 놓여져 있다.
 * 미로의 가장 왼쪽 윗 방은 (1, 1)이고 가장 오른쪽 아랫 방은 (N, M)이다.
 * 준규는 현재(1, 1)에 있고, (N, M)으로 이동하려고 한다.
 * 준규가 (r, c)에 있으면  (r+1, c), (r, c+1), (r+1, c+1)로 이동할 수 있고, 
 * 각 방을 방문할 때마다 방에 놓여져있는 사탕을 모두 가져갈 수 있다.
 * 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수의 최댓값을 구하시오.
 * 
 * [입력]
 * 첫째 줄에 미로의 크기 N, M이 주어진다. (1 ≤ N, M ≤ 1,000)
 * 둘째 줄부터 N개 줄에는 총 M개의 숫자가 주어지며, r번째 줄의 c번째 수는 (r, c)에 놓여져 있는 사탕의 개수이다. 
 * 사탕의 개수는 0보다 크거나 같고, 100보다 작거나 같다.
 * 
 * [출력]
 * 첫째 줄에 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수를 출력한다.
 * 
 * 1. 맵의 정보를 입력 받는다.
 * 3. 맵에서 다음 칸으로 갈 때 최대한 많은 사탕을 가져가는 경우의 사탕 개수 값을 하나씩 더하며 저장
 * 	- 우선 0행과 0열은 직선으로 모든 값을 더하여 저장 - 직선으로 갔을 경우의 사탕 개수 저장
 * 	- 이후 다른 모든 칸은 →,↘,↓방향의 값을 전부 비교하여 가장 큰 값을 해당 칸에 저장한다.
 * 	- 최종 적으로 (N, M)에 저장되는 사탕 개수 값이 최대 사탕 개수 값이다.
 * 4. 결과 출력
 * @author sehyeon.eom
 *
 */

public class BOJ_11048 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int map[][];
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 맵의 크기 입력
		st = new StringTokenizer(br.readLine());
		int rowSize = Integer.parseInt(st.nextToken());
		int colSize = Integer.parseInt(st.nextToken());
		
		// 맵의 정보를 입력 받는다.
		map = new int[rowSize][colSize];
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 우선 0행과 0열은 직선으로 모든 값을 더하여 저장 
		for(int idx = 1; idx < rowSize; idx++) {
			map[idx][0] += map[idx - 1][0];
		}
		for(int idx = 1; idx < colSize; idx++) {
			map[0][idx] += map[0][idx - 1];
		}
		
		// 이후 모든 경로 계산
		for(int row = 1; row < rowSize; row++) {
			for(int col = 1; col < colSize; col++) {
				map[row][col] += Math.max(Math.max(map[row - 1][col], map[row][col - 1]), map[row - 1][col - 1]);
			}
		}
		System.out.println(map[rowSize - 1][colSize - 1]);
	}
}
