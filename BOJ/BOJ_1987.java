package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 알파벳
 * 
 * 가로 R, 세로 C칸으로 된 보드가 있다.
 * 각 칸에는 대문자 알파벳이 하나씩 적혀있고, 1행 1열에는 말이 놓여있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한칸으로 이동할 수 있다.
 * 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 최대 몇 칸을 지날 수 있는가?
 * 
 * 1. 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다.
 * 2. 이후 대문자 알파벳들이 공백없이 주어진다.
 * 
 * 해당 알파벳이 쓰였는지 확인할 boolean배열을 하나 만든다.
 * -> 해당 boolean배열이 쓰였는지 확인하기 위해서 알파벳의 아스키코드인 65를 빼줘 배열의 인덱스로 지정한다.
 * 델타 인덱스를 활용해서 상하좌우로 이동을 하게 한다.
 * -> 이때, 맵의 범위 안인지, 다음 문자가 이미 쓰였는지 확인하고 조건을 통과했을 경우에만 이동한다.
 * -> 먼저 맵의 범위 안인지 확인시켜 불필요한 에러를 피한다.
 *
 */
public class BOJ_1987 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static char[][] board;				// 보드 판
	static boolean[] alphabetUsed;		// 알파벳이 사용되었는지 안되었는지 확인하는 배열
	static int maxCount;				// 최대 값 저장 변수
	static int rowSize;					// 보드 행 크기
	static int colSize;					// 보드 열 크기
	
	// 상 하 좌 우
	static final int dRow[] = { -1, 1, 0, 0 };
	static final int dCol[] = { 0, 0, -1, 1 };
	
	// 말을 이동시키자.
	private static void move(int row, int col, int count) {
		int nextRow;	// 다음 행
		int nextCol;	// 다음 열
		
		// 현재 보드 값의 알파벳이 이미 쓰인 경우 도달할 수 있는 끝에 도착한 것이므로
		// 현재까지 센 count와 maxCount를 비교하고 결과 반환
		if(!isAvailable(row, col)) {
			if(count > maxCount)
				maxCount = count;
			return;
		}
		
		alphabetUsed[board[row][col] - 65] = true;	// 현재 말의 위치의 알파벳을 사용했다고 처리
		
		// 말을 이동시킨다.
		for(int idx = 0; idx < dRow.length; idx++) {
			nextRow = row + dRow[idx];
			nextCol = col + dCol[idx];
			// 다음 범위가 맵의 범위 안이라면
			if(nextRow >= 0 && nextCol >= 0 && nextRow < rowSize && nextCol < colSize) {
				move(nextRow, nextCol, count + 1);			// 이후 다음 위치로 넘어가고 count 추가
			}
		}
		alphabetUsed[board[row][col] - 65] = false;	// 나왔을 떄는 알파벳 미사용 처리
		
	}
	
	// 현재 위치의 알파벳이 사용되었는지 사용되지 않았는지 확인한다.
	private static boolean isAvailable(int row, int col) {
		if(alphabetUsed[board[row][col] - 65])
			return false;
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// 메인 시작 후 즉시 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 행과 열 입력
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 보드 정보 입력
		String str;							// 입력받을 한 줄 정보 임시 저장 변수
		board = new char[rowSize][colSize];	// 보드 생성
		for(int row = 0; row < rowSize; row++) {
			str = br.readLine().trim();
			for(int col = 0; col < colSize; col++) {
				board[row][col] = str.charAt(col);	// 붙어있는 스트링을 한칸씩 쪼개 char로 저장
			}
		}
		
		// 말의 이동 시작
		maxCount = 0;	// 말이 지날 수 있는 칸 수 초가화
		alphabetUsed = new boolean[26];	// 알파벳이 쓰였는지 확인할 배열 생성
		move(0, 0, 0);	// (1,1)도 세야하기 때문에 미리 1 추가하고 시작한다.
		
		sb.append(maxCount);
		System.out.println(sb);
	}
}
