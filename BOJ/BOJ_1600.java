package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 말이 되고픈 원숭이
 * 
 * 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다.
 * 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 
 * 대각선 방향은 인접한 칸에 포함되지 않는다.
 * 
 *  격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다. 
 *  인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다. 
 *  격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.
 *  
 *  [입력]
 *  첫째 줄에 정수 K가 주어진다. 
 *  둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 
 *  그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다. 
 *  장애물이 있는 곳으로는 이동할 수 없다. 시작점과 도착점은 항상 평지이다. 
 *  W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.
 *  
 *  [출력]
 *  첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 
 *  시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
 *  
 * @author sehyeon.eom
 *
 */

public class BOJ_1600 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int horseLikeCount;
	static int rowSize;
	static int colSize;
	static int[][] map;
	
	// 우 우하 하
	static final int dRow[] = { 0, 1, 1 };
	static final int dCol[]	= { 1, 1, 0 };
	
	private static void monkeyMove(int row, int col) {
		// 기저 조건
		if(row == rowSize - 1 && col == colSize - 1)
			return;
		
		// 원숭이가 말처럼 움직일 수 있다면
		if(horseLikeCount > 0) {
			horseLikeCount--;
			moveLikeHorse();
			return;
		}
		
		// 평범한 원숭이의 움직임
		
	}
	
	// 원숭이가 말처럼 움직일 때의 메소드
	private static void moveLikeHorse() {
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 말처럼 움직일 수 있는 횟수가 주어진다.
		horseLikeCount = Integer.parseInt(br.readLine().trim());
		
		// 격자판의 크기가 주어진다.
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 맵의 정보가 주어진다.
		// 0은 평지 1은 장애물이다.
		for(int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 원숭이 출발
		// 원숭이는(0,0)에서 출발해 (rowSize-1, colSize-1)로 간다.
		
		
		
		
	}
}