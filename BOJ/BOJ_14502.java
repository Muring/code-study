package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소
 * 
 * 인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 
 * 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
 * 
 * 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 
 * 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 
 *
 * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 
 * 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 * 
 * 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 
 * 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.
 * 
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다.
 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
 * 
 * [입력]
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
 * 둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 
 * 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.
 * 빈 칸의 개수는 3개 이상이다.
 * 
 * [출력]
 * 첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.
 * 
 * [풀이]
 * 벽을 세우고 바이러스를 퍼트린 뒤에 남아있는 안전 영역을 세어 최대 안전 영역을 구하면 된다.
 * 범위가 작기 때문에 벽을 세울 수 있는 모든 경우의 수를 전부 구해서 그냥 때려박아버린다.
 * 벽을 세운 뒤 BFS로 바이러스가 퍼질 수 있는 영역을 모두 구해 안전 영역을 구한다.
 * 계속 반복하여 정답을 구하면 된다.
 * 
 * @author sehyeon.eom
 *
 */
public class BOJ_14502 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int rowSize;
	static int colSize;
	static int[][] map;
	static int maxSafeZone;
	static int initCount;	// Initial safe zone count
	static List<Point> virusList;
	
	static int[] dRow = { -1, 1, 0, 0 };
	static int[] dCol = { 0, 0, -1, 1 };
	
	static final int MAX_WALL_COUNT = 3;
	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	
	private static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static void installWall(int wallCount) {
		// When the wall installation is complete
		if(wallCount == MAX_WALL_COUNT) {
			// spread the virus
			activateVirus();
			return;
		}
		
		// Install walls
		// Search the whole map which is blank and install walls
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				if(map[row][col] == EMPTY) {
					map[row][col] = WALL;
					installWall(wallCount + 1);
					// Restore map
					// This should be inside 'if' - If not it will make all walls disappear
					map[row][col] = EMPTY;
				}
			}
		}
	}
	
	// Spread virus with BFS method
	private static void activateVirus() {
		int tempCount = initCount - 3;	// Extract installed pillar count
		int[][] copy = copyMap();	// make copied map
		
		// Save virus data into que to use it in BFS
		Queue<Point> que = new LinkedList<>();
		for(int virusIdx = 0; virusIdx < virusList.size(); virusIdx++) {
			que.add(virusList.get(virusIdx));
		}
		
		// Do BFS
		while(!que.isEmpty()) {
			// Get current virus data
			Point now = que.poll();
			// Get current virus location
			int row = now.row;
			int col = now.col;
			
			for(int deltaIdx = 0; deltaIdx < dRow.length; deltaIdx++) {
				// Move to next area location
				int nextRow = row + dRow[deltaIdx];
				int nextCol = col + dCol[deltaIdx];
				
				// When next area is available
				if(isAvailable(nextRow, nextCol, copy)) {
					copy[nextRow][nextCol] = VIRUS;
					que.add(new Point(nextRow, nextCol));
					tempCount--;
				}
			}
		}
		
		// Count safe zones
		if(tempCount > maxSafeZone)
			maxSafeZone = tempCount;
	}
	
	// Deep copy - Shallow copy effects the origin map
	private static int[][] copyMap(){
		int[][] copy = new int[rowSize][colSize];
		
		for(int row = 0; row < rowSize; row++) {
			copy[row] = map[row].clone();
		}
		return copy;
	}
	
	// Check if next area is inside the map and empty
	private static boolean isAvailable(int row, int col, int[][] map) {
		if(row >= 0 && col >= 0 && row < rowSize && col < colSize && map[row][col] == EMPTY)
			return true;
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// Input map data
		initCount = 0;						// Start safe zone count
		map = new int[rowSize][colSize];	// New map
		virusList = new LinkedList<>();		// Save virus points here
		
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				// 해당 칸이 바이러스면
				if(map[row][col] == VIRUS)
					virusList.add(new Point(row, col));
				else if(map[row][col] == EMPTY)
					initCount++;
			}
		}	// Map input end
		
		installWall(0);
		
		System.out.println(maxSafeZone);
	}	// main end
}
