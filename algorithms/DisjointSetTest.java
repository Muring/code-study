package algorithms;

import java.util.Arrays;

// 서로소 트리
public class DisjointSetTest {

	static int N; // 초기 집합의 개수
	static int parents[];
	
	// idx를 원소로 갖는 최소 단위 집합 만드는 메소드
	private static void make() {
		parents = new int[N];
		for(int idx = 0; idx < N; idx++) {
			parents[idx] = idx; 	// 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다. (자신이 곧 패효자인 루트로 표현)
		}
	}
	
	// 받아온 num의 루트 노드를 찾는 메소드
	private static int find(int num) {
		if(num == parents[num]) return num;
		return parents[num] = find(parents[num]);	// path compression
	}
	
	// a가 속한 집합과 b가 속한 집합을 합칠 수 있다면 합치고 true 반환, 아니면 false 반환
	private static boolean union(int a, int b) {	
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;	// 서로의 대표자가 같은 즉, 같은 집합이므로 union하지 않음
		// union 처리 (bRoot를 aRoot 아래로 붙이기) -> 임의로 한 것
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) {
		N = 5;
		make();
		System.out.println(Arrays.toString(parents));
		System.out.println(union(0, 1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(2, 1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(3, 2));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(4, 3));
		System.out.println(Arrays.toString(parents));
		
		System.out.println("====================find====================");
		System.out.println(find(4));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(3));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(2));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(1));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(0));
		System.out.println(Arrays.toString(parents));
	}
}
