package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 석유 시추
 * 
 * 지도에는 매장된 석유의 양이 기록되어 있다.
 * 시추를 진행할 수 있는 영역M과 실제 시추할 영역의 개수 N이 주어진다.
 * 
 * 시추를 진행할 수 있는 각 영역에는 지도의 가로와 세로 영역마다
 *  한 번에 추출할 수 있는 석유의 양, 시추를 진행할 횟수가 주어진다.
 * 
 * 시추를 진행할 수 있는 각 영역에는 석유가 매장되어 있지 않으며, 
 * 석유를 추출하고자 할 때는 석유가 매장되어 있지 않다면 추출하고자하는 방향을 유지하면서 탐색하여 석유가 있는 곳을 추출한다.
 * 이때, 추출은 동시에 이뤄진다는 것을 주의하자.
 * 
 * 추출할 수 있는 최대 석유의 양을 출력
 * @author sehyeon.eom
1
10 // 지도의 크기
5 3 // 시추할 수 있는 영역의 개수(M), 실제 시추할 개수(N)
1 2 9 2 // 가로, 세로, 추출할 수 있는 석유의 양, 시추 횟수
4 5 1 1
5 9 1 1
7 8 2 1
8 8 3 2
4 5 8 1 9 9 5 9 3 2
5 2 0 2 6 5 3 2 7 4
5 5 9 9 2 5 4 3 1 5
7 7 2 9 9 1 3 3 6 9
4 9 7 8 9 0 5 3 1 3
5 9 6 4 8 9 3 9 3 0
8 7 7 1 5 6 4 8 1 5
6 6 8 4 9 7 4 4 0 8
4 9 2 6 3 7 1 6 0 4
1 8 8 6 4 6 8 7 4 7
 */

