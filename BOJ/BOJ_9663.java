package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author sehyeon.eom
 * 
 * N x N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * 
 * 1. 첫째 줄에 N이 주어진다.
 * 2. 이후 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 * 
 * 백트래킹 기법을 사용한다.
 * 퀸을 놓을 자리가 없어지면 전에 퀸을 놓았던 자리로 돌아가 그 다음 순서부터 다시 퀸을 놓을 수 있는지 없는지 확인한다.
 *
 */
public class BOJ_9663 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int mapSize;			// 체스판 한 변의 길이
	static int colArr[];		// 해당 열에 있는 퀸이 몇 번째 행에 있는지 저장하는 배열
	static boolean visited[];	// 퀸이 해당 열에 있는지 확인하는 배열
	static int ans;				// 정답 수
	
	private static void setQueen(int row) {	// 다음 행으로 넘어가야하기 때문에 정보를 받는다.
		
		// 종료 조건
		if(row == mapSize) {
			ans++;
			return;
		}
		
		// 모든 조건을 통과했을 경우
		for(int idx = 0; idx < mapSize; idx++) {
			// 현재 열에 퀸이 없고, 대각선 검사가 통과했을 때
			if(!visited[idx] && isPossible(row, idx)) {
				visited[idx] = true;	// 방문 확인
				colArr[row] = idx;			// 해당 행의 몇 열에 퀸이 있는지 저장
				setQueen(row + 1);		// 재귀
				visited[idx] = false;	// 돌아오면 미방문으로 처리
			}
			
		}
	}
	
	// 대각선 검사 메서드
	// 현재 위치가 해당 열 위의 모든 퀸과 비교했을 때 대각선으로 안전한 위치에 있는지 확인한다.
	private static boolean isPossible(int row, int col) {
		// 현재 행 전의 모든 행을 검사
		for(int idx = 0; idx < row; idx++) {
			// 비교하는 두 수의 행끼리의 차의 절대값이 열끼리의 차의 절대값과 같으면 같은 대각선에 있다는 것이다.
			// 동시에 두 개의 대각선을 전부 계산한다.
			if(Math.abs(row - idx) == Math.abs(col - colArr[idx])) 
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 체스판 사이즈 입력
		mapSize = Integer.parseInt(br.readLine().trim());
		visited = new boolean[mapSize];	// 체스판의 크기만큼 확인할 배열 생성
		colArr = new int[mapSize];		// 체스판의 크기만큼 열 배열 생성
		
		// 퀸을 놓을 수 있는 경우의 수 계산
		if(mapSize == 1) ans = 1;		// 맵 사이즈가 1이면 한가지 경우만 가능
		else if(mapSize < 4) ans = 0;	// 맵 사이즈가 2부터 4면 가능한 경우의 수가 없음
		else {
			setQueen(0);
		}

		sb.append(ans);
		System.out.println(sb);
	}
}
