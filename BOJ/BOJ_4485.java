package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 녹색 옷 입은 애가 젤다지?
 * 
 * [입력]
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * 각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. 
 * (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.
 * 이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 
 * 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 
 * 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
 * 
 * [출력]
 * 각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.
 * Problem 1: 20
 * Problem 2: 19
 * Problem 3: 36
 * 
 * [풀이]
 * 1. 동굴의 크기를 입력 받는다.
 * 	1-1. 이때 0을 입력받으면 종료
 * 2. 동굴의 정보 입력
 * 3. 젤다를 이동시켜 최소 루피를 잃는 경우를 찾는다.
 * 	3-1. 완탐이므로 bfs사용
 * 4. 결과 출력
 *
 */

public class BOJ_4485 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;
	static int mapSize;
	static int minRupee;
	static boolean[][] visited;
	static int testCaseIdx;
	
	static final int[] D_ROW = { -1, 1, 0, 0 };
	static final int[] D_COL = { 0, 0, -1, 1 };
	
	// 자동적으로 다음 가능한 위치 중 루피 값이 가장 작은 것을 우선적으로 탐색하도록 하기 위해서 우선순위 큐 사용
	// 우선순위 큐를 사용하기 위해 Comparable사용
	static class Node implements Comparable<Node>{
		int row;
		int col;
		int rupee;
		
		public Node(int row, int col, int rupee) {
			this.row = row;
			this.col = col;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(Node o) {
			return this.rupee - o.rupee;
		}
	}
	
	// BFS 원리로 작동한다.
	private static void moveZelda(int row, int col, int lost) {
		// 큐 선언 후 값 넣어주기
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(row, col, lost));
		visited = new boolean[mapSize][mapSize];
		
		// 반복 시작
		while(!que.isEmpty()) {
			Node currentNode = que.poll();
			int currentRow = currentNode.row;
			int currentCol = currentNode.col;
			int rupee = currentNode.rupee;
			
			for(int deltaIdx = 0; deltaIdx < D_ROW.length; deltaIdx++) {
				int nextRow = currentRow + D_ROW[deltaIdx];
				int nextCol = currentCol + D_COL[deltaIdx];
				
				if(isAvailable(nextRow, nextCol)) {
					map[nextRow][nextCol] += rupee;
					visited[nextRow][nextCol] = true;
					que.offer(new Node(nextRow, nextCol, map[nextRow][nextCol]));
				}
			}
		}
		// 정답 찾기
		minRupee = map[mapSize - 1][mapSize - 1];
		sb.append("Problem ").append(++testCaseIdx).append(": ").append(minRupee).append("\n");
	}
	
	private static boolean isAvailable(int row, int col) {
		if(row >= 0 && col >= 0 && row < mapSize && col < mapSize && visited[row][col] == false)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 선언
		int currentRupee = 0;
		
		while(true) {
			mapSize = Integer.parseInt(br.readLine().trim());
			if(mapSize == 0) break;	// 0이면 종료
			
			// 초기화
			map = new int[mapSize][mapSize];
			minRupee = Integer.MAX_VALUE;
			currentRupee = 0;
			
			// 맵 입력
			for(int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if(row == 0 && col == 0)
						currentRupee = map[row][col];
				}
			}
			
			// 젤다 동굴 탐색
			moveZelda(0, 0, currentRupee);
		}
		System.out.println(sb);
	}
}
