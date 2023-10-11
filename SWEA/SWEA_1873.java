package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 
	문자	의미
	.	평지(전차가 들어갈 수 있다.)
 	*	벽돌로 만들어진 벽
	#	강철로 만들어진 벽
	-	물(전차는 들어갈 수 없다.)
	^	위쪽을 바라보는 전차(아래는 평지이다.)
	v	아래쪽을 바라보는 전차(아래는 평지이다.)
	<	왼쪽을 바라보는 전차(아래는 평지이다.)
	>	오른쪽을 바라보는 전차(아래는 평지이다.)


	문자	동작
	U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
	D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
	L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
	R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
	S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

	전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 당연히 이동하지 않는다.
	전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
	만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
	강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
	게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
	초기 게임 맵의 상태와 사용자가 넣을 입력이 순서대로 주어질 때, 모든 입력을 처리하고 나면 게임 맵의 상태가 어떻게 되는지 구하는 프로그램을 작성하라.
	
	1. 테스트 케이스 수가 주어진다.
	2. 맵의 row, col이 주어진다.
	3. row만큼 길이가 col인 문자열이 주어진다.
	4. 다음 줄에는 사용자가 넣을 입력의 개수 N이 주어진다.
	5. 다음 줄에는 길이가 N인 문자열이 주어진다.
	6. 결과 출력은 #테스트케이스 를 출력하고 한 칸을 띄운 후, 모든 입력을 처리하고 난 후의 맵의 상태를 출력한다.
 *
 */

public class SWEA_1873 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int rowSize;		// 맵의 행 사이즈
	static int colSize;		// 맵의 열 사이즈
	static char map[][];	// 맵 배열
	static char input[];	// 사용자 입력 배열
	
	static int dir;					// 탱크가 바라보는 방향	1:상, 2:하, 3:좌, 4:우
	static int tankRow, tankCol; 	// 탱크의 위치
	static final char tankDir[] = { '^', 'v', '<', '>' };	// 탱크 방향
	static final int dRow[] = { -1, 1, 0, 0 };				// 델타 배열
	static final int dCol[] = { 0, 0, -1, 1 };
	
	// 탱크의 방향을 구하는 메소드
	// 탱크의 방향을 구해 dir 변수에 저장하고 현재 탱크의 위치를 저장한다.
	private static void getDirection(int row, int col) {
		if(map[row][col] == '^') dir = 1;
		else if(map[row][col] == 'v') dir = 2;
		else if(map[row][col] == '<') dir = 3;
		else if(map[row][col] == '>') dir = 4;
		tankRow = row;
		tankCol = col;
	}

	// 탱크의 이동을 담당하는 메소드
	private static void moveTank(int idx) {
		// 다음 행과 열 저장
		int nextRow = tankRow + dRow[idx];
		int nextCol = tankCol + dCol[idx];
		
		dir = idx + 1;	// 이후의 switch 문에서 사용될 탱크의 방향 인덱스 조정
		map[tankRow][tankCol] = tankDir[idx];	// 현재 탱크의 위치에서 탱크가 바라보는 방향 조정
		if(isAvailable(nextRow, nextCol) && map[nextRow][nextCol] == '.') {	// 만약 바라보는 방향이 평지라면
			map[nextRow][nextCol] = map[tankRow][tankCol];	// 탱크를 움직인다.
			map[tankRow][tankCol] = '.';	// 탱크가 이전에 있던 위치는 평지로 바꾼다.
			tankRow = nextRow;	// 탱크의 위치 인덱스 이동
			tankCol = nextCol;
		}
	}
	
	private static void fireTank() {
		// 다음 행과 열 저장
		int nextRow = tankRow;
		int nextCol = tankCol;
		
		while(true) {
			nextRow += dRow[dir - 1];
			nextCol += dCol[dir - 1];
			
			if(!isAvailable(nextRow, nextCol) || map[nextRow][nextCol] == '#') break;
			else if(map[nextRow][nextCol] == '*') {
				map[nextRow][nextCol] = '.';
				break;
			}
		}
	}
	
	// 다음 인덱스가 맵의 범위 안인지 확인하는 메소드
	private static boolean isAvailable(int row, int col) {
		if(row >= 0 && row < rowSize && col >= 0 && col < colSize)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String str;	// 입력받는 정보를 임시 저장할 변수
		
		// 전체 테스트 케이스 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= totalCase; tc++) {
			// 매 테스트 케이스마다 초기화
			map = null;
			input = null;
			
			// 맵의 크기가 주어진다.
			st = new StringTokenizer(br.readLine());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			
			// 맵의 상태가 주어진다.
			map = new char[rowSize][colSize];	// 맵 생성
			for(int row = 0; row < rowSize; row++) {
				str = br.readLine();
				for(int col = 0; col < colSize; col++) {
					map[row][col] = str.charAt(col);
					
					// 맵을 입력받는 도중 탱크를 찾으면 현 탱크의 방향을 확인한다.
					if(map[row][col] == '<' || map[row][col] == '>' || map[row][col] == '^' || map[row][col] == 'v') {
						getDirection(row, col);
					}
				}
			}
			
			// 사용자가 넣을 입력의 개수가 주어진다.
			int inputLen = Integer.parseInt(br.readLine().trim());
			
			// 사용자가 넣을 입력의 개수 만큼 문자열이 주어진다.
			input = new char[inputLen];	// 사용자 입력 저장 배열 생성
			str = br.readLine();
			for(int idx = 0; idx < inputLen; idx++)
				input[idx] = str.charAt(idx);
			
			// 사용자 입력 수행 시작
			for(int idx = 0; idx < inputLen; idx++) {
				switch(input[idx]) {
				// 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
				case 'U':
					moveTank(0);
					break;
					
				// 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
				case 'D':
					moveTank(1);
					break;
					
				// 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
				case 'L':
					moveTank(2);
					break;
					
				// 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
				case 'R':
					moveTank(3);
					break;
					
				// 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
				case 'S':
					fireTank();
					break;
					
				}	// switch end
			}	// for end
			
			// 결과 저장 및 출력
			sb.append("#").append(tc).append(" ");
			for(int row = 0; row < rowSize; row++) {
				for(int col = 0; col < colSize; col++) {
					sb.append(map[row][col]);
				}
				sb.append("\n");
			}
		}	// tc end

		System.out.println(sb);
	}
}
