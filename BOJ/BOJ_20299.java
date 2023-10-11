package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author MuRing
 * 
 * 1. 첫째 줄에 팀의 수, 팀원 3명의 레이팅 합에 대한 클럽 가입 조건, 개인 레이팅에 대한 클럽 가입 조건이다.
 * 2. 둘째 줄부터 신청한 팀원들의 레이팅 정보를 주어진다.
 * 
 * 첫째 줄에 VIP 클럽에 가입 가능한 팀 수를 입력한다.
 * 둘재 줄에 회원들의 레이팅을 입력받은 순서대로 공백으로 구분해 출력한다.
 * 
 */
public class BOJ_20299 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int teamCount;			// 팀 수
	static int VIPTeamCount;		// VIP 클럽에 가입된 팀원의 수
	static int teamCondition;		// 팀원 합 가입 조건
	static int personalCondition;	// 개인 가입 조건
	static int[] team;				// 각 팀의 점수 입력 배열
	static final int teammateCount = 3;	// 각 팀의 팀원 수
	
	public static void main(String[] args) throws IOException {
		// 시작 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		VIPTeamCount = 0;
		team = new int[teammateCount];
		
		// 첫째 줄 정보 입력
		// 각각 팀 수, 팀의 최소 총점, 개인 최소 총점이 주어진다.
		st = new StringTokenizer(br.readLine());			
		teamCount = Integer.parseInt(st.nextToken());
		teamCondition = Integer.parseInt(st.nextToken());
		personalCondition = Integer.parseInt(st.nextToken());
		
		// 팀원 수 만큼 반복하며 정보를 입력받는다.
		for(int teamIdx = 0; teamIdx < teamCount; teamIdx++) {
			int teamSum = 0;	// 팀의 점수 총 합을 저장하기 위한 변수
			int flag = 0;	// 입력받은 팀원의 개인 점수가 미달일 경우 즉시 반복문 탈출을 위한 변수
			st = new StringTokenizer(br.readLine());
			
			// 각 팀의 팀원 정보를 입력받는다.
			for(int idx = 0; idx < teammateCount; idx++) {
				team[idx] = Integer.parseInt(st.nextToken());
				teamSum += team[idx];
			}
			
			if(teamSum >= teamCondition && team[0] >= personalCondition &&
					team[1] >= personalCondition && team[2] >= personalCondition) {
				// 조건을 모두 통과했다면 결과를 저장하고 통과한 팀원 수를 센다.
				VIPTeamCount++;
				for(int idx = 0; idx < teammateCount; idx++)
					sb.append(team[idx]).append(" ");
			}
				
		}	// for end
		System.out.println(VIPTeamCount);
		System.out.println(sb);
		
	}	// main end
}
