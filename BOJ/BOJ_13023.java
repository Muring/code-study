package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 알고리즘 캠프에는 총 N명이 참가한다.
 * 사람들은 0번부터 N-1번으로 번호가 매겨져 있고, 일부 사람들은 친구이다.
 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보아라.
 * - A는 B와 친구이다.
 * - B는 C와 친구이다.
 * - C는 D와 친구이다.
 * - D는 E와 친구이다.
 * 위와 같은 친구 관계가 존재하는지 안하는지 구하시오.
 * 
 * 1. 첫째 줄에 사람의 수 N과 친구 관계의 수 M이 주어진다.
 * 2. 둘째 줄부터 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다.
 * 3. 문제 조건에 맞는 관계가 존재하면 1, 없으면 0을 출력한다.
 * 
 * 이건 어떻게 푸는걸까~ 그래프네~
 * DFS 탐색으로 문제를 해결한다.
 * 다만 단순 DFS로 하면 예외 상황이 발생하기 때문에 visited를 사용한다.
 * 
 * 
 * @author sehyeon.eom
 *
 */


public class BOJ_13023 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int totalCount;	// 전체 사람 수
	static int relateCount;	// 친구 관계 수
	static boolean flag;	// 관계가 있는지 없는 나타내는 변수
	
	static ArrayList<Integer>[] relation;	// 관계 그래프
	static boolean visited[];				// 방문 확인 배열
	
	
	private static void dfs(int person, int count) {
		if(count == 4) {
			flag = true;
			return;
		}
		
		// 현재 탐색하는 사람 방문 처리
		visited[person] = true;
		// 해당 사람과 친구인 다음 사람 탐색
		for(int nextPerson: relation[person]) {
			// 다음 사람이 이미 방문 처리가 되어있다면
			if(visited[nextPerson])	continue;
			dfs(nextPerson, count + 1);
		}
		// 다 끝나고 나오면 다시 미방문 처리
		visited[person] = false;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {	
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫째 줄 입력
		st = new StringTokenizer(br.readLine());
		totalCount = Integer.parseInt(st.nextToken());
		relateCount = Integer.parseInt(st.nextToken());
		
		// 입력 값 토대로 배열 및 리스트 생성
		relation = new ArrayList[totalCount];
		
		for(int idx = 0; idx < totalCount; idx++) {
			relation[idx] = new ArrayList<>();
		}
		
		// 관계 입력
		int f1, f2;
		for(int idx = 0; idx < relateCount; idx++) {
			st = new StringTokenizer(br.readLine());
			f1 = Integer.parseInt(st.nextToken());
			f2 = Integer.parseInt(st.nextToken());
			
			relation[f1].add(f2);
			relation[f2].add(f1);
		}
		// 간선을 돌아다니며 결과 확인
		// 각 사람이 번호로 저장되어 있기에 해당 사람의 번호를 dfs로 넘기면 된다.
		visited = new boolean[totalCount];
		for(int person = 0; person < totalCount; person++) {
			dfs(person, 0);
			
			// 관계가 존재한다면 탈출
			if(flag) break;
		}
		// 결과 출력
		System.out.println(flag ? 1: 0);
	}	// main
}
