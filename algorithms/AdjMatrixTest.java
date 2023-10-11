package algorithms;

import java.util.*;

public class AdjMatrixTest {
	
	private static void bfs(int adjMatrix[][]) {
		int size = adjMatrix.length;
		Queue<Integer> que = new ArrayDeque<>();	// 큐에 넣는 값은 방문 대상을 관리할 값과 그 밖의 값들을 넣을 수 있다.
		boolean visited[] = new boolean[size];
		
		// 탐색 시작점 정점0으로 하자.
		que.offer(0);
		visited[0] = true;
		
		while(!que.isEmpty()) {
			int current = que.poll();
			System.out.println((char)(current + 65));
			
			// 현 정점의 인접 정점들을 체크하며 대기열에 넣기
			for(int idx = 0; idx < size; idx++) {
				// 인접행렬에서 현재 연결되어 있다는 표시가 배열에 1로 되어있기 때문에
				if(adjMatrix[current][idx] != 0 && !visited[idx]) {
					que.offer(idx);
					visited[idx] = true;
				}
			}
		}
	}

	
	private static void dfs(int[][] adjMatrix, boolean visited[], int current) {

		visited[current] = true;		
		System.out.println((char)(current + 65));
		
		// 현 정점의 인접 정점들을 체크하며 대기열에 넣기
		for(int idx = 0, size = adjMatrix.length; idx < size; idx++) {
			// 인접행렬에서 현재 연결되어 있다는 표시가 배열에 1로 되어있기 때문에
			if(adjMatrix[current][idx] != 0 && !visited[idx]) {
				dfs(adjMatrix, visited, idx);
			}
		}
	}
	
	// 무향 그래프
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); // 정점 수
		int E = sc.nextInt(); // 간선 수
		
		int adjMatrix[][] = new int[V][V]; // 초기값 0
		
		// 간선이 있으면 1, 없으면 0
		for(int idx = 0; idx < E; idx++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
		
		bfs(adjMatrix);

//		bfs(adjMatrix);
		dfs(adjMatrix, new boolean[V], 0);
		
		sc.close();
	}
}
