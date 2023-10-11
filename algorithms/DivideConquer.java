package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DivideConquer {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int map[][];
	static int white, green;
	
	// 영역의 좌상단 row, col, 영역의 크기
	private static void makeSpace(int startRow, int startCol, int size) {
		
		int sum = 0;
		for(int row = startRow; row < startRow + size; row++) {
			for(int col = startCol; col < startCol + size; col++) {
				sum += map[row][col];
			}
		}
		
		// 모두 하얀색인 공간인 경우 (기저조건)
		if(sum == 0) {
			white++;
		}
		// 모두 초록색인 공간 (기저조건)
		else if(sum == size * size) {
			green++;
		}
		// 두 색이 섞여있는 공간 
		else {
			int half = size / 2;
			makeSpace(startRow, startCol, half);				// 1사분면
			makeSpace(startRow, startCol + half, half);			// 2사분면
			makeSpace(startRow + half, startCol, half);			// 3사분면
			makeSpace(startRow + half, startCol + half, half);	// 4사분면
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int mapSize = Integer.parseInt(br.readLine().trim());
		map = new int[mapSize][mapSize];
		
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		makeSpace(0, 0, mapSize);
		System.out.println(white);
		System.out.println(green);
	}
}
