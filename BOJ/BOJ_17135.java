package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 캐슬 디펜스
 * 
 * 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다.
 * NxM인 격자판에서 각 칸에 적 한명이 있을 수 있다.
 * N번행의 바로 아래 행의 모든 칸에는 성이 있다.
 * 
 * 성을 적에게서 지키기 위해 궁수를 3명 배치한다.
 * 궁수는 성이 있는 칸에 배치할 수 있고 한 칸에는 최대 한 명의 궁수만 배치 가능하다.
 * 모든 궁수는 동시에 공격하며 공격하는 적은 거리가 D 이하인 적 중에서 가장 가까운 적이다.
 * 거리가 같은 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다.
 * 같은 적이 여러 궁수에게 공겨당할 수 있다.
 * 
 * 공격이 끝나면 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동 시 게임에서 제외한다.
 * 모든 적이 사라지면 게임이 끝난다.
 * 
 * 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 최대 적의 수를 계산하자.
 * 격자판 두 위치의 거리는 |r1 - r2| + |c1 - c2|
 * 
 * 1. 첫째 줄에 격자판의 행, 열 수와 궁수의 공격 거리 제한 D가 주어진다.
 * 2. 둘째 줄부터 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸
 * 3. 입력받은 행과 열의 값으로 맵을 만든다. 만들 때 행의 수를 한 칸 더 늘려 성벽에 궁수를 설치할 수 있도록 한다.
 * 4. putArcher 메서드에서 조합을 사용하여 궁수가 설치될 수 있는 경우의 수를 모두 만들어 각각의 결과를 도출 할 수 있도록 한다.
 * 5. 조합 하나가 생성될 때 gameStart 메서드로 이동해 게임을 시작한다.
 * 	-> 게임이 시작되면 먼저 활을 쏴 적중된 적을 제거한다.
 *    -> 동시에 같은 적을 쏠 경우 정답이 하나만 올라가게 처리를 해야 한다.
 *    -> 그렇다고 적을 먼저 제거했다가 다음 궁수가 다른 타겟을 쏠 여지가 있기 떄문에 모두 쏠 때까지 적을 제거하면 안된다.
 *    -> 제거하는 방법은 killed 배열을 사용하여 확인한다.
 * 	-> 이후 남은 모든 적들의 위치를 한칸씩 내린다.
 * 	  -> 내리는 동작은 밑의 행부터 시작해 위로 올라가며 한 칸씩 아래로 내린다.
 * 	  -> 내릴 때, 성벽에 닿는다면 제거
 * 	-> 위 과정을 모든 적이 사라질 때까지 반복한다.
 * 	-> 이후 나온 킬 수를 현재 저장된 maxKill과 비교하여 더 큰 수를 저장한다.
 * 6. 모든 결과가 끝났을 때 maxKill을 출력한다.
 *
 */

