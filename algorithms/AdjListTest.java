package algorithms;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 무향 그래프 
public class AdjListTest {
	
	static class Node{
		int vertex;	// 관계를 맺고 있는 타정점 정보
		Node next;		// 연결리스트 유지를 위한 다음 노드 참조
		
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertext=" + vertex + ", next=" + next + "]";
		}
		
		
	}
	
	private static void bfs(Node adjList[]) {
		int size = adjList.length;
		Queue<Integer> que = new ArrayDeque<>();	// 큐에 넣는 값은 방문 대상을 관리할 값과 그 밖의 값들을 넣을 수 있다.
		boolean visited[] = new boolean[size];
		
		// 탐색 시작점 정점0으로 하자.
		que.offer(0);
		visited[0] = true;
		
		while(!que.isEmpty()) {
			int current = que.poll();
			System.out.println((char)(current + 65));
			// 출력 결과가 인접 행렬과는 다르게 나온다.
			// 이유는 현재 리스트에 저장하는 구조가 앞에 삽입하는 구조이기 때문에 순서가 밀리기 때문이다.
			
			// 현 정점의 인접 정점들을 체크하며 대기열에 넣기
			for(Node temp = adjList[current]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex]) {
					que.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}
	

	private static void dfs(Node adjList[], boolean visited[], int current) {
		visited[current] = true;		
		System.out.println((char)(current + 65));
		
		// 현 정점의 인접 정점들을 체크하며 대기열에 넣기
		for(Node temp = adjList[current]; temp != null; temp = temp.next) {
			// 인접행렬에서 현재 연결되어 있다는 표시가 배열에 1로 되어있기 때문에
			if(!visited[temp.vertex]) {
				dfs(adjList, visited, temp.vertex);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); // 정점 수
		int E = sc.nextInt(); // 간선 수
		
		Node adjList[] = new Node[V];	// 헤드리스트
		
		// 간선이 있으면 1, 없으면 0
		for(int idx = 0; idx < E; idx++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);	// 첫 원소로 삽입
			adjList[to] = new Node(from, adjList[to]);
		}
		

		bfs(adjList);

//		bfs(adjList);
		dfs(adjList, new boolean[V], 0);

	}
}
