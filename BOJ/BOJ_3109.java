package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 빵집
 * 가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
 * 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결하는 것이다.
 * 
 * 원웅이는 가스를 되도록 많이 훔치려고 한다. 따라서, 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다. 
 * 이 경로는 겹칠 수 없고, 서로 접할 수도 없다. 즉, 각 칸을 지나는 파이프는 하나이어야 한다.
 * 원웅이 빵집의 모습이 주어졌을 때, 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하는 프로그램을 작성하시오.
 * 
 * 1. 첫 줄에 행과 열의 길이가 주어진다.
 * 2. .은 빈 칸이고, x는 건물이다. ( 첫쨰 열과 마지막 열은 항상 비어있다.)
 * 3. 놓을 수 있는 최대 파이프라인의 최대 개수를 출력한다.
 * 
 * 분명 백트래킹인데 어떻게?
 * -> 위쪽부터 채워서 파이프를 지어야 최대한 많이 지을 수 있다.
 * -> 즉, 위쪽 대각선, 수평, 아래 대각선 순으로 길이 있는지 확인한다.
 * -> 길이 있으면 가고, 없으면 백트래킹으로 돌아간다.
 * 
 * 
 */


public class BOJ_3109 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize;						// 행 사이즈
	static int colSize;						// 열 사이즈
	static boolean map[][];					// 맵 -> true는 빈 공간, false는 벽 및 이미 설치된 공간
	static final int dRow[] = { -1, 0, 1 };	// 상 중 하 탐색을 위한 델타 배열
	static int pipeCount;					// 총 파이프 개수
	
	static boolean finish;					// 파이프 설치가 끝났는지 확인하는 변수
	
	private static void findWay(int row, int col) {
		
		// 기저 조건
		// 마지막 열에 도착했으면 설치가 가능한 것이다.
		if(col == colSize - 1) {
			finish = true;		// 설치 완료 플래그 설정
			pipeCount++;		// 파이프 개수 추가	
			return;
		}
		
		// 상 중 하 탐색
		int nextRow;	// 다음 파이프 설치할 행 위치 저장 변수
		for(int idx = 0; idx < dRow.length; idx++) {
			
			nextRow = row + dRow[idx];			// 다음 행을 구한다.
			
			// 해당 맵 범위 내이고 다음 행을 구했으니 해당 행의 다음 열이 비어있는지 확인
			if(nextRow >= 0 && nextRow < rowSize && map[nextRow][col + 1]) {
				// 조건을 통과했으니 파이프를 설치한다.
				map[nextRow][col + 1] = false;
				// 다음 구역 파이프로 이동
				findWay(nextRow, col + 1);
				// 파이프 설치가 끝났으면 종료
				// 어차피 각 행마다 반복문을 돌리는 것이기 때문에 해당 행이 끝나면 리턴하면 된다.
				if(finish)
					return;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// 시작시 초기화 및 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		pipeCount = 0;
		
		// 첫째 줄 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 맵 정보 입력
		String str;								// 각 줄의 입력을 저장해놓을 변수
		map = new boolean[rowSize][colSize];	// 맵 생성
		
		for(int row = 0; row < rowSize; row++) {
			str = br.readLine().trim();
			for(int col = 0; col < colSize; col++) {
				if(str.charAt(col) == '.')
					map[row][col] = true;	// 지나갈 수 있는 길만 true로 표시한다.
			}
		}
		
		// 길 찾기 시작
		for(int idx = 0; idx < rowSize; idx++) {
			finish = false;
			findWay(idx, 0);
		}
		// 결과 출력
		System.out.println(pipeCount);
	}
}