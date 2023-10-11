package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  
 * 가장 첫 번째 줄에는 전체 test case의 수 T(1≤T≤10)가 주어진다. 
 * 각 케이스의 첫째 줄에는 정점의 개수 V(1≤V≤100,000)와 간선의 개수 E(1≤E≤200,000)가 주어진다.
 * 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
 * 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다.
 * C는 음수일 수도 있으며, 절대값이 1,000,000을 넘지 않는다.
 * 
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * @author sehyeon.eom
 *
 */

public class SWEA_3124 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int vertexCount;
	static int edgeCount;
	static Edge[] edgeList;
	static int[] parents;
	
	
	private static class Edge implements Comparable<Edge>{
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
	
	// 자기 자신을 집합으로 하는 최소 단위 집합 만들기
	private static void make() {
		parents = new int[vertexCount + 1];
		
		for(int idx = 0; idx < vertexCount; idx++) {
			parents[idx] = idx;
		}
	}
	
	// 해당 원소의 부모 찾는 메서드
	private static int find(int element) {
		// 스스로가 부모라면 더 위로는 없기 때문에 리턴
		if(parents[element] == element)
			return element;
		
		// 경로 압축
		// 스스로가 부모가 아니라면 다음 부모를 찾아간다.
		return parents[element] = find(parents[element]);
		
	}
	
	// 두 집합을 하나로 묶는다.
	private static boolean union(int e1, int e2) {
		int e1Root = find(e1);
		int e2Root = find(e2);
		
		if(e1Root == e2Root) return false;
		
		parents[e1Root] = e2Root;	// e2의 부모가 e1의 부모와 같게 한다.
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			
			// 정점과 간선 개수 입력
			st = new StringTokenizer(br.readLine());
			vertexCount = Integer.parseInt(st.nextToken());
			edgeCount = Integer.parseInt(st.nextToken());
			
			// 입력 받은 데이터로 엣지 저장 배열 생성
			edgeList = new Edge[edgeCount];
			// 정점의 데이터 입력
			int from = 0, to = 0, weight = 0;
			for(int idx = 0; idx < edgeCount; idx++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				edgeList[idx] = new Edge(from, to, weight);
			}
			
			// 간선 가중치로 오름차순 정렬
			Arrays.sort(edgeList);
			
			// 가중치가 가장 낮은 간선부터 선택하면서 트리 증가
			// 우선 각 정점 make set 작업
			make();
			
			long result = 0;	// MST = 최소 비용
			int count = 0;	// 간선 수 세기
			for(Edge edge: edgeList) {
				if(union(edge.from, edge.to)) {	// 싸이클이면 false를 반환하기 때문에 싸이클이 아닐경우
					result += edge.weight;
					if(++count == vertexCount - 1) break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}	// tc end
		System.out.println(sb);
	}	// main end
	
}