public class SWEA_17220 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;				// 맵 사이즈
	static int map[][];				// 전체 맵
	static int totalDiggerCount;	// 전체 시추기 개수
	static int usableDiggerCount;	// 사용하는 최대 시추기 수
	static List<Digger> diggerList;	// 시추기 저장 리스트
	static int[] diggerComb;		// 시추기 조합
	static int oilAmount;			// 현재 시추량
	static int maxOilAmount;		// 최대 시추량
	static boolean[][] empty;		// 현재 과정에서 비어버렸는지 확인할 배열
	static int maxDigCount;			// 최대 시추 횟수
	
	// 상 하 좌 우 좌상 우상 좌하 우하
	static final int[] dRow = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static final int[] dCol = { 0, 0, -1, 1, -1, 1, -1, 1 };
	
	static class Digger{
		int row;			// 시추기의 좌표
		int col;
		int diggableAmount;	// 한번에 시추 가능한 석유의 양
		int digCount;		// 시추 횟수
		
		public Digger(int row, int col, int diggableAmount, int digCount) {
			super();
			this.row = row;
			this.col = col;
			this.diggableAmount = diggableAmount;
			this.digCount = digCount;
		}

		@Override
		public String toString() {
			return "Digger [row=" + row + ", col=" + col + ", diggableAmount=" + diggableAmount + ", digCount="
					+ digCount + "]";
		}
	}
	
	
	private static void getDiggerComb(int start, int count) {
		if(count == usableDiggerCount) {
//			System.out.println(Arrays.toString(diggerComb));
			startDig();
			return;
		}
		
		for(int idx = start; idx < totalDiggerCount; idx++) {
			diggerComb[count] = idx;
			getDiggerComb(idx + 1, count + 1);
		}
	}
	
	// 시추를 시작하는 메소드
	// 시추는 동시에 이루어져야 하므로 맵의 값을 변화시키는 것은 마지막에 한번에 이루어져야 한다.
	private static void startDig() {
		// 원본 맵을 보존하기 위해 복사된 맵을 만든다.
		int[][] copyMap = copy();
		oilAmount = 0;	// 매번 계산할 때마다 현재 시추 값 초기화
		
		// 최대 시추 가능 횟수만큼 반복
		for(int digIdx = 1; digIdx <= maxDigCount; digIdx++) {
			// 현재 계산 동안 맵의 값이 0이되는 경우를 체크할 배열
			empty = new boolean[mapSize][mapSize];
			// 각 시추기의 정보를 가져와 계산한다.
			for(int diggerIdx = 0; diggerIdx < usableDiggerCount; diggerIdx++) {
				// 현재 시추기 정보 가져오기
				Digger currentDigger = diggerList.get(diggerComb[diggerIdx]);
				
				// 현재 시추기의 시추 가능 횟수가 모자라면 다음 시추기로 넘어간다.
				if(currentDigger.digCount < digIdx)
					continue;
				
				// 각 시추기에서 사방으로 확인한다.
				for(int deltaIdx = 0; deltaIdx < dRow.length; deltaIdx++) {
					int nextRow = currentDigger.row + dRow[deltaIdx];
					int nextCol = currentDigger.col + dCol[deltaIdx];
					
					// 맵 범위 밖이라면 통과
					if(!isAvailable(nextRow, nextCol))
						continue;
					
					// 맵 범위 내라면
					// 빈 공간이고 다른 시추기가 시추한적이 없는 곳이라면 직선으로 한 칸 더 가야한다.
					if(copyMap[nextRow][nextCol] == 0 && !empty[nextRow][nextCol]) {
						while(isAvailable(nextRow, nextCol) && copyMap[nextRow][nextCol] == 0 && !empty[nextRow][nextCol]) {
							nextRow = nextRow + dRow[deltaIdx];
							nextCol = nextCol + dCol[deltaIdx];
						}
						if(!isAvailable(nextRow, nextCol))
							continue;
					}
					
					// 시추 가능한 양 보다 매장량이 더 많다면
					if(copyMap[nextRow][nextCol] > currentDigger.diggableAmount) {
						oilAmount += currentDigger.diggableAmount;
						copyMap[nextRow][nextCol] -= currentDigger.diggableAmount;
					}
					// 시추 가능한 양이 매장량보다 더 많다면
					else {
						// 다른 시추기가 지나가서 이미 비어있다면
						if(empty[nextRow][nextCol]) {
							continue;
						}
						// 이번이 처음 시추하는 것이라면
						else {
							oilAmount += copyMap[nextRow][nextCol];
							copyMap[nextRow][nextCol] = 0;
							empty[nextRow][nextCol] = true;
						}
					}
					
				}	// delta for end

			} 	// digger for end
		}
		
		// 시추한 기름 최대값 계산
		maxOilAmount = Math.max(maxOilAmount, oilAmount);
	}
	
	// 맵 범위 안인지 확인하는 메소드
	private static boolean isAvailable(int row, int col) {
		if(0 <= row && 0 <= col && row < mapSize && col < mapSize)
			return true;
		
		return false;
	}
	
	// 전체 맵 복사 메소드
	private static int[][] copy(){
		int[][] copyMap = new int[mapSize][mapSize];
		for(int idx = 0; idx < mapSize; idx++) {
			copyMap[idx] = Arrays.copyOf(map[idx], mapSize);
		}
		
		return copyMap;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 케이스 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		// 전체 케이스 만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			diggerList = new ArrayList<>();
			maxOilAmount = 0;
			
			// 맵 사이즈 저장
			mapSize = Integer.parseInt(br.readLine().trim());
			
			// 전체 시추기의 개수와 사용할 시추기의 개수 입력
			st = new StringTokenizer(br.readLine());
			totalDiggerCount = Integer.parseInt(st.nextToken());
			usableDiggerCount = Integer.parseInt(st.nextToken());
			
			// 시추기 정보 저장
			for(int idx = 0; idx < totalDiggerCount; idx++) {
				st = new StringTokenizer(br.readLine());
				int diggerRow = Integer.parseInt(st.nextToken());
				int diggerCol = Integer.parseInt(st.nextToken());
				int digAmount = Integer.parseInt(st.nextToken());
				int digCount = Integer.parseInt(st.nextToken());
				diggerList.add(new Digger(diggerRow, diggerCol, digAmount, digCount));
				
				maxDigCount = Math.max(maxDigCount, digCount);
			}
			
			// 전체 맵 정보 저장
			map = new int[mapSize][mapSize];
			for(int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < mapSize; col++)
					map[row][col] = Integer.parseInt(st.nextToken());
			}
			
			// 시추기의 조합 구하기
			diggerComb = new int[usableDiggerCount];
			getDiggerComb(0, 0);

			sb.append("#").append(tc).append(" ").append(maxOilAmount).append("\n");
		}	// tc end
		System.out.println(sb);
	}	// main end
}
