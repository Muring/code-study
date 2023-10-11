package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기 1
 * 
 * 새 집의 크기는 N×N의 격자판으로 나타낼 수 있고, 1×1크기의 정사각형 칸으로 나누어져 있다. 
 * 각각의 칸은 (r, c)로 나타낼 수 있다. 
 * 여기서 r은 행의 번호, c는 열의 번호이고, 행과 열의 번호는 1부터 시작한다. 
 * 각각의 칸은 빈 칸이거나 벽이다.
 * 
 * 파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.
 * 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 
 * 파이프는 밀면서 회전시킬 수 있다. 
 * 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.
 * 
 * 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 
 * 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.
 * 
 * [입력]
 * 첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다. 
 * 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 
 * 	- 빈 칸은 0, 벽은 1로 주어진다. 
 * 	- (1, 1)과 (1, 2)는 항상 빈 칸이다.
 * 
 * [출력]
 * 첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 
 * 이동시킬 수 없는 경우에는 0을 출력한다. 
 * 방법의 수는 항상 1,000,000보다 작거나 같다.
 * 
 * 1. 집의 크기를 입력 받는다.
 * 2. 집의 상태를 입력 받는다.
 * 3. 파이프 이동 시작
 * 	- 45도 각도로만 움직여 진다는 것은 현재 해당 파이프의 방향을 계산해야 한다는 것
 * 	- 파이프의 종류는 세 종류이기에 방향 벡터도 세 종류이다.
 * 	- 백트래킹을 사용한 경우의 수 구하기
 * @author sehyeon.eom
 *
 */

public class BOJ_17070 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;
	static int map[][];
	
	// 우 우하 하
	static final int dRow[] = { 0, 1, 1 };
	static final int dCol[]	= { 1, 1, 0 };
	static final int toggleArr[][] = {	// 현재 파이프 방향에 따른 델타 인덱스
			{ 0, 1 },	// 가로일 때
			{ 0, 2 },	// 대각선일 때
			{ 1, 2 }	// 세로일 때
	};
	
	private static void movePipe(int row, int col, int toggle) {
		// 기저조건
		if(row == mapSize - 1 && col == mapSize - 1) {
			return;
		}
		
		// 현재 파이프의 방향에 따른 deltaIdx 범위 조정
		int start = toggleArr[toggle][0];
		int end = toggleArr[toggle][1];

		// deltaIdx는 결국 toggle이다.
		// deltaIdx가 0이면 가로, 1이면 대각선, 2면 세로 방향으로 칸이 옮겨지기 때문이다. 
		for(int deltaIdx = start; deltaIdx <= end; deltaIdx++) {
			// 다음 칸 지정
			int nextRow = row + dRow[deltaIdx];
			int nextCol = col + dCol[deltaIdx];
			
			// 파이프가 맵 범위 안이고 이동하려는 칸이 전부 비어있다면
			if(isAvailable(nextRow, nextCol, deltaIdx)) {
				map[nextRow][nextCol] -= 1;
				movePipe(nextRow, nextCol, deltaIdx);
			}

		}
	}
	
	private static boolean isAvailable(int row, int col, int toggle) {
		// 맵 범위 안인지 확인
		if(row < 0 || col < 0 || row >= mapSize || col >= mapSize)
			return false;
		// 이동하려는 파이프의 경로가 비어있는지 확인
		// 파이프가 가로이거나 세로로 설치될 때
		if(toggle == 0 || toggle == 2) {
			if(map[row][col] == 1)
				return false;
		}
		// 파이프가 대각선으로 설치될 때 대각 칸의 왼쪽과 위쪽 칸을 확인한다.
		else if(toggle == 1) {
			if(map[row - 1][col] == 1 || map[row][col - 1] == 1 || map[row][col] == 1)
				return false;
		}
		// 모두 통과하면 true
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 집의 크기를 입력 받는다.
		mapSize = Integer.parseInt(br.readLine().trim());
		
		// 집의 상태를 입력 받는다.
		map = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		if(map[mapSize - 1][mapSize - 1] == 1)
			System.out.println(0);
		else {
			// 파이프 이동 시작
			movePipe(0, 1, 0);
			// 결과 출력
			System.out.println(map[mapSize - 1][mapSize - 1] * -1);
		}

//		// 맵 결과 확인용
//		for(int row = 0; row < mapSize; row++) {
//			for(int col = 0; col < mapSize; col++) {
//				System.out.print(map[row][col] + " ");
//			}
//			System.out.println();
//		}
	}
}
