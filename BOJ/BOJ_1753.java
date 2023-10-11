package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
 *  단, 모든 간선의 가중치는 10 이하의 자연수이다.
 *  
 *  i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력
 * @author sehyeon.eom
 *
 */

public class BOJ_1753 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int vertexCount;		// 전체 정점 수
	static int edgeCount;		// 간선 수
	static List<List<Node>> nodeList;	// 노드 그래프 리스트
	static int[] distance;		// 각 노드별 최소 거리 배열

	static final int INF = Integer.MAX_VALUE;
	
	
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		
		distance[start] = 0;	// 시작점 거리 0
		que.add(new Node(start, 0));	// 시작 노드 적재
		
		while(!que.isEmpty()) {
			Node current = que.poll();
			
			int now = current.vertex;
			int dist = current.weight;
			
			if(distance[now] < dist)
				continue;
			
			// 현재 노드와 연결된 모든 노드들에 대하여
			for(Node next: nodeList.get(now)) {
				int cost = dist + next.weight;
				if(cost < distance[next.weight]) {
					distance[next.weight] = cost;
					que.add(new Node(next.vertex, cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫째 줄 입력
		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		int startIdx = Integer.parseInt(br.readLine().trim());
		
		nodeList = new ArrayList<>();
		for(int idx = 0; idx <= vertexCount; idx++) {
			nodeList.add(new ArrayList<>());
		}
		
		for(int idx = 0; idx < edgeCount; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodeList.get(from).add(new Node(to, weight));	// 리스트의 인덱스가 노드의 번호이고 노드 안의 vertex가 다음 노드의 값이다.
		}
		
		distance = new int[vertexCount + 1];
		Arrays.fill(distance, INF);
		
		dijkstra(startIdx);
		
		// 결과
		for(int idx = 1; idx <= vertexCount; idx++) {
			if(distance[idx] == INF) {
				sb.append("INF").append("\n");
			}
			else
				sb.append(distance[idx]).append("\n");
		}
		
		System.out.println(sb);
	}
}
