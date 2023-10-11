package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 월드컵
 * 
 * 
 *
 */
public class BOJ_6987 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int gameInfo[][];		// 각 경기의 결과 저장 배열 -> 행은 나라, 열은 승, 무, 패
	static final int teamCount = 6;		// 전체 팀 개수
	static final int infoCount = 3;		// 승무패
	static final int preGameCount = 4;	// 전체 예선전 횟수
	static int score[][];	
	
	// 어차피 한정된 팀이고 팀의 비교 순서가 정해져 있기 때문에 비교하는 순서대로 인덱스를 미리 저장해놓고 꺼내쓰면
	// 간단하게 데이터에 접근하는 것이 가능하다
	static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	
	private static boolean checkGame(int gameIdx) {
		// 기저 조건
		// 게임 수가 15개에 도달하면 종료
		// 15개보다 적거나 많으면 어차피 틀린 것
		if(gameIdx == 15)
			return true;
		
		// 홈 팀이 이기는 경우
		if(gameInfo[home[gameIdx]][0] > 0 && gameInfo[away[gameIdx]][2] > 0) {
			gameInfo[home[gameIdx]][0]--;
			gameInfo[away[gameIdx]][2]--;
			if(checkGame(gameIdx + 1)) return true;		// true라는 말은 게임을 모두 확인했다는 의미이므로 true 반환
			gameInfo[home[gameIdx]][0]++;
			gameInfo[away[gameIdx]][2]++;
		}
		
		// 어웨이 팀이 이기는 경우
		if(gameInfo[home[gameIdx]][2] > 0 && gameInfo[away[gameIdx]][0] > 0) {
			gameInfo[home[gameIdx]][2]--;
			gameInfo[away[gameIdx]][0]--;
			if(checkGame(gameIdx + 1)) return true;		// true라는 말은 게임을 모두 확인했다는 의미이므로 true 반환
			gameInfo[home[gameIdx]][2]++;
			gameInfo[away[gameIdx]][0]++;
		}
		
		// 비기는 경우
		if(gameInfo[home[gameIdx]][1] > 0 && gameInfo[away[gameIdx]][1] > 0) {
			gameInfo[home[gameIdx]][1]--;
			gameInfo[away[gameIdx]][1]--;
			if(checkGame(gameIdx + 1)) return true;		// true라는 말은 게임을 모두 확인했다는 의미이므로 true 반환
			gameInfo[home[gameIdx]][1]++;
			gameInfo[away[gameIdx]][1]++;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		gameInfo = new int[teamCount][infoCount];	// 6팀, 승무패 3개의 정보
		
		// 게임의 정보 저장
		for(int gameIdx = 0; gameIdx < preGameCount; gameIdx++) {
			st = new StringTokenizer(br.readLine());
			int total = 0;	// 전체 게임 수 세는 변수
			
			// 각 팀별 경기 성적 저장
			for(int teamIdx = 0; teamIdx < teamCount; teamIdx++) {
				for(int infoIdx = 0; infoIdx < infoCount; infoIdx++) {
					total += gameInfo[teamIdx][infoIdx] = Integer.parseInt(st.nextToken());
				}
			}	// 정보 입력 end
			
			// 각 예선전의 가능 여부 확인
			if(total > 30) {
				sb.append("0").append(" ");
				continue;
			}
			// 예선전이 가능한 경우
			if(checkGame(0))
				sb.append("1").append(" ");
			else
				sb.append("0").append(" ");
			
		}	// 게임 가능 여부 end
		System.out.println(sb);
	}
}
