package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTKruskalTest {
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static Edge[] edgeList;
	static int V, E;
	
	static int[] parents;
	
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int num) {
		if(parents[num] == num) return num;
		
		return parents[num] = find(parents[num]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;	// 싸이클 발생 했을 시
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		
		for(int idx = 0; idx < E; idx++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[idx] = new Edge(from, to, weight);
		}
		
		// 간선 리스트를 가중치 기준으로 오름차순 정렬
		Arrays.sort(edgeList);
		
		// V개의 정점으로 make set 작업
		make();
		
		int result = 0;	// MST 비용
		int count = 0;	// 연결된 간선 수
		for(Edge edge : edgeList) {	// 비용이 작은 간선순으로 꺼내어서 처리
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++count == V - 1) break;
			}
		}
		System.out.println(result);
	}
}
