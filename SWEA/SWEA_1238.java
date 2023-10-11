package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.sound.sampled.DataLine;

/**
 * 10개의 테스트 케이스가 주어진다.
 * 각 테스트 케이스 첫 번째 줄에는 입력 받는 데이터의 길이와 시작점이 주어진다.
 * 그 다음 줄에 입력받는 데이터는 {from, to, from, to}의 순서로 해석된다.
 * 같은 비상연락망을 표현하는 다양한 인풋이 존재한다.
 * 동일한 from to 쌍이 반복되는 경우도 있으며, 한 번 기록된 경우와 여러 번 기록된 경우의 차이는 없다.
 * #부호와 함께 테스트 케이스의 번호 출력, 공백 문자 후 동시에 연락을 받은 사람 중에 번호가 가장 큰 사람을 출력한다.
 * 
 * -- 연락 인원은 최대 100명이며, 부여될 수 있는 번호는 1이상, 100이하이다.
 * -- 중간에 비어있는 번호가 있을 수 있다.
 * 
 * 1. 테스트 케이스는 10으로 고정한다.
 * 2. 데이터의 길이와 시작점을 입력 받는다.
 * 3. 데이터를 입력 받는다 - from to
 * 4. 그래프를 돌며 마지막에 동시 연락 받은 사람 중에 번호가 가장 큰 사람을 고른다.
 * 	  - 양방향인 경우가 있기 때문에 visited 배열을 생성한다.
 *    - 간선이 많기 때문에 인접 행렬로 구현한다.
 *    - 차수가 가장 큰 숫자들 중에서 가장 큰 숫자를 골라야한다.
 *    	- 해당 차수의 숫자들을 모두 비교해서 가장 큰 수를 저장
 *    	- 새로운 차수로 넘어가면 가장 큰 수 초기화 후 다시 저장
 *    
 * 
 * @author sehyeon.eom
 *
 */

public class SWEA_1238 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int dataLen;		// 데이터의 길이
	static int startPoint;	// 데이터의 시작점
	static int maxDepth;	// 최대 차수
	static int maxNumber;	// 가장 번호가 큰 사람의 번호
	
	static ArrayList<Integer>[] graph;
	static int[] visited;	// 번호의 방문 여부

	static final int TOTAL_CASE = 10;	// 전체 테스트 케이스 개수
	static final int MAX_NUMBER = 100;
	
	/**
	 * 그래프 탐색 메서드
	 * 그래프를 돌며 현재 노드의 숫자와 차수를 넘기며 확인한다.
	 * BFS의 원리
	 * 
	 * @param current 현재 노드의 번호
	 */
	private static void graphSearch(int start) {
		Deque<Integer> que = new ArrayDeque<>();
		que.offer(start);
		visited[start] = 1;
		
		while(!que.isEmpty()) {
			int currentNum = que.poll();
			
			// 현재 번호와 연결된 번호들 탐색
			for(int nextNum: graph[currentNum]) {
				// 이미 연락을 받은 번호라면 패스
				if(visited[nextNum] > 0) continue;
				que.offer(nextNum);
				visited[nextNum] = visited[currentNum] + 1;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 기본 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int tc = 1; tc <= TOTAL_CASE; tc++) {
			// 데이터 입력
			st = new StringTokenizer(br.readLine());
			dataLen = Integer.parseInt(st.nextToken());
			startPoint = Integer.parseInt(st.nextToken());
			
			// 그래프 생성
			graph = new ArrayList[MAX_NUMBER + 1];
			for(int idx = 0; idx <= MAX_NUMBER; idx++)
				graph[idx] = new ArrayList<>();
			
			// 그래프에 데이터 저장
			int from = 0, to = 0;
			st = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < dataLen / 2; idx++) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
			}
			
			// 번호의 연락 유무를 저장하는 배열 초기화
			visited = new int[MAX_NUMBER + 1];
			// 그래프 탐색 시작
			graphSearch(startPoint);
			
			// 탐색이 끝났으니 가장 나중에 연락 받은 큰 번호를 찾는다.
			maxNumber = -1;
			maxDepth = -1;
			for(int idx = 1; idx <= MAX_NUMBER; idx++) {
				// 연락을 돌린 깊이가 현재 최대 깊이와 같다면 더 높은 숫자를 가진 전화번호를 저장
				if(visited[idx] == maxDepth)
					maxNumber = Math.max(maxNumber, idx);
				// 연락을 돌린 깊이가 현재 최대 깊이보다 높다면 높이도 바꾸고 값도 바꾼다.
				else if(visited[idx] > maxDepth) {
					maxDepth = visited[idx];
					maxNumber = idx;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxNumber).append("\n");
		}	// tc end
		
		System.out.println(sb);
	}	// main end
	
}
