package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 크기가 2^N × 2^N인 2차원 배열을 Z모양으로 탐색하려고 한다.
 * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
 * 
 * 1. 첫째 줄에 정수 N, row, col이 주어진다.
 * 2. row행 col열을 몇 번째로 방문했는지 출력한다.
 * 
 * 일단 배열을 만들고 재귀를 돌며 해당 배열에 값을 입력한다.
 * 입력하다가 row행 col열에 도달하면 결과 출력
 *
 */

public class BOJ_1074 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;		// 맵의 한 변 길이
	static int targetRow;	// 목표 행
	static int targetCol;	// 목표 열
	static int answer; 		// 정답
	static final int boxLen = 2;	// 작은 박스 탐색 길이
	
	// 구동 함수
	private static void findTarget(int row, int col, int size) {	// index는 탐색 번호이다.
		// 기저 조건
		if(size == 1) {
			System.out.println(answer);
			return;
		}
		
		// 맵의 사분면을 나눠서 타겟이 있는 사분면을 특정한다.
		// 처음 가장 큰 맵 전체를 사분면으로 나눠서 확인하고
		// 이후 한 사분면이 특정되면 다시 해당 사분면은 사분면으로 나눠서 타겟의 위치를 특정하기를 반복한다.
		// 이후 해당 재귀함수에서의 사분면 길이가 1이 되면 정답을 특정한 것이므로 정답을 출력하고 종료한다.
		// 여기서 answer는 각 사분면의 첫번째 인덱스를 나타낸다.
		// ■ □ | ■ □
		// □ □ | □ □
		// ---------
		// ■ □ | ■ □
		// □ □ | □ □
		// 각각의 색칠된 부분이 answer이다.
		// 가장 먼저 (0, 0)위치의 값 0을 answer가 가진다.
		// 이후 target이 있는 사분면을 찾게 되면 해당 사분면의 (0, 0)위치인 색칠된 사각형의 인덱스로 값이 바뀌게 된다.
		// 이를 반복하면 결국에 마지막에 남는 answer의 값이 나오게 된다.
		int newSize = size / 2;
		
		// 1사분면에 있으면
		if(targetRow < row + newSize && targetCol < col + newSize) {
			findTarget(row, col, newSize);
		}
		// 2사분면에 있으면
		else if(targetRow < row + newSize && targetCol >= col + newSize) {
			answer += (size*size) / 4;
			findTarget(row, col + newSize, newSize);
		}
		// 3사분면에 있으면
		else if(targetRow >= row + newSize && targetCol < col + newSize) {
			answer += ((size*size) / 4) * 2;
			findTarget(row + newSize, col, newSize);
		}
		// 4사분면에 있으면
		else if(targetRow >= row + newSize && targetCol >= col + newSize) {
			answer += ((size*size) / 4) * 3;
			findTarget(row + newSize, col + newSize, newSize);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 기본 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		answer = 0;
		
		// 정보 입력
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());		// 2의 몇 승을 할 것인지 입력 받는다.
		targetRow = Integer.parseInt(st.nextToken());	// 목표 행 입력
		targetCol = Integer.parseInt(st.nextToken());	// 목표 열 입력
		
		// 입력 받은 N만큼 맵을 만든다.
		mapSize = (int) Math.pow(2, N);
		
		findTarget(0, 0, mapSize);
		
		
	}
}
