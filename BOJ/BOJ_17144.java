package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 미세먼지 안녕!
 * 
 * 공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지한다. 
 * 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c이다.
 * 1초 동안 아래 적힌 일이 순서대로 일어난다.
 * ---------------------------------------------------------------
 * 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * 확산되는 양은 Ar,c/5이고 소수점은 버린다.
 * (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
 * 
 * 공기청정기가 작동한다.
 * 공기청정기에서는 바람이 나온다.
 * 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
 * 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * ---------------------------------------------------------------
 * 
 * [입력]
 * 첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.
 * 둘째 줄부터 R개의 줄에 Ar,c (-1 ≤ Ar,c ≤ 1,000)가 주어진다. 
 * 공기청정기가 설치된 곳은 Ar,c가 -1이고, 나머지 값은 미세먼지의 양이다. 
 * -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
 * 
 * [출력]
 * 첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
 * 
 * [풀이]
 * 1. 맵의 크기와 초 수를 입력받는다.
 * 2. 맵의 정보가 주어진다.
 * 3. 매 초마다 먼지를 확산시키고, 공기청정기를 작동시킨다.
 * 4. 방에 남아있는 미세먼지 양을 출력
 * 
 */

public class BOJ_17144 {
	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize, timeLen;
	static int[][] originMap, copyMap;
	static int leftDust;
	static List<Integer> directionList;
	static List<Point> cleaner;
	
	// 상 우 하 좌
	static final int[] D_ROW = { -1, 0, 1, 0 };
	static final int[] D_COL = { 0, 1, 0, -1 };
	static final int AIR_CLEANER = -1;
	
	static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		timeLen = Integer.parseInt(st.nextToken());
		
		// 맵 생성
		originMap = new int[rowSize][colSize];
		cleaner = new ArrayList<>();
		// 맵 정보 입력
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				originMap[row][col] = Integer.parseInt(st.nextToken());
				// 공기 청정기 위치 저장
				// 첫번째 저장되는 청정기가 위쪽, 두번째가 아래쪽이다.
				if(originMap[row][col] == AIR_CLEANER) {
					cleaner.add(new Point(row, col));
				}
			}
		}
	}
	
	// 매초마다 수행되게하는 메소드
	private static void timeFlow() {
		for(int timeIdx = 0; timeIdx < timeLen; timeIdx++) {
			// 먼지 퍼트리기
			spreadDust();
			// 공기 청정기 작동
			activateAirCleaner();
			// 이후 originMap의 정보를 처리가 끝난 copyMap으로 바꾼다.
			updateMap();
		}		
		
		// 최종적으로 저장된 먼지 값을 저장
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				if(originMap[row][col] > 0)
					leftDust += originMap[row][col];
			}
		}
	}
	
	/**
	 * 각 시간마다 먼지가 먼저 퍼지고 공기청정기의 작동이 이루어져야한다.
	 * 먼지를 퍼트리기 위해서는 맵 전체를 탐색하며 각 먼지를 찾을때 마다 퍼트릴 수 있는 공간만큼 퍼트린 값을 copyMap에 저장한다.
	 * OriginMap을 안건드리는 이유는 동시에 먼지가 퍼진 처리를 하기 위해서는 원본 먼지의 값을 유지할 필요가 있기 떄문이다.
	 * 먼지를 퍼트리는 것은 deltaIdx로 한칸씩 퍼트리면 된다.
	 */
	private static void spreadDust() {
		// 해당 칸에 남는 먼지는 originMap[row][col] - (originMap[row][col] / 5) * 확산된 방향의 개수
		// originMap의 값으로 그 칸에 남는 먼지를 copyMap에 저장
		// 이후 spreadAmount에 저장된 값을 copyMap에 가능한 곳에 뿌린다.
		
		int leftAmount = 0;		// 그 자리에 남는 먼지 양
		int spreadAmount = 0;	// 퍼질 먼지의 양
		int countDir = 0;
		int nextRow = 0, nextCol = 0;

		copyMap = new int[rowSize][colSize];
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				if(originMap[row][col] > 0) {
					// 해당 지점에서 퍼질 수 있는 방향의 개수를 구한다.
					getDirection(row, col);
					countDir = directionList.size();
					// 남을 먼지의 양과 퍼질 먼지의 양을 구한다.
					leftAmount = originMap[row][col] - (originMap[row][col] / 5) * countDir;
					spreadAmount = (originMap[row][col] - leftAmount) / countDir;
					// 구한 값들을 copyMap에 저장한다.
					copyMap[row][col] += leftAmount;
					for(int idx = 0; idx < countDir; idx++) {
						nextRow = row + D_ROW[directionList.get(idx)];
						nextCol = col + D_COL[directionList.get(idx)];
						if(isAvailable(nextRow, nextCol, 2))
							copyMap[nextRow][nextCol] += spreadAmount;
					}
				}
			}
		}
	}
	
	private static void getDirection(int row, int col) {
		int nextRow = 0;
		int nextCol = 0;
		directionList = new ArrayList<>();
		for(int deltaIdx = 0; deltaIdx < D_ROW.length; deltaIdx++) {
			nextRow = row + D_ROW[deltaIdx];
			nextCol = col + D_COL[deltaIdx];
			// 맵의 범위 안이라면 방향리스트에 deltaIdx를 넣는다.
			if(isAvailable(nextRow, nextCol, 2)) {
				directionList.add(deltaIdx);
			}
		}
	}
	
	private static boolean isAvailable(int row, int col, int toggle) {
		// 공기 청정기 1번
		if(toggle == 0) {
			if(row >= 0 && col >= 0 && row <= cleaner.get(0).row && col < colSize)
				return true;
		}
		// 공기 청정기 2번
		else if(toggle == 1) {
			if(row >= cleaner.get(1).row && col >= 0 && row < rowSize && col < colSize)
				return true;
		}
		else if(toggle == 2) {
			// 맵 범위 안이고 공기청정기가 아니라면
			if(row >= 0 && col >= 0 && row < rowSize && col < colSize && originMap[row][col] != AIR_CLEANER)
				return true;
		}

		return false;
	}
	
	/**
	 * 먼지 처리가 끝나면 공기청정기를 작동시킨다.
	 * 공기 청정기는 시작점으로부터 오른쪽 끝까지 간 후, 공기청정기의 윗부분이라면 위로, 아랫부분이라면 아래로 순환한다.
	 * 이후 끝에 도달하면 왼쪽으로 이동하고, 또 끝에 도달하면 공기청정기의 위치로 돌아오게된다.
	 * 이때, 1초에 한칸씩 움직이기 때문에 먼지를 퍼트리고 옮기고를 반복하여 청정기에 닿는 숫자를 모두 더하여 저장한다.
	 */
	private static void activateAirCleaner() {
		// copyMap을 가지고 이동시켜야한다.
		// 위 아래 다르게 동작해야한다.
		// 첫번째 청정기는 0부터 순차적으로 하면 되고
		// 두번째 청정기는 0부터 역순으로 하면 된다.
		for(int cleanerIdx = 0; cleanerIdx < cleaner.size(); cleanerIdx++) {
			int row = cleaner.get(cleanerIdx).row;
			int col = cleaner.get(cleanerIdx).col;
			copyMap[row][col] = AIR_CLEANER;
			int deltaIdx = 0;
			
			// 한칸씩 당기면서 확인한다.
			while(true) {
				int nextRow = row + D_ROW[deltaIdx];
				int nextCol = col + D_COL[deltaIdx];

				// 범위 밖을 넘어가면 다음 델타 인덱스로 바꿔준다.
				if(!isAvailable(nextRow, nextCol, cleanerIdx)) {
					if(cleanerIdx == 0) {
						deltaIdx++;
//						if(deltaIdx == D_ROW.length)
//							break;
					}
					else {
						deltaIdx--;
						if(deltaIdx < 0)
							deltaIdx = D_ROW.length - 1;
					}
					continue;
				}
				
				// 공기 청정기를 만나면 종료
				if(copyMap[nextRow][nextCol] == AIR_CLEANER)
					break;
				
				// 첫 시작 예외
				if(copyMap[row][col] == AIR_CLEANER) {
					copyMap[nextRow][nextCol] = 0;
					row = nextRow;
					col = nextCol;	
					continue;
				}
				

				// 값 옮기기
				copyMap[row][col] = copyMap[nextRow][nextCol];
				copyMap[nextRow][nextCol] = 0;
//				System.out.println();
//				for(int idx = 0; idx < rowSize; idx++) {
//					System.out.println(Arrays.toString(copyMap[idx]));
//				}
				// 다음 값으로 밀기
				row = nextRow;
				col = nextCol;		
				
			}
		}
	}
	
	private static void updateMap() {
		for(int row = 0; row < rowSize; row++) {
			originMap[row] = copyMap[row].clone();
		}
	}

	public static void main(String[] args) throws IOException {
		
		init();
		
		timeFlow();
		
		// 결과 출력
		System.out.println(leftDust);
	}// main end
}
