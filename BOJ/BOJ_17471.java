package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BAEKJOON
 * @author sehyeon.eom
 *
 * 1. 선거구의 개수를 입력받는다.
 *    1-1. 선거구의 번호가 1부터 시작하므로 애초에 1개 더 크게 만든다.
 *
 * 2. 각 선서구마다의 인원수를 저장한다.
 * 3. 선거구마다 인접 여부를 저장한다.
 * 4. 부분 집합을 구현한다.
 *    4-1. 1, 2, 3, 4 ... N개의 집합을 만들어 낼 수 있고, 선택된 팀 1개, 선택이 안된팀 1개로 구성이 가능하다.
 *    4-2. 전부 선택되거나, 전부 선택되지 않은 경우는 2개의 팀으로 구성할 수 없다.
 *    4-3. 2개의 팀으로 구성했더라고 인접하지 않으면 안된다.
 *         4-3-1. 각 팀이 인접해있는지 확인하기위해 큐를 이용하여 동일한 팀인 경우 넣어주고 팀 방문 체크 진행.
 *         4-3-2. 각 선거구를 모두 방문하지 않았으면 인접하지 않은 것, 모두 방문했으면 인접한 것.
 * 5. 모두 인접해서 선거구를 이룰 수 있으면 최소를 계산하여 갱신한다.
 */

/*
6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2
 */

public class BOJ_17471 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int areaCount;				// 전체 구역 수
	static int areaPopulationList[];	// 각 구역의 인구 분포도
	static boolean visited[];			// 구역의 방문 여부
	static int adjMatrix[][];			// 인접 행렬
	static int adjCount;				// 해당 구역과 인접한 수 저장 변수
	static int minPopulationDifference;	// 최소 인구수 차이
	static Deque<Integer> que;			// 팀들이 다 연결되어 있는지 확인할 떄 쓰일 큐
	
	
	private static void powerSet(int idx) {
		// 모든 선거구를 살펴보았다면
		if(idx == areaCount) {
			// 선거구가 두 개의 구역으로 나누어지는지 확인해야 한다.
			// 부분집합을 구하면서 visited가 체크가 되어 있을 것이다.
			// visited의 true와 false의 여부로 구역을 나눌 수 있다.
			int redTeam = -1;	// true
			int blueTeam = -1;	// false
			for(int areaIdx = 1; areaIdx < areaCount; areaIdx++) {
				
				if(visited[areaIdx] && redTeam == -1)
					redTeam = areaIdx;
				
				else if(!visited[areaIdx] && blueTeam == -1)
					blueTeam = areaIdx;
			}
			
			// 만약에 팀 값이 전부 -1이라면 팀을 만들지 못한 것이니 제외
			if(redTeam == -1 || blueTeam == -1)
				return;
			
			// 팀이 서로 인접해있는지 확인한다.
			if(isAdjacency(redTeam, blueTeam)) {
				
				// 제대로 구역이 나누어졌다면 각 선거구의 인원을 계산한다.
				int redTeamCount = 0;
				int blueTeamCount = 0;
				
				// true가 레드팀, false가 블루팀이다.
				for(int areaIdx = 1; areaIdx < areaCount; areaIdx++){
					if(visited[areaIdx]) {
						redTeamCount += areaPopulationList[areaIdx];
						continue;
					}
					blueTeamCount += areaPopulationList[areaIdx];
				}
				
				// 최소 값 계산
				minPopulationDifference = Math.min(minPopulationDifference, Math.abs(redTeamCount - blueTeamCount));
			}
			return;	// 까먹지 말자
		}
		
		// 부분 집합 처리
		visited[idx] = true;
		powerSet(idx + 1);
		
		visited[idx] = false;
		powerSet(idx + 1);
	}
	
	// 각 팀이 서로 제대로 인접해 있는지 확인하는 메소드
	private static boolean isAdjacency(int redTeam, int blueTeam) {
		
		boolean[] teamVisited = new boolean[areaCount];
		
		isTeamConnected(redTeam, teamVisited, true);	// 레트팀은 true
		isTeamConnected(blueTeam, teamVisited, false);	// 블루팀은 false
		
		// 모든 구역을 방문하지 못했으면 인접하지 못한 것이다.
		for(int idx = 1; idx < areaCount; idx++) {
			if(!teamVisited[idx])
				return false;
		}
		
		// 조건을 모두 통과했으면 팀은 완성된 것이다.
		return true;
	}
	
	// 헷갈리는 점!
	// teamVisited와 visited는 다르다.
	// teamVisited는  현재 메소드에서 팀이 인접해 있는지 확인할 때 방문했는지 확인하는 용으로 사용하는 visited 배열이고
	// 그냥 visited는 해당 메소드 밖에서 팀원을 구분하기 위해 사용하는 visited 배열이다.
	private static void isTeamConnected(int team, boolean[] teamVisited, boolean teamSide) {
		que = new ArrayDeque<>();
		
		// 시작 큐
		que.offer(team);
		
		// 방문 처리
		teamVisited[team] = true;	// 이건 현재 메소드에서 팀이 인접해 있는지 확인할 때 사용하는 visited
		
		// BFS시작
		while(!que.isEmpty()) {
			int currentTeam = que.poll();
			
			// 다음 구역을 확인
			for(int nextTeamIdx = 1; nextTeamIdx < areaCount; nextTeamIdx++) {
				// 다른 팀이면 패스
				if(visited[nextTeamIdx] != teamSide)
					continue;
				
				// 이미 방문했으면 패스
				if(teamVisited[nextTeamIdx])
					continue;
				
				// 인접하지 않은 곳이면 패스
				if(adjMatrix[currentTeam][nextTeamIdx] == 0)
					continue;
				
				// 다 통과했으면 인접해 있는 것으로 같은 팀이다.
				teamVisited[nextTeamIdx] = true;
				que.offer(nextTeamIdx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 전체 구역 수 입력
		// 선거구의 번호가 1부터 시작하므로 애초에 1개 더 크게 만든다.
		areaCount = Integer.parseInt(br.readLine()) + 1;
		areaPopulationList = new int[areaCount];
		visited = new boolean[areaCount];
		
		// 각 선서구마다의 인원수를 저장한다.
		st = new StringTokenizer(br.readLine());
		for(int idx = 1; idx < areaCount; idx++) {
			areaPopulationList[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 선거구마다 인접 여부를 저장한다.
		adjMatrix = new int[areaCount][areaCount];
		for(int areaIdx = 1; areaIdx < areaCount; areaIdx++) {
			st = new StringTokenizer(br.readLine());
			// 몇 개의 구역과 인접해 있는지 입력
			adjCount = Integer.parseInt(st.nextToken());
			
			// 인접해 있는 구역 입력
			for(int adjIdx = 0; adjIdx < adjCount; adjIdx++) {
				adjMatrix[areaIdx][Integer.parseInt(st.nextToken())] = 1;
			}
		}
		
		// 최소 인원 차이를 구하기 위해 초기화
		minPopulationDifference = Integer.MAX_VALUE;
		
		// 부분 집합을 구현한다.
		powerSet(1);
		
		// 모든 과정을 다 끝내고도 최소 인원이 여전히 MAX_VALUE이면 선거 구역을 만들 수 없다는 의미이므로 -1
		// 그렇지 않으면 차이를 출력
		System.out.println(minPopulationDifference == Integer.MAX_VALUE ? -1 : minPopulationDifference);
		
		
	}	// main end
	
}
