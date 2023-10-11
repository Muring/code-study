package algorithms;

import java.util.Scanner;

public class NQueenTest1 {
	
	static int mapSize;
	static int col[];
	static int ans;
	
	// 해당 퀸을 현재 행에 가능한 모든 곳에 놓아보기
	private static void setQueen(int row) {
		
		// 기저 조건
		// 마지막 행에 도달했을 때
		if(row == mapSize) {
			ans++;
			return;
		}
		
		// 유도 파트
		// 가지치기
		for(int c = 1; c <= mapSize; c++) {	// 1부터 mapSize열까지 시도
			
			setQueen(row + 1);			
		}
		
		
	}
	
	// 대각선을 비교할 떄는 각 대각선의 행과 열의 합과 차가 모두 같다.
	// 이 대각선 행과 열의 합을 인덱스로하는 배열을 생성하여 사용하면 대각선 내에 해당 퀸이 존재하는지 안하는지 확인 가능
	private static boolean isAvailable(int row) {	// row: 마지막으로 놓아진 퀸의 행
		for(int i = 1; i < row; i++) {
			// 유망하지 않은 상황
			if(col[i] == col[row] || row - i == Math.abs(col[row] - col[i])) {
				return false;
			}
		}
		// 위의 틀린 조건을 전부 충족했다는 의미이므로 true를 리턴한다.
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		mapSize = sc.nextInt();
		
		col = new int[mapSize + 1];	// 1행부터 사용
		ans = 0;
		
		setQueen(1);
		System.out.println(ans);
		
		sc.close();
	}
}
