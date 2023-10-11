package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 영식이와 친구들
 * 
 * 원형으로 모여서 시계방향으로 1부터 N까지 적혀있는 자리에 앉는다.
 * 일단 1번 자리에 앉은 사람이 공을 받는다. 이후 공을 다른 사람에게 던진다.
 * 공을 받은 사람은 다시 공을 던지는 행위를 반복한다.
 * 한 사람이 공을 M번 받으면 게임 종료이다.(지금 받은 공 포함)
 * 공을 M번보다 적게 받은 사람이 고을 던질 때, 
 * 	현재 공을 받은 횟수가 홀수번이면 자신의 위치에서 시계 방향으로 L번째 사람에게,
 * 	짝수번이면 자기 위치에서 반시계 방향으로 L번째 사람에게 던진다.
 * 공을 총 몊 번 던지는지 구하시오.
 * 
 * [입력]
 * 첫째 줄에 N, M, L이 주어진다.
 * @author sehyeon.eom
 *
 */
public class BOJ_1592 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int seatCount;			// 좌석 수
	static int maxBallReceiveCount;	// 공을 받을 수 있는 횟수
	static int throwBallLen;		// 한 번 던질 때 공을 던지는 길이
	static int[] gameStatus;		// 공을 받은 횟수 저장 배열
	
	// 공을 받은 횟수가 홀수번이면 시계방향으로 throwBallLen만큼, 짝수면 반시계 방향
	// 앉은 방향도 시계방향이기 때문에, 번호순이다.
	private static void gameStart() {
		int seatIdx = 0;
		int time = 0;
		gameStatus[seatIdx] = 1;
		
		while(true) {
			// 공을 받은 횟수가 홀수 - 시계방향 던지기
			if(gameStatus[seatIdx] % 2 != 0) {
				seatIdx = (seatIdx + throwBallLen) % seatCount;
				gameStatus[seatIdx]++;
				time++;
			}
			// 짝수
			else {
				// 반시계로 돌렸을 때 0을 지나면
				if(seatIdx - throwBallLen < 0)
					seatIdx = seatCount - Math.abs(seatIdx - throwBallLen);
				else
					seatIdx -= throwBallLen;
				gameStatus[seatIdx]++;
				time++;
			}


			if(gameStatus[seatIdx] == maxBallReceiveCount) {
				System.out.println(time);
				break;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		seatCount = Integer.parseInt(st.nextToken());
		maxBallReceiveCount = Integer.parseInt(st.nextToken());
		throwBallLen = Integer.parseInt(st.nextToken());
		
		gameStatus = new int[seatCount];
		if(maxBallReceiveCount == 1)
			System.out.println(0);
		else
			gameStart();
		
	}	// main end
	
}
