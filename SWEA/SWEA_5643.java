package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 키 순서
 * 
 * 1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다.
 * 단, N명의 학생들의 키는 모두 다르다고 가정한다.
 * a번 학생의 키가 b번 학생의 키보다 작다면, a에서 b로 화살표를 그려서 표현하였다.
 * 학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램을 작성하시오.
 * 
 * [입력]
 * 첫 줄에 테스트케이스의 개수 T가 주어진다. (1 ≤ T ≤ 15)
 * 각 테스트 케이스의 첫 번째 줄에 학생들의 수 N이 주어진다. (2 ≤ N ≤ 500)
 * 각 테스트 케이스의 두 번째 줄에 두 학생의 키를 비교한 횟수 M이 주어진다. (0 ≤ M ≤ N*(N-1)/2)
 * 각 테스트 케이스의 세 번째 줄부터 M개의 줄에 걸쳐 두 학생의 키를 비교한 결과를 나타내는 두 양의 정수 a, b가 주어진다.
 * 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다. 이 때, 입력은 항상 모순이 없도록 주어진다.
 * 
 * [출력]
 * 각 테스트케이스마다 한 줄에 걸쳐, 테스트케이스 수 “#(TC) “를 출력하고,
 * 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.
 *
 * 그래프로 푸는 문제이다.
 * 단방향 그래프로 서로의 순위를 알 수 있다.
 * 이때, 나보다 작은 사람 + 나보다 큰 사람 = 전체 학생 수 -1이면 본인의 순위가 특정이 되므로 정답 수를 늘려주면 된다.
 */

public class SWEA_5643 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int totalStudent;
	static int compareCnt;
	static int[][] map;
	static int shorterCnt, tallerCnt;
	static int answer;
	

	private static void shorter(int tallerStudent, boolean[] visited) {
		// 해당 학생의 방문 처리
		visited[tallerStudent] = true;
		for(int idx = 1; idx < totalStudent + 1; idx++) {
			// 만약 해당 학생이 방문되지 않았고 자신보다 작은 학생이 있다면
			// 방문되지 않았는지 체크하는 이유는 방문이 되어있다면 해당 학생을 방문한 학생과의 우열을 가릴 수 없기 때문이다.
			if(!visited[idx] && map[idx][tallerStudent] == 1) {
				shorter(idx, visited);
				shorterCnt++;
			}
		}
		
	}
	
	private static void taller(int shorterStudent, boolean[] visited) {
		visited[shorterStudent] = true;
		for(int idx = 1; idx < totalStudent + 1; idx++) {
			// 만약 해당 학생이 방문되지 않았고 자신보다 큰 학생이 있다면
			if(!visited[idx] && map[shorterStudent][idx] == 1) {
				taller(idx, visited);
				tallerCnt++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			
			answer = 0;	// 매번 초기화
			
			totalStudent = Integer.parseInt(br.readLine().trim());
			compareCnt = Integer.parseInt(br.readLine().trim());
			map = new int[totalStudent + 1][totalStudent + 1]; // 학생의 번호는 1번부터이므로 +1을 해준다.
			for(int idx = 0; idx < compareCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int shorter = Integer.parseInt(st.nextToken());
				int taller = Integer.parseInt(st.nextToken());
				map[shorter][taller] = 1;
			}
			
			// 나보다 작은 사람 및 큰 사람 수를 센다.
			for(int studentIdx = 1; studentIdx < totalStudent + 1; studentIdx++) {
				shorterCnt = 0;
				tallerCnt = 0;
				
				// 나보다 작은 사람 수 구하기
				shorter(studentIdx, new boolean[totalStudent + 1]);
				
				// 나보다 큰 사람 수 구하기
				taller(studentIdx, new boolean[totalStudent + 1]);
				
				// 순위가 특정지어질 수 있다면
				if(shorterCnt + tallerCnt == totalStudent - 1)
					answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}	// tc end
		System.out.println(sb);
	}	// main end
}
