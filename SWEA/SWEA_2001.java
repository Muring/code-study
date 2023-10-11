package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 파리퇴치
 * 
 * 1. 파리가 있는 맵을 입력 받는다.
 * 2. 파리채 사이즈를 입력 받는다.
 * 3. 해당 범위 내의 죽은 파리 수를 저장
 * 4. 가장 많은 파리가 죽었을 때의 수를 구하라.
 * 
 * 범위를 탐색해야 한다. 어떻게 할 건가?
 * 파리채 사이즈 만큼의 반복문을 돌려서 위아래로 맵을 탐색해 값을 도출한다. -> 비효율적이지 않을까?
 * 좀 더 효율적인 방법을 찾아보자
 * dp를 활용한 행마다의 열 합으로 해볼까?
 * 그러면 mapSize 만큼의 반복문 안에서 Sum[rowIdx][start+mapSize] - Sum[rowIdx][start]하면 된다.
 * 이게 더 빠를 수도? 아무래도 맵을 받으면서 미리 arrSum에 값을 더해서 저장하기 때문에 반복 횟수가 많이 줄어든다.
 *
 */
public class SWEA_2001 {
	static int mapSize;			// 맵의 사이즈
	static int flapperSize;		// 파리채 사이즈
	static int[][] map;			// 맵
	static int[][] mapSum;		// 맵의 합 저장 배열
	static int maxKill;			// 제일 많이 죽인 파리 수
	
	static BufferedReader br;		// 입력 -> 입력 받는게 많으니까 버퍼드 리더
	static StringBuilder sb;		// 출력
	static StringTokenizer st;		// 띄어쓰기 입력을 받기 위한 토큰
	
	private static void countKill(int startRow, int startCol) {
		int kill = 0;			// 해당 파리채로 죽은 총 파리 수
		for(int row = startRow; row < startRow + flapperSize; row++) {
			kill += mapSum[row][startCol + flapperSize] - mapSum[row][startCol];
		}
		if(kill > maxKill)
			maxKill = kill;
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));	
		sb = new StringBuilder();
		
		// 전체 테스트 케이스 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		// 입력받은 테스트 케이스의 수 만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			maxKill = 0;										// 매 테스트 케이스마다 최대 킬 수 초기화
			st = new StringTokenizer(br.readLine().trim());		// 줄 받아서 띄어쓰기 구분
			mapSize = Integer.parseInt(st.nextToken());			// 맵 사이즈 입력
			flapperSize = Integer.parseInt(st.nextToken());		// 파리채 사이즈 입력
			
			// 맵을 입력 받으면서 mapSum도 저장한다.
			// 맵을 입력 받기 위한 맵 선언
			map = new int[mapSize][mapSize];
			mapSum = new int[mapSize][mapSize + 1];						// 빼기 할 때, 한 칸 더 앞의 부분을 빼야 하는데 0번째 인덱스일 경우 터지기 때문에 앞에 빈 공간을 만든다.
			
			for(int row = 0; row < mapSize; row++) {
				// 행마다 초기화 및 선언해줘야 하는 것들
				st = new StringTokenizer(br.readLine().trim());			// 맵 한줄 입력
				int sum = 0;											// mapSum에 저장하기 위한 sum을 저장하는 변수
				
				for(int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());	// 맵 정보 저장
					sum += map[row][col];
					mapSum[row][col + 1] = sum;							// 총합 맵에 저장
				}
			}	// map input end
			
			// 이제 맵을 돌면서 총 합을 구해야 한다.
			// 파리채 크기만큼 뺀 행까지만 반복을 돌린다.
			
			for(int row = 0; row <= mapSize - flapperSize; row++) {
				for(int col = 0; col <= mapSize - flapperSize; col++) {
					countKill(row, col);
				}
			}
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(maxKill).append("\n");
		}	// tc end
		System.out.println(sb); //결과 출력
	}	// main end
}
