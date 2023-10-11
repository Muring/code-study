package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author MuRing
 * 
 * 가로, 세로의 크기가 각각 100인 정사각형 모양의 흰색 도화지가 있다. 
 * 이 도화지 위에 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다. 
 * 이러한 방식으로 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 검은 영역의 넓이를 구하는 프로그램을 작성하시오.
 * 
 * 1. 입력받을 색종이의 개수를 입력한다.
 * 2. 입력받을 색종이의 왼쪽 아래 모서리 좌표를 입력한다.
 * 3. 넓이 구한다.
 */


public class BOJ_2563_1 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;					// 전체 도화지
	static final int whiteSize = 100;	// 전체 도화지 한 변 길이
	static final int blackSize = 10;	// 검은 색족이 한 변 길이
	static int blackTerritory;			// 색종이가 붙어있는 넓이
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		map = new int[whiteSize][whiteSize];
		
		int paperCount = Integer.parseInt(br.readLine().trim());	// 입력 받을 색종이 개수 입력
		
		// 입력받은 색종이 개수만큼 반복한다.
		// 전체 맵은 int형이므로 기본적으로 0으로 초기화가 되어있따.
		// 해당 맵의 범위 내에 색종이가 붙는다면 그 맵을 1로 바꾸어주어 색칠되었다고 표시하고 넓이를 ++해준다.
		blackTerritory = 0;	// 사용하기 전 초기화
		for(int paperIdx = 0; paperIdx < paperCount; paperIdx++) {
			st = new StringTokenizer(br.readLine());	// 색종이의 정보 입력받는다.
			int startCol = Integer.parseInt(st.nextToken());	// 시작 열
			int startRow = Integer.parseInt(st.nextToken());	// 시작 행
			
			// 색종이의 범위만큼 색 바꿔주기
			for(int row = startRow; row < startRow + blackSize; row++) {
				for(int col = startCol; col < startCol + blackSize; col++) {
					if(row < whiteSize && col < whiteSize) {
						if(map[row][col] == 1)
							continue;
						else {
							map[row][col] = 1;
							blackTerritory++;
						}
					}
					
				}
			}
			
		}
		sb.append(blackTerritory);
		System.out.println(sb);
		
	}
}
