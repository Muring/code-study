package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * mapRow x mapCol인 배열이 있을 때, 배열을 돌린다. 배열은 반시계 방향으로 돌린다.
 * A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
 * ↓                                       ↑
 * A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
 * ↓         ↓                   ↑         ↑
 * A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
 * ↓                                       ↑
 * A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]
 * 
 * 배열과 정수 movement가 주어졌을 때 movement번 회전시킨 결과를 출력하자.
 * 
 * @author sehyeon.eom
 *
 */
public class BOJ_16926 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;			// 전체 맵
	static int mapRow;			// 전체 맵의 행 길이
	static int mapCol;			// 전체 맵의 열 길이
	static int movement;		// 움직이는 총 횟수
	static int lines;			// 회전하는 줄 개수
	
	// 맵의 데이터를 옮기는 방향 좌 상 우 하
	// 하지만 다음 데이터를 지정하는 방향은 그의 반대
	// ex) 데이터가 움직이는 방향이 왼쪽이면 오른쪽 값을 참조해서 왼쪽으로 옮겨야 하므로 nextCol은 + 1을 해야한다.
	//     즉, dy가 1이어야 한다.
	static int[] dx = { 1, 0, -1, 0 };	// 열
	static int[] dy = { 0, 1, 0, -1 };	// 행
	
	// 회전시키는 함수
	public static void rotate() {
		// 회전 시작
		// 회전할 횟수만큼 반복
		for(int rotateIdx = 0; rotateIdx < movement; rotateIdx++) {
			// 회전하는 줄의 개수만큼 반복 -> 외부 줄과 내부 줄 한줄씩 있다면 총 두 번 반복한다.
			for(int line = 0; line < lines; line++) {
				int temp = map[line][line];		// 옮기기 전 가장 처음 자리를 temp에 저장
				
				// 각 회전하는 줄에서의 시작 지점 저장 
				// -> 첫번째 회전하는 줄은 (0, 0), 두번째 회전하는 줄은 (1, 1), 세번째 회전하는 줄은 (2, 2)
				int startRow = line;	// 처음 회전 시작 행
				int startCol = line;	// 처음 회전 시작 열
				
				// 각 방향만큼 반복
				int idx = 0; 	// dx dy의 인덱스
				while(idx < 4) {
					int nextRow = startRow + dy[idx];	// 다음 행의 좌표 값
					int nextCol = startCol + dx[idx];	// 다음 열의 좌표 값
					
					// 맵의 범위 내에 있을 경우
					if(nextRow >= line && nextRow < (mapRow - line) 
							&& nextCol >= line && nextCol < (mapCol - line)) {
						// 해당 위치의 값을 start지점으로 옮긴다.
						map[startRow][startCol] = map[nextRow][nextCol];
						
						// 이후 시작 지점을 다음 칸으로 옮긴다.
						startRow = nextRow;	
						startCol = nextCol;
					}
					// 맵의 범위를 벗어난 경우
					else {
						idx++;	// 다음 델타 인덱스 지정
					}
				}
				map[line + 1][line] = temp;
			}
		}
	}
	
	// 맵을 입력받는 메소드
	public static void installMap() throws IOException {
		map = new int[mapRow][mapCol];	// 맵 생성
		for(int row = 0; row < mapRow; row++) {	
			st = new StringTokenizer(br.readLine());	// 각 줄의 데이터를 받아온다.
			for(int col = 0; col < mapCol; col++)
				map[row][col] = Integer.parseInt(st.nextToken());	// 받아온 데이터를 개행문자로 쪼개 각각 저장
		}
	}
	
	// 결과 출력 메서드
	public static void printResult() {
		// 스트링 빌더에 결과를 순차적으로 저장 -> 일일히 sysout을 사용하면 너무 많은 시간을 잡아먹음
		for(int row = 0; row < mapRow; row++) {
			for(int col = 0; col < mapCol; col++)
				sb.append(map[row][col]).append(" ");
			sb.append("\n");
		}
		// 저장된 결과 출력
		System.out.println(sb);
	}
	
	// 메인 메서드
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 데이터 입력
		st = new StringTokenizer(br.readLine());
		mapRow = Integer.parseInt(st.nextToken());			// 행 입력
		mapCol = Integer.parseInt(st.nextToken());			// 열 입력
		movement = Integer.parseInt(st.nextToken());		// 움직이는 횟수
		lines = Math.min(mapRow, mapCol) / 2;				// 회전하는 줄 개수
		
		// 맵 입력
		installMap();
		
		// 회전 시작
		rotate();
		
		// 회전이 끝난 결과 출력
		printResult();
	}
}
