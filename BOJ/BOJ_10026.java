package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 *
 * 적록색약
 * 적록색약인 사람은 빨강색과 초록색을 구별하지 못한다.
 * 즉, 같은 색깔로 보는 것이다.
 * RGB로 입력이 주어졌을 때, 정상인 사람과 적록색약인 사람이 봤을 때의 구역 개수를 구분해 출력하라.
 * 
 * 1. 그림의 크기가 주어진다.(NxN)
 * 2. N줄만큼 그림의 입력이 주어진다.
 * 3. 정상인과 적록색인이 봤을 때의 구역 수를 공백으로 구분해 출력
 * 
 * FloodFill을 사용하는 것 같다.
 * 처음 찾은 색깔의 위치에서 dfs를 수행하여 해당 색깔의 구역을 모두 방문처리한다.
 * -> 전 맵을 돌면서 방문처리가 되어있지 않으면 dfs를 수행하도록 한다.
 * -> dfs를 돌기 시작하는 현재 주소의 색깔만을 찾도록 하는 식으로 반복한다.
 * 
 */

// 현재 가리키고 있을 노드 클래스 정의
class Node{
	int row;
	int col;
	
	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}

public class BOJ_10026 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;			// 맵의 크기
	static char map[][];		// 맵 배열
	static boolean visited[][];	// 방문 배열
	static int normalCount;		// 일반 사람의 구역 카운트
	static int abnormalCount;	// 적록색약 사람의 구역 카운트
	
	// 텔다 배열
	static final int dRow[] = { -1, 1, 0, 0 };
	static final int dCol[] = { 0, 0, -1, 1 };
	
	private static void dfs(int startRow, int startCol) {
		Stack<Node> stack = new Stack<>();	// DFS를 위한 스택이다.
		
		// 스택에 첫 스타잉 포인트를 넣어준다.
		stack.add(new Node(startRow, startCol));
		
		// 스택이 비어있지 않을 때
		while(!stack.isEmpty()) {
			Node node = stack.pop();	// 노드에 있는 정보 꺼낸다.
			
			// 꺼낸 정보의 위치의 방문 체크
			visited[node.row][node.col] = true;
			
			for(int idx = 0; idx < dRow.length; idx++) {
				int nextRow = node.row + dRow[idx];
				int nextCol = node.col + dCol[idx];
				
				// 만약 다음 칸이 맵의 밖이라면
				if(nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize)
					continue;
				// 다음 칸이 이미 방문된 적이 있다면
				if(visited[nextRow][nextCol])
					continue;
				// 다음 칸의 색깔이 다르다면
				if(map[nextRow][nextCol] != map[node.row][node.col])
					continue;
				
				stack.push(new Node(nextRow, nextCol));
			}
		}
	}
	
	private static void abnormalDfs(int startRow, int startCol) {
		Stack<Node> stack = new Stack<>();
		
		// 스택에 첫 스타잉 포인트를 넣어준다.
		stack.add(new Node(startRow, startCol));
		
		// 스택이 비어있지 않을 때
		while(!stack.isEmpty()) {
			Node node = stack.pop();	// 노드에 있는 정보 꺼낸다.
			
			// 꺼낸 정보의 위치의 방문 체크
			visited[node.row][node.col] = true;
			
			for(int idx = 0; idx < dRow.length; idx++) {
				int nextRow = node.row + dRow[idx];
				int nextCol = node.col + dCol[idx];
				
				// 만약 다음 칸이 맵의 밖이라면
				if(nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize)
					continue;
				// 다음 칸이 이미 방문된 적이 있다면
				if(visited[nextRow][nextCol])
					continue;
				// 다음 칸의 색깔이 다르다면 -> 빨강과 초록은 같은 처리
				if((map[nextRow][nextCol] == 'B' || map[node.row][node.col] == 'B') && map[nextRow][nextCol] != map[node.row][node.col])
					continue;
				
				stack.push(new Node(nextRow, nextCol));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 그림 사이즈 입력
		mapSize = Integer.parseInt(br.readLine().trim());
		
		// 맵 생성
		map = new char[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		
		// 그림 정보 입력
		String str;	// 임시 저장 변수
		for(int row = 0; row < mapSize; row ++) {
			str = br.readLine().trim();
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = str.charAt(col);
			}
		}
		
		// 일반 사람이 봤을 떄의 구역 수를 구한다.
		for(int row = 0; row < mapSize; row ++) {
			for(int col = 0; col < mapSize; col++) {
				if(visited[row][col] == false) {
					dfs(row, col);
					normalCount++;
				}
			}
		}
		
		// 적록색약인 사람이 봤을 떄의 구역 수를 구한다.
		visited = new boolean[mapSize][mapSize];
		for(int row = 0; row < mapSize; row ++) {
			for(int col = 0; col < mapSize; col++) {
				if(visited[row][col] == false) {
					abnormalDfs(row, col);
					abnormalCount++;
				}
			}
		}
		
		// 결과 출력
		sb.append(normalCount).append(" ").append(abnormalCount);
		System.out.println(sb);
		
	}	// main end
}
