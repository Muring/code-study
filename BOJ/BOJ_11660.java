package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 1. 보드의 사이즈와 합을 구해야하 하는 횟수가 주어진다. // size, sumCount
 * 2. 둘째 줄 부터 표에 채워져 있는 수가 들어온다.
 * 3. 다음 sumCount개의 줄에는 네개의 정수 x1 y1 x2 y2 가 주어진다.
 * 4. (x1 y1) ~ (x2 y2) 의 합을 구해 출력
 * 
 */
public class BOJ_11660 {
	static BufferedReader br;	// 입력
	static StringBuilder sb;	// 출력
	static int SIZE;			// 보드 사이즈
	static int sumCount;		// 합을 구해야하는 횟수
	static int[][] board;		// 보드 저장 공간
	static int[][] boardSum;	// 보드 누적 합 공간
	static int sum;				// 좌표 이내의 합
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));		// 입력받는 값이 한줄에 많기 때문에 버퍼드 리더가 좋다.
		sb = new StringBuilder();										// 출력도 경우의 수가 많기 때문에 스트링 빌더가 더 좋다.
		StringTokenizer st = new StringTokenizer(br.readLine());		// 띄어쓰기 입력이 있기 때문에 토크나이저를 사용한다.
		
		// 데이터 입력
		SIZE = Integer.parseInt(st.nextToken());
		sumCount = Integer.parseInt(st.nextToken());
	
		// 보드 입력
		int rowSum;														// 그 행의 합연산에 쓰일 변수
		board = new int[SIZE][SIZE];									// 보드의 선언 필수!! 자꾸 안해서 터진다.
		boardSum = new int[SIZE][SIZE + 1];
		for(int row = 0; row < SIZE; row++){
			st = new StringTokenizer(br.readLine(), " ");
			rowSum = 0;													// 각 행마다 rowSum 초기화 -> 하지 않으면 값이 계속 더해짐
			for(int col = 0; col < SIZE; col++){
				board[row][col] = Integer.parseInt(st.nextToken());		// 보드에 값 저장
				rowSum += board[row][col];
				boardSum[row][col + 1] = rowSum;						// 보드에 값을 저장하면서 동시에 해당 행에서 받아온 모든 수의 합을 boardSum에 저장한다.
			}
		}

		// 합을 구할 범위 계산
		// sumCount만큼 반복한다.
		for(int tc = 0; tc < sumCount; tc++){

			// 우선 x1 y1 x2 y2를 입력받는다.
			st = new StringTokenizer(br.readLine(), " ");
			
			// 배열의 위치를 입력 받기 때문에 -1을 해줘야한다. 이거 안해줘서 계속 터졌다.
			// 하지만 y2는 안해준다. 왜냐하면 애초에 boardSum의 col 사이즈가 SIZE + 1로 설정이 되어 있기 때문에
			// 0 1 3 6 10
			// 0 2 5 9 14
			// 0 3 7 12 18
			// 0 4 9 15 22
			// 이런 식으로 저장되어 있기 때문에 다른 x와 y보다 한칸 커야 한다.
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken());
			
			// 범위 내의 총 합을 구한다.
			sum = 0;												// sum의 초기화
			for(int row = x1; row <= x2; row++)
				sum += boardSum[row][y2] - boardSum[row][y1];		// 각 행의 총 합 계산
		
			// 결과 저장
			sb.append(sum).append("\n");						// 결과를 스트링 빌더에 저장한다.
		}
		
		System.out.println(sb);										// 이후 출력
	}	// main end
}





