package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 다리 만들기 2
 * 
 * [입력]
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 
 * 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다.
 * 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.
 * 
 * [출력]
 * 모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 
 * 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.
 *
 */

public class BOJ_17472 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;
	static int rowSize, colSize;
	static int minLen;
	
	// 하 우
	static final int[] dRow = { 1, 0 };
	static final int[] dCol = { 0, 1 };
	
	static final int ISLAND = 1;
	static final int SEA = 0;

	private static void findIsland() {
		
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				if(map[row][col] == ISLAND) {
					
				}
			}
		}
		
	}
	

	
	private static boolean isAvailable(int row, int col) {
		if(row < rowSize && col < colSize && map[row][col] == SEA)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		findIsland();
		
	}	// main end

}
