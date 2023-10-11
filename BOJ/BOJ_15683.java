package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 감시
 * 감시카메라의 최소 사각지대를 구하는 프로그램이다.
 * 1번 카메라는 한 쪽 방향만 감시 가능
 * 2번 카메라는 일직선 전체 감시 가능
 * 3번 카메라는 직각으로 두 방향 감시 가능
 * 4번 카메라는 ㅗ모양으로 3방향 감시 가능
 * 5번 카메라는 사방향 전체 감시 가능
 * 6은 벽이다. 벽 너머는 감시 불가능하다.
 * 
 * 1. 첫째 줄에 사무실의 세로크기와 가로 크기가 주어진다.
 * 2. 둘째 줄부터 사무실 각 칸의 정보가 주어진다.
 * 3. CCTV의 개수는 8개를 넘지 않는다.
 * 4. 사각지대 최소 크기를 출력한다.
 * 
 *   교수님의 코드를 매우 많이 참조했다.
 *   다른 것보다도 카메라의 방향 설정을 위한 델타 배열을 설정하는 것에 애를 많이 먹은 것 같다.
 * 	  어렵다 정말..
 * 
 * @author sehyeon.eom
 *
 */


public class BOJ_15683 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;		// 맵의 크기
	static int map[][];					// 맵
	static List<CCTV> cctvList;			// ccvt의 목록을 저장할 리스트
	
	// toggle 및 상수 지정
	static final int WATCHED = -1;		// 카메라의 감시 구역임을 확인할 때 사용
    static final int UN_WATCHED = 1;	// 감시 해제(복구)할 떄 사용
    static final int BLANK = 0;			// 빈 칸임을 표시
    static final int WALL = 6;			// 벽임을 표시
	
	static int initBlindAreaCount, minBlindAreaCount;	// 최초 빈 칸 수, 현재까지의 최소 빈 칸 수
	
	// 방향은 상 하 좌 우
	static final int[] dRow = { -1, 1, 0, 0 };
	static final int[] dCol = { 0, 0, -1, 1 };
	
	// 3중 배열로 해야한다.
	// 왜냐하면 방향을 여러가지 가지고 있는 경우가 있기 때문에 단순히 이중 배열로하면 해결 할 수 없다.
	static final int[][][] cctvWatchDirectionList = {
            {}, // 0번
            {
                    {0}, {1}, {2}, {3} // 1번
            },
            {
                    {0, 1}, {2, 3} // 2번
            },
            {
                    {0, 2}, {2, 1}, {3, 1}, {0, 3} // 3번
            },
            {
                    {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3} // 4번
            },
            {
                    {0, 1, 2, 3} // 5번
            }
    };
	
	// 각 cctv의 정보를 저장할 클래스
	public static class CCTV {
		int row;	// cctv 행 위치
		int col;	// cctv 열 위치
		int num;	// cctv 번호
		
		public CCTV(int row, int col, int num) {
			this.row = row;
			this.col = col;
			this.num = num;
		}
	}
	
	// cctv의 감시 구역 표시 메소드
	// 각각 현재 cctv 자체, 델타 배열(카메라의 방향), 어떤 기능을 수행할지인 toggle변수가 주어진다.
	// toggle 변수는 코드의 중복성을 줄이기 위해 같은 메소드로 다른 역할을 수행할 수 있도록 하기 위해서 사용한다.
	private static int watch(CCTV cctv, int[] directionList, int toggle) {
		
		int watchCount = 0;	// 감시된 칸의 수를 저장할 변수
		
		for(int dir = 0; dir < directionList.length; dir++) {
			for(int times = 1; ; times++) {	// 범위를 지정해 줄 필요가 없다. 무한으로 돌리면서 조건 만족시 break
				// 다음 감시 칸을 위한 변수
				int nextRow = cctv.row + dRow[directionList[dir]] * times;
				int nextCol = cctv.col + dCol[directionList[dir]] * times;
				
				// 맵 범위를 벗어난 경우 제외
				if(nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize)
					break;
				
				// 벽인 경우 제외
				if(map[nextRow][nextCol] == WALL)
					break;
				
				// 카메라일 경우 한 칸 건너뛴다.
				if(map[nextRow][nextCol] > BLANK)	// 카메라는 1~5인 값이기 때문에 빈 칸인 0보다 항상 크다.
					continue;
				
				// 빈 칸이면 감시되는 영역으로 바꾼다.
				if(map[nextRow][nextCol] == BLANK)
					watchCount++;
				
				// 여러대의 카메라가 동시에 감시하는 곳일 경우
				// 토글로 인해 감시되는 경우일 경우 -1씩 추가된다.
				// 이는 이후 복구할 때 +1씩 되어 0으로 복구될 것이다.
				map[nextRow][nextCol] += toggle;
			}
		}
		return watchCount;
	}	// watch method end
	
	
	// cctv를 세팅하는 메소드
	private static void setWatchArea(int currentCCTVIdx, int currentWatchAreaCount) {
		// 기저 조건
		// 모든 카메라의 세팅이 완료되었을 경우 == 현재 세팅된 카메라 수 = 리스트에 저장된 전체 카메라 수
		if(currentCCTVIdx == cctvList.size()) {
			// 사각지대 계산
			minBlindAreaCount = Math.min(minBlindAreaCount, initBlindAreaCount - currentWatchAreaCount);
			return;
		}
		
		// 반복 실행
		// 현재 카메라 정보를 가져온다.
		CCTV currentCCTV = cctvList.get(currentCCTVIdx);	// 메소드에서 받아온 현재 세팅해야하는 카메라의 인덱스로 리스트에서 받아온다.
		
		int watchAreaCount = 0;	// 해당 카메라가 이번 수행으로 감시하는 칸 수를 저장할 변수
		// 카메라를 주어진 방향으로 돌려본다.
		// 해당 CCTV의 번호에 해당하는 델타 배열의 개수만큼 반복문을 수행한다. ex) 1번 카메라는 4번, 2번 카메라는 2번 ...
		for(int dir = 0; dir < cctvWatchDirectionList[currentCCTV.num].length; dir++) {
			// 해당 카메라로 감시한 지역을 표시하고 
			watchAreaCount = watch(currentCCTV, cctvWatchDirectionList[currentCCTV.num][dir], WATCHED);
			// 다음 카메라로 넘어간다.
			// 이때 다음 카메라를 확인해야하기 때문에 카메라 인덱스를 1 올려주고, 현재까지 감시된 구역의 수를 더해 넘겨준다.
			setWatchArea(currentCCTVIdx + 1, currentWatchAreaCount + watchAreaCount);
			
			// 수행이 끝나고 나오게되면 맵을 복구해야한다.
			watch(currentCCTV, cctvWatchDirectionList[currentCCTV.num][dir], UN_WATCHED);
		}
		
	}	// setWatchCamera method end
	
	
	public static void main(String[] args) throws IOException {
		// 메인 시작 시 최초 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		cctvList = new ArrayList<>();
		
		// 첫 번째 줄 입력 - 맵의 크기
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 입력 받은 크기를 토대로 map 생성
		map = new int[rowSize][colSize];
		
		// 맵에 정보 입력, 빈칸 세기, 카메라 정보 저장
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				// 먼저 비어있는 칸 수를 모두 세어 저장한다.
				if(map[row][col] == BLANK)
					initBlindAreaCount++;
				// 비어있지 않고 벽도 아니라면 카메라이다. -> 카메라륵 카메라 리스트에 저장
				else if(map[row][col] != WALL)
					cctvList.add(new CCTV(row, col, map[row][col]));
			}
		}	// for end
		
		// 최초 사각지대 개수가 현재 최소 사각지대 개수이다.
		minBlindAreaCount = initBlindAreaCount;
		
		// 카메라 세팅
		setWatchArea(0, 0);
		
		// 결과 출력
		System.out.println(minBlindAreaCount);
		
	}	// main end
}
