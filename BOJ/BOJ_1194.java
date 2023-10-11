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
 * 달이 차오른다, 가자
 * 
 * 빈 칸: 언제나 이동할 수 있다. ('.')
 * 벽: 절대 이동할 수 없다. ('#')
 * 열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
 * 문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
 * 민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
 * 출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')
 * 민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 * 
 * [입력]
 * 첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 
 * 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다. 
 * 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다. 
 * 그리고, 문에 대응하는 열쇠가 없을 수도 있다. 
 * '0'은 한 개, '1'은 적어도 한 개 있다. 열쇠는 여러 번 사용할 수 있다.
 * 
 * [출력]
 * 첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 
 * 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.
 * 
 * [풀이]
 * 1. 맵의 크기를 입력받는다.
 * 2. 맵의 정보를 입력받는다.
 * 3. 민식이 출발
 * 	 0에서 출발해 1을 향해 가는 것이다.
 * 	  완전탐색으로 가야하므로 bfs가 메인이다.
 * 	  해당 문제에서는 신경써야하는 정보가 두가지 있다.
 *   	- 키의 저장 
 *   		-> 키의 저장은 6자리 OR연산
 *   		-> 문을 열 수 있는지 확인은 해당 문과 키를 AND연산
 *   	- 키를 먹고 돌아오는길이 있어야하기 때문에 따로 처리된 방문처리
 *    		-> 어느 한 열쇠를 획득했다면 다시 처음부터 방문처리
 *    		-> 각각 어느 한 열쇠를 얻었을 때의 방문기록을 따로 가져가야한다.
 *    		-> 따라서3차원 visited 배열 사용
 * 	 		-> visited[무슨 키를 가지고 있는지 비트값][row][col]
 *   
 *   
 *
 */
public class BOJ_1194 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;	// 맵의 사이즈
	static char[][] map;			// 맵 배열
	static boolean visited[][][];	// 방문 배열
	static int minCount;			// 최소 정답 값
	static Node startPoint;			// 시작 지점
	
	static int[] dRow = { -1, 1, 0, 0 };
	static int[] dCol = { 0, 0, -1, 1 };
	
	private static class Node {
		int row, col;
		int count;
		int key;
		
		public Node(int row, int col, int count, int key) {
			this.row = row;
			this.col = col;
			this.count = count;
			this.key = key;
		}
	}
	
	private static int escapeMaze() {
		// 큐에 시작 지점 노드 저장 및 방문처리
		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(startPoint.row, startPoint.col, 0, 0));
		visited[0][startPoint.row][startPoint.col] = true;
		
		// 방문 시작
		while(!que.isEmpty()) {
			// 큐에 저장된 지점 뽑아온다.
			Node point = que.poll();
			int row = point.row;
			int col = point.col;
			int count = point.count;
			int key = point.key;
			
			// 기저조건
			if(map[row][col] == '1') {
				return count;
			}
			
			// 사방탐색
			for(int idx = 0; idx < dRow.length; idx++) {
				int nextRow = row + dRow[idx];
				int nextCol = col + dCol[idx];
				
				// 맵 범위 안에 있고 해당 공간이 벽이 아니고 해당 키를 들고 방문한 적이 없다면
				if(isBoundary(nextRow, nextCol) && map[nextRow][nextCol] != '#' && !visited[key][nextRow][nextCol]) {
					// 해당 칸이 갈 수 있는 공간이라면 방문처리하고 큐에 저장하여 이동처리한다.
					if(map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == '0'|| map[nextRow][nextCol] == '1') {
						visited[key][nextRow][nextCol] = true;
						que.offer(new Node(nextRow, nextCol, count + 1, key));
					}
					// 갈 수 있지만 키가 있는 곳이라면
					else if(map[nextRow][nextCol] >= 'a' && map[nextRow][nextCol] <= 'z') {
						// 우선 새로운 키를 비트연산으로 해당하는 키로 저장
						int newKey = 1 << (map[nextRow][nextCol] - 'a');
						// or연산으로 키 목록 업데이트
						newKey = newKey | key;
						// 그리고 방문된 현재 칸을 각 키값의 방문 배열 모두 방문처리하고 다음을 위한 큐에 저장
						// 어차피 새로운 키로만 쓸 것이기 때문에 새로운 키가 들어있는 노드를 큐에 저장한다.
						if(!visited[newKey][nextRow][nextCol]) {
							visited[key][nextRow][nextCol] = true;
							visited[newKey][nextRow][nextCol] = true;
							que.offer(new Node(nextRow, nextCol, count + 1, newKey));
						}
					}
					// 갈 수 없지만 문이 있는 곳이라면
					else if(map[nextRow][nextCol] >= 'A' && map[nextRow][nextCol] <= 'Z') {
						// 문의 값을 비트연산자로 구한다.
						// 키는 소문자를 뺐고, 문은 대문자를 뺐으니 상응하는 값이 키로 열 수 있는 문이다.
						int door = 1 << (map[nextRow][nextCol] - 'A');
						// 키 값과 문의 값을 AND연산 했을 때 1이 나오면 해당하는 문에 대한 키가 있다는 것이므로 문을 열고 이동
						if((key & door) > 0) {
							visited[key][nextRow][nextCol] = true;
							que.offer(new Node(nextRow, nextCol, count + 1, key));
						}
					}
				}
			}
		}
		
		// 위의 모든 조건을 통과하지 못한 경우는 진행할 수 없다는 의미
		return -1;
	}
	
	private static boolean isBoundary(int row, int col) {
		return (row >= 0 && row < rowSize) && (col >= 0 && col < colSize);
			
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 맵의 사이즈 입력
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 맵과 visited 배열 초기화
		map = new char[rowSize][colSize];
		visited = new boolean[64][rowSize][colSize];
		
		// 맵 정보 입력
		for(int row = 0; row < rowSize; row++) {
			String temp = br.readLine();
			for(int col =0; col < colSize; col++) {
				map[row][col] = temp.charAt(col);
				// 시작 지점 저장
				if(map[row][col] == '0')
					startPoint = new Node(row, col, 0, 0);
			}
		}
		
		System.out.println(escapeMaze());
		
	}
}