package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 탈출
 * 
 * 티떱숲의 지도는 R행 C열로 이루어져 있다. 
 * 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 
 * 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 * 매 분마다 고슴도치와 물은 인접한 네 칸 중 하나로 이동한다.
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 
 * 1. 맵의 크기를 입력 받는다.
 * 2. 맵의 정보를 입력받는다.
 * 3. 물이 움직이고 고슴도치가 움직여도 로직은 똑같기 때문에 물을 먼저 퍼뜨리는 방식으로 알고리즘을 짠다.
 *   - 물과 고슴도치 모두 BFS로 이동
 * 4. 고슴도치가 무사히 굴에 도착하면 이동하는데 걸린 시간을, 도착하지 못하면 KAKTUS 출력
 *
 */

public class BOJ_3055 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;
	static char[][] map;
	static Queue<Point> hogQue;
	static Queue<Point> waterQue;
	static int minCount;
	
	static int[] dRow = { -1, 1, 0, 0 };
	static int[] dCol = { 0, 0, -1, 1 };
	
	static class Point {
		int row, col;
		int count;
		
		public Point(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
		
	}
	
	private static void runHome() {
		
		while(!hogQue.isEmpty()) {
			// 물을 먼저 퍼뜨려야 한다.
			// 물이 몇 칸을 차지하는지 확인
			int waterLen = waterQue.size();
			for(int waterIdx = 0; waterIdx < waterLen; waterIdx++) {
				Point curWater = waterQue.poll();
				int row = curWater.row;
				int col = curWater.col;
				
				for(int deltaIdx = 0; deltaIdx < dRow.length; deltaIdx++) {
					int nextRow = row + dRow[deltaIdx];
					int nextCol = col + dCol[deltaIdx];
					if(isAvailable(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
						map[nextRow][nextCol] = '*';
						waterQue.add(new Point(nextRow, nextCol, 0));
					}
				}
			}	// water end
			
//			for(int row = 0; row < rowSize; row++) {
//				System.out.println(Arrays.toString(map[row]));
//			}
//			System.out.println();
			// 이제 고슴도치를 움직인다.
			int hogLen = hogQue.size();
			for(int hogIdx = 0; hogIdx < hogLen; hogIdx++) {
				Point curHog = hogQue.poll();
				int row = curHog.row;
				int col = curHog.col;
				int count = curHog.count;
				for(int deltaIdx = 0; deltaIdx < dRow.length; deltaIdx++) {
					int nextRow = row + dRow[deltaIdx];
					int nextCol = col + dCol[deltaIdx];
					if(isAvailable(nextRow, nextCol)) {
						// 고슴도치가 도착했다면
						if(map[nextRow][nextCol] == 'D') {
							minCount = Math.min(minCount, count + 1);
							return;
						}
						// 평지라면
						else if(map[nextRow][nextCol] == '.') {
							map[nextRow][nextCol] = 'S';
							hogQue.add(new Point(nextRow, nextCol, count + 1));
						}
					}
				}
			}
		}
	}
	
	private static boolean isAvailable(int row, int col) {
		if(row >= 0 && row < rowSize && col >= 0 && col < colSize)
			return true;
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		minCount = Integer.MAX_VALUE;
		
		// 맵 크기 입력
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 맵 입력
		map = new char[rowSize][colSize];
		hogQue = new LinkedList<>();
		waterQue = new LinkedList<>();
		
		for(int row = 0; row < rowSize; row++) {
			String str = br.readLine();
			for(int col = 0; col < colSize; col++) {
				map[row][col] = str.charAt(col);
				// 고슴도치 시작 지점
				if(map[row][col] == 'S') 
					hogQue.add(new Point(row, col, 0));
				// 물 시작 지점
				else if(map[row][col] == '*')	
					waterQue.add(new Point(row, col, 0));
			}
		}
		
		// 고슴도치야 집으로 뛰어!
		runHome();
		
		// 출력
		System.out.println(minCount == Integer.MAX_VALUE ? "KAKTUS" : minCount);
	}
}
