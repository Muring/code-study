package SWEA;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author SeHyeon.Eom
 * 
 * 1. 판을 저장할 배열을 생성 -> 단순 탐색이기 때문에 배열
 * 2. 첫번째 행을 확인하여 1일 경우 시작하게 함 -> 열의 끝까지 확인
 * 3. 시작하면 계속해서 아래로 내려간다.
 * 4. 내려가면서 좌, 우를 확인하며 좌, 우에 길이 있을 시 빠지며 진행 방향을 바꾼다.
 * 5. 좌, 우로 길을 바꾸면 아래 방향으로 1이 나올 때까지 계속 평행 이동
 * 6. 끝났을 때 2가 있으면 시작 인덱스를 출력
 *
 */

class SWEA_1210
{
	// 전체적으로 사용될 변수 선언
	static int[][] map;		// 전체 맵
	static int answer; 		// 정답을 저장할 변수
	
	// 이동 메서드
	// map[1][col]부터 시작
	public static void move(int row, int col) {
		// 골인 지점부터 역으로 길을 따라간다.
		while(row >= 0) {
			// 좌 확인
			if(col - 1 >= 0 && map[row][col - 1] == 1) {
				while(col - 1 >= 0 && map[row][col - 1] == 1)		// 왼쪽이 맵 범위 내이고 1이라면
					col--;
			}
			
			// 우 확인
			else if(col + 1 < 100 && map[row][col + 1] == 1) {
				while(col + 1 < 100 && map[row][col + 1] == 1)		// 오른쪽이 맵 범위 내이고 1이라면
					col++;
			}
			
			// 도착점 확인
			if(row == 0) 
				answer = col;
			// 내려가기0
			row--;
		}
	}
	
	public static void main(String args[]) throws Exception
	{	
		// 객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력 객체 생성
		StringTokenizer st;
		map = new int[100][100];													// 맵 생성
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			br.readLine().trim();
			// 맵 입력
			for(int row = 0; row < 100; row++) {
				
				st = new StringTokenizer(br.readLine(), " ");		// 토크나이저 생성
				for(int col = 0; col < 100; col++)
					map[row][col] = Integer.parseInt(st.nextToken());
			}
			
			// 게임 시작!
			// 밑에서 역으로 출발점 찾기
			for(int startPoint = 0; startPoint < 100; startPoint++) {
				// 시작점이 아니라면
				if(map[99][startPoint] == 2)
					// 시작점에서 시작
					move(99, startPoint);
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
}