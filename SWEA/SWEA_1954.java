package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author sehyeon.eom
 * 
 * 달팽이 숫자 출력
 * 
 * 1. 테스트 케이스의 개수가 입력된다.
 * 2. 그 아래로 각 달팽이의 크기인 snailSize가 주어진다.
 * 
 * deltaIndex로 달팽이의 방향을 정한다.
 * deltaIndex가 4가 되면 다시 0부터
 * snailSize의 제곱수 만큼 진행
 * 끝나면 종료
 */
public class SWEA_1954 {
	static BufferedReader br;		// 입력
	static StringBuilder sb;		// 출력
	static int[][] snailBoard;		// 달팽이판
	static int snailSize;			// 달팽이 사이즈
	static final int number = 1;	// 첫 시작 숫자
	
	// 달팽이를 돌리기 위해 델타 인덱스 사용
	// 우, 하, 좌, 상
	static final int[] dx = {1, 0, -1, 0};	// col
	static final int[] dy = {0, 1, 0, -1};	// row
	static boolean[][] visited;
	
	// 달팽이 실행 메서드
	public static void doSnail() {
		int currentRow = 0;			// 현재 행
		int currentCol = 0;			// 현재 열
		int direction = 0;			// 델타 인덱스의 인덱스
		visited = new boolean[snailSize][snailSize];// 같은 장소 방지용
		for(int snailIdx = 1; snailIdx <= snailSize*snailSize; snailIdx++) {
			
			snailBoard[currentRow][currentCol] = snailIdx;	// snail 판에 숫자 입력
			visited[currentRow][currentCol] = true;			// 장소의 방문 표시
			
			int nextRow = currentRow + dy[direction];	// 다음 행
			int nextCol = currentCol + dx[direction];	// 다음 열
			
			// 방향이 바뀔 조건
			// 다음 행과 열이 보드의 범위 외인지, 그리고 방문한 적이 없는지 확인
			if(nextRow >= snailSize || nextRow < 0 
					|| nextCol >= snailSize || nextCol < 0 
					|| visited[nextRow][nextCol] == true) {
				direction++;	// 해당 조건을 만족하면 다음 방향 지칭
			
				if(direction == 4) 	// 방향이 넘어가면 다시 초기화
					direction = 0;
			}
			
			// 현재 위치 이동
			currentRow += dy[direction];
			currentCol += dx[direction];
		}
	}
	
	// 결과 스트링 빌드에 저장
	public static void appendSnail(int tc) {
		sb.append("#").append(tc).append("\n");
		for(int i = 0; i < snailSize; i++) {
			for(int j = 0; j < snailSize; j++)
				sb.append(snailBoard[i][j]).append(" ");
			sb.append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));	
		sb = new StringBuilder();									
		
		int totalCase = Integer.parseInt(br.readLine().trim());	// 전체 테스트 케이스 입력
		
		// 전체 테스트 케이스만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			snailSize = Integer.parseInt(br.readLine().trim());	// 스네일 사이즈 입력
			snailBoard = new int[snailSize][snailSize];				// 스네일 판 생성
			
			doSnail();
			appendSnail(tc);
			
		}	// tc end
		System.out.println(sb);
	}
}
