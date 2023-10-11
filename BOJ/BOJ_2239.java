package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author sehyeon.eom
 * 
 * 스도쿠
 * 아직 숫자가 채워지지 않은 칸에는 0이 주어진다.
 * 9개의 줄에 9개의 숫자로 답을 출력한다. 
 * 답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다. 즉, 81자리의 수가 제일 작은 경우를 출력한다.
 * 
 * 1. 10x10 크기의 맵에 스도쿠 판을 입력받는다.
 * 2. 스도쿠의 상태를 확인하며 값을 입력한다.
 * 	2-1. 가로, 세로, 네모칸을 확인하여 가능하다면 숫자를 넣는다.
 *  2-2. 위와 같은 방법으로 다음 숫자들도 넣다가 넣을 수 없는 숫자가 나온다면 전 숫자로 돌아가서 다시 다음 숫자부터 가능여부를 판단한다.
 *  2-3. 위 방법을 반복한다.
 * 3. 나오는 답을 저장하는데 이때, 마지막 자리의 수가 가장 작은 경우를 출력한다.
 * 	3-1. 이때, 숫자 확인을 위에서부터 아래로 내려오는 방식으로 하기 때문에 처음 정답이 구해졌을 때 바로 종료하면 그것이 정답이다.
 *
 */
public class BOJ_2239 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int MAP_SIZE = 9;
	static int[][] map;
	static boolean[] notAvailable;
	static boolean sudokuComplete;	// 첫 정답이 구해졌는지 안구해졌는지 나타내는 변수
	
	// 기본적인 확인 방식은 dfs이다.
	private static void sudoku(int index) {
		// 기저조건
		if(index == MAP_SIZE * MAP_SIZE) {
			sudokuComplete = true;
			return;
		}
		
		// 동작 구문
		// 현재 확인하는 스도쿠 판의 위치
		int row = index / MAP_SIZE;
		int col = index % MAP_SIZE;
		
		// 해당 숫자가 0인지 아닌지 확인
		// 0이 아니라면 다음 숫자로 넘어간다.
		if(map[row][col] != 0)
			sudoku(index + 1);
		// 0이라면 스도쿠 실행
		else {
			// 1~9까지의 숫자의 경우의 수를 모두 넣어본다.
			for(int idx = 1; idx <= MAP_SIZE; idx++) {
				// 해당 숫자가 조건에 부합하는지 확인
				if(!isAvailable(row, col, idx))
					continue;
				
				// 부합한다면 맵에 현재 숫자 넣기
				map[row][col] = idx;
				// 다음 숫자를 넣으러 간다.
				sudoku(index + 1);
				
				// 스도쿠가 완성되었다면 즉시 종료
				if(sudokuComplete)
					return;
				// 숫자가 부합하지 않아 되돌아 온 경우 다시 맵의 값을 0으로 바꿔준다.
				map[row][col] = 0;
			}
		}
	}
	
	private static boolean isAvailable(int row, int col, int num) {
		// 가로와 세로 확인
		for(int idx = 0; idx < MAP_SIZE; idx++) {
			if(map[row][idx] == num || map[idx][col] == num)
				return false;
		}
		
		// 사각형 확인
		// 사각형의 왼쪽 최상단의 좌표를 구한다
		int startRow = row / 3 * 3;
		int startCol = col / 3 * 3;
//		System.out.println(startRow + " " + startCol);
		for(int rowIdx = startRow; rowIdx < startRow + 3; rowIdx++) {
			for(int colIdx = startCol; colIdx < startCol + 3; colIdx++) {
				if(map[rowIdx][colIdx] == num)
					return false;
			}
		}
		// 모든 조건을 통과했다면 가능한 숫자이다.
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 맵 입력
		// 띄어쓰기가 없기 때문에 스트링으로 받아서 쪼갠 뒤 int로 변환
		map = new int[MAP_SIZE][MAP_SIZE];
		for(int row = 0; row < MAP_SIZE; row++) {
			String temp = br.readLine();
			for(int col = 0; col < MAP_SIZE; col++) {
				map[row][col] = (int)(temp.charAt(col)) - 48;
			}
		}
		
		// 스도쿠 시작
		sudoku(0);
		
		// 나왔으면 결과를 저장하자.
		for(int row = 0; row < MAP_SIZE; row++) {
			for(int col = 0; col < MAP_SIZE; col++) {
				sb.append(map[row][col]);
			}
			sb.append("\n");
		}
		
		// 결과 출력
		System.out.println(sb);
	}
}