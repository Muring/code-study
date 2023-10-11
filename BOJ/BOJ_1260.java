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
 * DFS와 BFS
 * 
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램
 * 방문할 수 있는 정점이 많은 경우 정점 번호가 작은 것부터 방문
 * 
 * 1. 첫째 줄에 정점의 개수, 간선의 개수, 탐색을 시작할 번호가 주어진다.
 * 2. 이후, 간선을 연결하는 두 정점의 번호가 주어진다.
 *    -> 주어지는 간선은 양방향이다.
 *    
 * 첫째 줄에 DFS 결과를, 둘째 줄에 BFS 결과를 출력한다.
 *
 */


public class BOJ_1260 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int vertexCount;
	static int edgeCount;
	static boolean check[];
	static int arr[][];
	static Queue<Integer> que = new LinkedList<>();
	
	
	private static void dfs(int start) {
		check[start] = true;		// 현재 정점 사용 확인
		sb.append(start).append(" ");	// 출력 저장
		
		// 정점의 개수 만큼 반복문을 돌린다.
		for(int idx = 0; idx <= vertexCount; idx++) {
			if(arr[start][idx] == 1 && !check[idx])	// 
				dfs(idx);
		}
	}
	
	private static void bfs(int start) {
		que.add(start);
		check[start] = true;
		
		while(!que.isEmpty()) {
			start = que.poll();
			sb.append(start).append(" ");
			
			for(int idx = 1; idx <= vertexCount; idx++) {
				if(arr[start][idx] == 1 && !check[idx]) {
						que.add(idx);
						check[idx] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫째 줄 입력
		// 정점의 개수 간선의 개수, 탐색을 시작할 번호
		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		int startVert = Integer.parseInt(st.nextToken());
		
		arr = new int[vertexCount + 1][vertexCount+ 1];
		check = new boolean[vertexCount + 1];
		
		// 간선 입력
		for(int idx = 0; idx < edgeCount; idx++) {
			st = new StringTokenizer(br.readLine());
			
			// 간선에 연결되어 있는 정점 값 입력
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = arr[b][a] = 1;	// 양방향 처리
		}
		
		dfs(startVert);
		sb.append("\n");
		check = new boolean[vertexCount + 1];
		bfs(startVert);
		
		System.out.println(sb);
		
	}
}
