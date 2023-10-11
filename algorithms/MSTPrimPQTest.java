package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MSTPrimPQTest {
	
	static int vertexCount;
	static int[][] adjMatrix;
	
	static class Vertex implements Comparable<Vertex>{
		int number;	// 정점 번호
		int weight;	// 간선 비용
		
		public Vertex(int number, int weight) {
			super();
			this.number = number;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		vertexCount = Integer.parseInt(br.readLine().trim());
		adjMatrix = new int[vertexCount][vertexCount];
		
		// 인접행렬 입력
		for(int row = 0; row < vertexCount; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < vertexCount; col++) {
				adjMatrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방문정점 (트리정점표시)
		boolean[] visited = new boolean[vertexCount];
		int[] minEdge = new int[vertexCount];	// 자신과 트리의 정점들 간 최소 간선 비용
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;	// 임의의 0정점을 트리 구성의 시작으로 하기 위해 세팅
		pq.offer(new Vertex(0, 0));
		
		int result = 0;	// 최소신장트리 비용
		int min = 0, minVertex = 0, count = 0;
		
		while(!pq.isEmpty()) {

			// step 1: 미방문 정점 중 최소간선비용의 정점 선택
			Vertex current = pq.poll();			
			minVertex = current.number;
			min = current.weight;

			if(visited[minVertex]) continue;
			
			if(++count == vertexCount);
						
			// step 2: 방문 정점에 추가
			visited[minVertex] = true;
			result += min;
			
			// step 3: 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선 비용 고려
			for(int idx = 0; idx < vertexCount; idx++) {
				if(!visited[idx] && adjMatrix[minVertex][idx] != 0 && minEdge[idx] > adjMatrix[minVertex][idx]) {
					minEdge[idx] = adjMatrix[minVertex][idx];
					pq.offer(new Vertex(idx, minEdge[idx]));	//////
				}
			}
		}
		
		
		System.out.println(result);
	}	// main end

}
