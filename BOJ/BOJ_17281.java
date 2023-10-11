package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 야구
 * 
 * 9명으로 이루어진 두 팀이 공겨과 수비를 번걸아가며 하는 게임이다.
 * 하나의 이닝은 공격과 수비로 이루어져있고, 총 N이닝동안 게임 진행
 * 한 이닝에 3아웃이 발생하면 이닝 종료, 두 팀이 서로 공수를 바꾼다.
 * 
 * 두 팀은 경기 시작 전 타순을 정해야 하고, 경기 중에는 변경 불가
 * 9번 타자까지 공을 쳤는데 3아웃이 발생하지 않으면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다.
 * 타순은 언제나 순서를 유지해야 한다. 
 * - 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번 타자부터 타석에 선다.
 * 
 * 공격은  투수가 던진 공을 타석에 있는 타자가 치는 것이다. 
 * 공격 팀의 선수가 1루, 2루, 3루를 거쳐서 홈에 도착하면 1점을 득점한다. 
 * 타자가 홈에 도착하지 못하고 1루, 2루, 3루 중 하나에 머물러있을 수 있다. 
 * 루에 있는 선수를 주자라고 한다. 이닝이 시작될 때는 주자는 없다.
 * 
 * 타자가 공을 쳐서 얻을 수 있는 결과는 안타, 2루타, 3루타, 홈런, 아웃 중 하나이다.
 * 
 * 한 야구팀의 감독 아인타는 타순을 정하려고 한다. 
 * 아인타 팀의 선수는 총 9명이 있고, 1번부터 9번까지 번호가 매겨져 있다. 
 * 아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정했다. 
 * 이제 다른 선수의 타순을 모두 결정해야 한다. 
 * 아인타는 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다. 
 * 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자.
 * 
 * [ 입력 ]
 * 첫째 줄에 이닝 수 N(2 ≤ N ≤ 50)이 주어진다. 
 * 둘째 줄부터 N개의 줄에는 각 선수가 각 이닝에서 얻는 결과가 1번 이닝부터 N번 이닝까지 순서대로 주어진다. 이닝에서 얻는 결과는 9개의 정수가 공백으로 구분되어져 있다. 
 * 각 결과가 의미하는 정수는 다음과 같다.
 * 안타: 1
 * 2루타: 2
 * 3루타: 3
 * 홈런: 4
 * 아웃: 0
 * 각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.
 * 
 * [ 출력 ]
 * 아인타팀이 얻을 수 있는 최대 점수를 출력한다.
 * 
 * [ 풀이 ]
 * 1. 이닝 수를 입력받는다.
 * 2. 각 이닝마다의 각 선수들의 결과 값을 순서대로 주어진다.
 * 3. 각 이닝을 수행
 * 	  - 1번 선수를 4번째 타순으로 고정한다.
 *    - 나머지 선수들의 타순을 고정한다.
 *    - 이후 선수들의 타순을 바탕으로 이닝을 수행한다.
 * 	    - 아웃이 3회가 아닐때 계속 반복한다.
 * 	    - 안타, 2루타, 3루타, 홈런 처리를 한다.
 * 	  - 해당 이닝이 끝나도 타순 인덱스는 유지하여 다음 이닝때도 사용한다.
 * 
 * @author SSAFY
 *
2
0 4 4 4 4 4 4 4 4
0 4 4 4 4 4 4 4 4
-> 43
 */

public class BOJ_17281 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int inningCount;		// 전체 이닝 수
	static int[] playerResult;	// 각 번호의 타자가 공을 친 결과
	static List<Integer> playerOrder;	// 순열로 구한 선수들의 타순
	static boolean[] visited;	// 순열에서 쓰일 배열
	static int maxScore;
	static int currentScore;
	static int inningPlayerIdx;	// 이닝 수행 중 플레이어 인덱스
		
	static final int TOTAL_PLAYER = 9;	// 전체 플레이어 수
	static final int FIXED_PLAYER_INDEX = 1;	// 고정된 선수 번호
	static final int FIXED_ORDER = 4;	// 고정된 타순 번
	static final int MAX_OUT_COUNT = 3;	// 최대 아웃 수
	static final int BASE_COUNT = 3;	// 전체 베이스 수
	
	// 게임 시작 전 선수들의 타순을 구하는 메소드 -> 순열
	private static void beforeGame(int count) {
		// 선수들의 타순이 정해졌을 때 게임을 시작한다.
		if(count == TOTAL_PLAYER - 1) {
			playerOrder.add(3, 0);
			System.out.println(playerOrder.toString());
			gameStart();
			playerOrder.remove(3);
			return;
		}
		
		for(int idx = 1; idx < TOTAL_PLAYER; idx++) {
			if(visited[idx] != true) {
				visited[idx] = true;
				playerOrder.add(idx);
				beforeGame(count + 1);
				visited[idx] = false;
				playerOrder.remove(count);
			}
		}
	}
	
	private static void gameStart() {
		// 선수들의 타순이 playerOrder에 저장되어 있다.
		// playerOrder의 값을 처음부터 불러오는 것이 각 선수들이 게임을 진행하는 것
		int outCount = 0;
		currentScore = 0;
		while(outCount < 3) {
			// 이닝이 끝나지 않았는데 마지막 순번의 선수까지 수행했다면 다시 처음으로 돌아간다.
			if(inningPlayerIdx == 9) {
				inningPlayerIdx = 0;
			}
			// hit은 현재 순번의 타자가 친 결과이다.
			int hit = playerResult[playerOrder.get(inningPlayerIdx++)];
			// 쳤으면 달린다.
			outCount = run(hit, outCount);
		}
	}
	
	// 타자가 공을 친 후 진루하는 메소드
	private static int run(int hit, int outCount) {
		// 현재 베이스의 상태
		int base = 0;	
		
		// 아웃일 경우
		if(hit == 0) {
			return outCount + 1;
		}
		// 진루해야하는 경우 진루한다.
		for(int idx = 0; idx < hit; idx++) {
			// 한칸 씩 진루
			base <<= 1;
			
			// 처음 진루 시 해당 선수를 넣는다.
			if(idx == 0)
				base |= 1;
			
			// 선수가 홈에 들어오면 점수를 추가.
			if(base / 8 == 1) {
				currentScore++;
				base %= 8;
			}

		}
		return outCount + 1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 이닝 수를 입력받는다.
		inningCount = Integer.parseInt(br.readLine().trim());
		
		// 각 이닝마다의 각 선수들의 결과 값을 순서대로 주어진다.
		playerResult = new int[TOTAL_PLAYER];
		for(int inningIdx = 0; inningIdx < inningCount; inningIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int playerIdx = 0; playerIdx < TOTAL_PLAYER; playerIdx++) {
				playerResult[playerIdx] = Integer.parseInt(st.nextToken());
			}
			
			// 각 이닝을 수행
			visited = new boolean[TOTAL_PLAYER];
			playerOrder = new ArrayList<>();
			inningPlayerIdx = 0;
			beforeGame(0);
			
			
		}
		
		maxScore = Math.max(maxScore, currentScore);
		
		System.out.println("정답: " + maxScore);
		
	}
}