public class BOJ_17135 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int map[][];					// 게임 맵
	static int rowSize, colSize;		// 게임 맵 크기
	
	static int archerRange;				// 궁수의 공격 범위
	static final int ARCHER_COUNT = 3;	// 전체 궁수 수
	static int archerComb[];			// 궁수 조합
	
	static int enemyCount;				// 적의 수
	static boolean killed[][];			// 적의 생존 여부 확인 배열 -> false 살아있다 true 죽었다.
	static int curKill;					// 현재 조합으로 죽인 킬 수
	static int maxKill;					// 최대 킬 수
	
	static final int MAX_VALUE = Integer.MAX_VALUE;
	
	// 궁수가 배치딜 수 있는 조합을 생성하는 메소드
	// start는 다음에 지정해야 할 요소의 위치, count는 현재 생성중인 조합의 길이
	private static void putArcher(int start, int count) {
		// 기저 조건
		// 궁수는 3명이기 때문에
		if(count == ARCHER_COUNT) {
			// 해당 궁수 조합으로 게임을 시작한다.
			int[][] tempMap = copyMap();
			gameStart(tempMap);
			return;
		}
		
		// 조합 수행
		for(int idx = start; idx < colSize; idx++) {
			archerComb[count] = idx;
			putArcher(idx + 1, count + 1);
		}
		
	}
	
	private static int[][] copyMap() {
		int[][] copyMap = new int[rowSize + 1][colSize];
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				copyMap[row][col] = map[row][col];
			}
		}
		return copyMap;
	}
	
	// 매번 새 지도로 게임을 해야하기 때문에 map을 매개변수로 받는다.
	private static void gameStart(int tempMap[][]) {
		// 숫자 카운트하기 전 초기화
		curKill = 0;			// 현재 전체 킬 수
		
		for(int idx = 0; idx < rowSize; idx++) {
			// 궁수 공격 시작
			tempMap = attack(tempMap);
			
			// 공격이 끝난 후 적의 이동
			tempMap = enemyMove(tempMap);
		}
		
		// 현 궁수 조합으로의 게임이 끝났다.
		// 현재 킬 수와 최대 킬 수를 비교한다.
		maxKill = Math.max(maxKill, curKill);
				
	}
	
	private static int[][] attack(int tempMap[][]) {
		// 현재 archerComb에 궁수의 위치 인덱스가 저장되어 있다.
		// 각 궁수로부터 최단 거리에 있는 적을 찾는다.
		// 궁수의 위치는 행 rowSize로 고정이고 열은 archerComb[idx]이다.
		// 맵의 전체를 돌면서 1을 발견하면 궁수와의 거리를 구해 distance에 저장
		
		int minDistance;				// 적과 궁수의 최소 거리
		int minRow = 0, minCol = 0;		// 최소 거리일 때의 행과 열
		int temp[][] = new int[3][2]; 	// 궁수가 쏠 목표의 행과 열 위치 저장 배열
		
		// 궁수 세 명 만큼 반복
		for(int archerIdx = 0; archerIdx < ARCHER_COUNT; archerIdx++) {
			minDistance = MAX_VALUE;
			// 배열을 모두 뒤지며 적을 찾는다.
			for(int row = 0; row < rowSize; row++) {
				for(int col = 0; col < colSize; col++) {
					// 적을 찾았다.
					if(tempMap[row][col] == 1) {
						// 현재 구한 거리가 최소 거리보다 작다면
						if(distance(rowSize, row, archerComb[archerIdx], col) <= minDistance) {
							if(distance(rowSize, row, archerComb[archerIdx], col) < minDistance) {
								minDistance = distance(rowSize, row, archerComb[archerIdx], col);
								minRow = row;
								minCol = col;
							}
							
							// 현재 구한 거리가 최소 거리와 같다면
							else {
								if(minCol > col) {
									minRow = row;
									minCol = col;
								}
							}
						}
					}
				}
			}
			// 구한 거리가 궁수 사거리보다 짧거나 같으면
			if(minDistance <= archerRange) {
				killed[minRow][minCol] = true;	// 목표로 하는 지점 죽였다고 설정
				temp[archerIdx][0] = minRow;	// 해당 좌표 저장
				temp[archerIdx][1] = minCol;
			}
		}
		
		// 죽음을 확정한다.
		for(int idx = 0; idx < ARCHER_COUNT; idx++) {
			
			if(killed[temp[idx][0]][temp[idx][1]] == true) {	// 이미 죽었다고 표시가 되어있으면
				
				tempMap[temp[idx][0]][temp[idx][1]] = 0;		// 해당 부분 값을 0으로 바꿔준다.
				killed[temp[idx][0]][temp[idx][1]] = false;		// 해당 부분 사람은 사라졌으니 다시 초기화
				curKill++;										// 킬 값 올리기
			}
		}
		
		return tempMap;
	}
	
	// 궁수와 목표 간의 거리 구하기
	private static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
	
	private static int[][] enemyMove(int tempMap[][]) {
		// 적들을 한 칸씩 아래로 내려야 한다.
		// 행의 맨 밑에서부터 아래로 내리자.
		for (int row = rowSize; row > 0; row--) {
            for (int col = 0; col < colSize; col++) {
            	tempMap[row][col] = tempMap[row - 1][col];
            }
        }
		Arrays.fill(tempMap[0], 0);	// 맵의 첫 줄을 전부 0으로 초기화
	
		return tempMap;
	}
	
	
	public static void main(String[] args) throws IOException {
		// 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫째 줄에 격자판의 행, 열 수와 궁수의 공격 거리 제한 D가 주어진다.
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		archerRange = Integer.parseInt(st.nextToken());
		
		// 둘째 줄부터 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸
		map = new int[rowSize + 1][colSize];	// 입력 받은 값으로 맵 생성, 이때 궁수를 배치할 성벽 행 추가 생성
		killed = new boolean[rowSize][colSize];	// 적의 생존 유무를 확인하는 배열 생성, 성벽 행은 필요 없음
		// 맵 입력
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == 1)
					enemyCount++;
			}
		}
		
		// putArcher 메서드에서 조합을 사용하여 궁수가 설치될 수 있는 경우의 수를 모두 만들어 각각의 결과를 도출 할 수 있도록 한다.
		archerComb = new int[ARCHER_COUNT];
		
		putArcher(0, 0);
		
		System.out.println(maxKill);
		
	}	// main end
}
