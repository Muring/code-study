package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 벽돌깨기
 * 
 * 구술을 쏘아 벽돌을 깨트리는 게임을 하려고 한다.
 * 구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W x H 배열로 주어진다.
 * ① 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * ② 벽돌은 숫자 1 ~ 9 로 표현되며, 구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 )칸 만큼 같이 제거된다.
 * 
 * N 개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
 * N, W, H, 그리고 벽돌들의 정보가 주어질 때, 남은 벽돌의 개수를 구하라!
 * 
 * 
 * [입력]
 * 가장 첫 줄에는 총 테스트 케이스의 개수 T 가 주어지고, 그 다음 줄부터 T 개의 테스트 케이스가 주어진다.
 * 각 테스트 케이스의 첫 번째 줄에는 N, W, H 가 순서대로 공백을 사이에 두고 주어지고,
 * 다음 H 줄에 걸쳐 벽돌들의 정보가 1 줄에 W 개씩 주어진다.
 * 
 * [출력]
 * 출력은 #t 를 찍고 한 칸 띄운 다음 정답을 출력한다.
 * (t 는 테스트 케이스의 번호를 의미하며 1 부터 시작한다)
 * 
 * [풀이]
 * 1. 데이터를 입력받는다.
 * 2. 중복순열로 벽돌을 떨어뜨릴 수 있는 모든 경우의 수를 구한다.
 * 3. 각 경우의 수들에 따라 벽돌을 떨어트린다.
 * 4. 떨어진 벽돌을 각 범위값만큼 부신다.
 * 	4-1. 연쇄적으로 부저시는 것은 별도의 bfs
 * 5. 부실 수 있는 벽돌을 모두 부신 다음 남은 벽돌들을 밑으로 내려준다.
 * 6. 최소값 비교 후 결과 출력
 *
 */

public class SWEA_5656 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int dropCnt, rowSize, colSize;
	static int[][] originMap;	// 원본 맵
	static int[][] map;		// 계산에 사용할 맵
	static int[] arr;	// 중복순열 저장 배열
	static int minCnt;
	
	static final int[] dRow = { -1, 1, 0, 0 };
	static final int[] dCol = { 0, 0, -1, 1 };
	
	static final int BLANK = 0;
	static final int BRICK = 1;
	
	private static class Point {
		int row;
		int col;
		int range;
		
		public Point(int row, int col, int range) {
			this.row = row;
			this.col = col;
			this.range = range;
		}
	}
	
	private static void findDropPoint(int count) {
		if(count == dropCnt) {
			// 벽돌깨기 시작
//			System.out.println(Arrays.toString(arr));
			startDrop();
//			for(int row = 0; row < rowSize; row++) {
//				System.out.println(Arrays.toString(map[row]));
//			}
			// 최소값 갱신
			minCnt = Math.min(minCnt, countMap());
			
			// 맵 초기화
			copyMap();
			return;
		}
		
		for(int idx = 0; idx < colSize; idx++) {
			arr[count] = idx;
			findDropPoint(count + 1);
		}
	}
	
	private static void startDrop() {
		// row와 col은 위에서 떨어트린 구슬과 닿은 블록의 위치이다.
		int row = 0;
		int col = 0;
		// 구슬 수 만큼 반복
		for(int dropIdx = 0; dropIdx < dropCnt; dropIdx++) {
			// 열은 고정이므로 행만 반복
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				// 가장 처음 부딪힌 벽돌의 좌표를 기억한다.
				if(map[rowIdx][arr[dropIdx]] != BLANK) {
					row = rowIdx;
					col = arr[dropIdx];
					break;
				}
			}
			spreadExplosion(row, col);	// 폭발 범위만큼 부신다.
			blockDown();	// 벽돌을 내린다.
		}	// for drop
	}
	
	private static void spreadExplosion(int row, int col) {
		// 큐에 해당 포인트의 정보를 저장하고 해당 위치를 파괴
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(row, col, map[row][col]));
		map[row][col] = BLANK;
		
		// bfs 시작
		while(!que.isEmpty()) {
			Point currentPoint = que.poll();
			int range = currentPoint.range;
			
			// 터트릴 범위만큼 반복
			for(int idx = 1; idx < range; idx++) {
				for(int deltaIdx = 0; deltaIdx < dRow.length; deltaIdx++) {
					int nextRow = currentPoint.row + dRow[deltaIdx] * idx;
					int nextCol = currentPoint.col + dCol[deltaIdx] * idx;
					
					// 다음 범위가 맵 범위 밖인지 아닌지 확인
					if(!isAvailable(nextRow, nextCol))
						continue;
					
					// 다음 칸도 벽돌이면 큐에 저장한다.
					if(map[nextRow][nextCol] != BLANK) {
						que.add(new Point(nextRow, nextCol, map[nextRow][nextCol]));
						map[nextRow][nextCol] = BLANK;
					}
				}
			}
		}
	}
	
	// 파괴가 끝난 모든 블록을 아래로 당겨준다.
	private static void blockDown() {
		Stack<Integer> stack = new Stack<>();
		
		// 각 열을 모두 확인해야하기 때문에 열만큼 반복
		for(int col = 0; col < colSize; col++) {
			// 위에서부터 모든 행을 확인하면서 벽돌이라면 스택에 저장하고 해당 부분을 0으로 만들어준다.
			for(int row = 0; row < rowSize; row++) {
				if(map[row][col] != BLANK) {
					stack.add(map[row][col]);
					map[row][col] = BLANK;
				}
			}
			// 밑에서부터 올라가면서 벽돌 설치
			for(int row = rowSize - 1; row >= 0; row--) {
				// 스택이 비어있다면 종료
				if(stack.isEmpty())
					break;
				else
					map[row][col] = stack.pop();
			}
		}
	}
	
	private static int countMap() {
		int count = 0;
		
		for(int row = 0; row < rowSize; row++) {
			for(int col = 0; col < colSize; col++) {
				if(map[row][col] != BLANK)
					count++;
			}
		}
//		System.out.println(count);
		return count;
	}
	
	private static void copyMap() {
		for(int row = 0; row < rowSize; row++)
			map[row] = originMap[row].clone();
	}

	
	private static boolean isAvailable(int row, int col) {
		if(row < 0 || col < 0 || row >= rowSize || col >= colSize || map[row][col] == BLANK)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			
			// 맵 사이즈 입력
			st = new StringTokenizer(br.readLine());
			dropCnt = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			rowSize = Integer.parseInt(st.nextToken());
			
			// 시작 전 필요부분 초기화
			minCnt = Integer.MAX_VALUE;
			arr = new int[dropCnt];
			originMap = new int[rowSize][colSize];
			map = new int[rowSize][colSize];
			
			// 맵 정보 입력
			for(int row = 0; row < rowSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < colSize; col++) {
					originMap[row][col] = map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 벽돌을 떨어트릴 지점 찾기
			findDropPoint(0);
			
			sb.append("#").append(tc).append(" ").append(minCnt).append("\n");
		}	// tc end
		System.out.println(sb);
	}	// main end
}
