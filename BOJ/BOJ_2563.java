package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이
 * 
 * 도화지에 검은색 색종이를 붙여 검은색이 붙어있는 도화지의 면적을 구하는 문제다.
 * 100 x 100짜리 배열을 만들어서 해당 색종이가 붙은 범위를 모두 1로 바꿔주고 넓이를 계산한다.
 * 
 * 첫번째 줄에 색종이의 수를 입력받는다.
 * 둘째 줄에 각 색종이의 왼쪽아래 모서리 좌표가 주어진다.
 * 
 * @author sehyeon.eom
 *
 */
public class BOJ_2563 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int mapSize = 100;							// 도화지 한 변 크기
	static final int confettiSize = 10;						// 색종이 한 변 크기
	static final int[][] map = new int[mapSize][mapSize];	// 도화지
	static int confettiCount;								// 색종이 개수
	static int area;										// 색종이가 차지하는 면적
	
	public static void main(String[] args) throws IOException {
		// 객체 생성 및 변수 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		confettiCount = 0;
		area = 0;
		
		// 입력받을 총 색종이 개수 입력
		confettiCount = Integer.parseInt(br.readLine().trim());
		
		// 색종이의 정보를 입력받으면서 넓이를 구한다.
		for(int confettiIdx = 0; confettiIdx < confettiCount; confettiIdx++) {
			// 색종이의 정보 입력
			// 사실 배열적으로 따지면 x값은 열 값이고 y값이 행 값이기 때문에 반대가 되야하지만
			// 어차피 정사각형이라 상관없으니 그냥 한다.
			// 색종이의 왼쪽 아래 모서리 좌표 값을 받는다.
			st = new StringTokenizer(br.readLine());
			int leftBottomX = Integer.parseInt(st.nextToken());
			int leftBottomY = Integer.parseInt(st.nextToken());
			
			// 입력받은 색종이의 좌표로부터 색종이가 차지하는 면적을 계산
			// 이때, 이미 다른 색종이가 범위를 차지하고 있다면 해당 부분은 계산하지 않고 넘어간다.
			for(int x = leftBottomX; x < leftBottomX + confettiSize; x++) {
				for(int y = leftBottomY; y < leftBottomY + confettiSize; y++) {
					if(map[x][y] == 1)
						continue;
					else {
						map[x][y] = 1;
						area++;
					}
				}
			}
		}
		System.out.println(area);
	}
}
