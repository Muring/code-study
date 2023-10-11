package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 특이한 자석
 * [제약사항]
 * 1. 시간제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초
 * 2. 자석의 개수는 4 개이며, 각 자석은 8 개의 날을 가지고 있다.
 * 3. 자석을 회전시키는 횟수 K 는 1 이상 20 이하의 정수이다. ( 1 ≤ K ≤ 20 )
 * 4. 하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1 칸 회전된다.
 * 5. 자석을 회전시키는 방향은 시계방향이 1 로, 반시계 방향이 -1 로 주어진다.
 * 6. 날의 자성은 N 극이 0 으로, S 극이 1 로 주어진다.
 * 7. 각 자석의 날 자성정보는 빨간색 화살표 위치의 날부터 시계방향 순서대로 주어진다.
 *    예를 들어, [Fig. 1] 의 1 번 자석의 자성정보는 0 0 1 0 0 1 0 0 과 같이 주어진다.
 * 
 * [입력]
 * 입력의 맨 첫 줄에는 총 테스트 케이스의 개수 T 가 주어지고,
 * 그 다음 줄부터 T 개의 테스트 케이스가 주어진다.
 * 각 테스트 케이스의 첫 번째 줄에는 자석을 회전시키는 횟수 K 가 주어진다.
 * 다음 4 개의 줄에는 1 번 자석부터 4 번 자석까지 각각 8 개 날의 자성정보가 차례대로 주어진다.
 * 그 다음 K 개의 줄에는 자석을 1 칸씩 회전시키는 회전 정보가 주어진다.
 * 자석의 회전 정보는 회전시키려는 자석의 번호, 회전방향으로 구성되어 있다.
 * 회전방향은 1 일 경우 시계방향이며, -1 일 경우 반시계방향이다.
 * 
 * [출력]
 * 테스트 케이스 개수만큼 T 개의 줄에 각각의 테스트 케이스에 대한 답을 출력한다.
 * 각 줄은 "#t" 로 시작하고 공백을 하나 둔 다음 정답을 출력한다. ( t는 1부터 시작하는 테스트 케이스의 번호이다. )
 * 정답은 모든 자석의 회전이 끝난 후 획득한 점수의 총 합이다.
 * 
 * [풀이]
 * 단순 구현
 *
 */

public class SWEA_4013 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int magneticRotationCnt;
	static int[][] gear; // 톱니 번호, 톱니 이빨 번호
	static int[] rotation;

	static final int GEAR_COUNT = 4;
	static final int TOOTH_COUNT = 8;
	static final int CLOCKWISE = 1;
	static final int COUNTER_CLOCKWISE = -1;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int totalCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= totalCase; tc++) {
			magneticRotationCnt = Integer.parseInt(br.readLine());

			gear = new int[GEAR_COUNT][TOOTH_COUNT];
			for (int gearIdx = 0; gearIdx < GEAR_COUNT; gearIdx++) {
				st = new StringTokenizer(br.readLine());
				for (int toothIdx = 0; toothIdx < TOOTH_COUNT; toothIdx++) {
					gear[gearIdx][toothIdx] = Integer.parseInt(st.nextToken());
				}
			}

			// 톱니 회전 명령
			for (int idx = 0; idx < magneticRotationCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int gearNum = Integer.parseInt(st.nextToken()) - 1;
				int rotateDir = Integer.parseInt(st.nextToken());

				// 명령 저장
				rotation = new int[GEAR_COUNT];
				rotation[gearNum] = rotateDir;

				// 오른쪽 자석들 검사
				for (int rightGearIdx = gearNum + 1; rightGearIdx < GEAR_COUNT; rightGearIdx++) {
					// 자성이 같으면 회전하지 않는다.
					if (gear[rightGearIdx - 1][2] == gear[rightGearIdx][6])
						break;
					else
						rotation[rightGearIdx] = -rotation[rightGearIdx - 1];
				}

				// 왼쪽 자석들 검사
				for (int leftGearIdx = gearNum - 1; leftGearIdx >= 0; leftGearIdx--) {
					if (gear[leftGearIdx][2] == gear[leftGearIdx + 1][6])
						break;
					else
						rotation[leftGearIdx] = -rotation[leftGearIdx + 1];
				}

				// 각 자석을 회전시킨다.
				for (int gearIdx = 0; gearIdx < GEAR_COUNT; gearIdx++) {
					if (rotation[gearIdx] == 0)
						continue;
					// 시계방향
					else if (rotation[gearIdx] == CLOCKWISE) {
						// 단순 치환
						int temp = gear[gearIdx][7];
						for (int toothIdx = TOOTH_COUNT - 1; toothIdx > 0; toothIdx--) {
							gear[gearIdx][toothIdx] = gear[gearIdx][toothIdx - 1];
						}
						gear[gearIdx][0] = temp;
					}
					// 반시계방향
					else if (rotation[gearIdx] == COUNTER_CLOCKWISE) {
						int temp = gear[gearIdx][0];
						for (int toothIdx = 0; toothIdx < TOOTH_COUNT - 1; toothIdx++) {
							gear[gearIdx][toothIdx] = gear[gearIdx][toothIdx + 1];
						}
						gear[gearIdx][TOOTH_COUNT - 1] = temp;
					}
				}

			}

			// 모든 실행이 끝난 후 총합을 계산
			int sum = 0;
			for (int idx = 0; idx < GEAR_COUNT; idx++) {
				if (gear[idx][0] == 1) {
					// 톱니의 수가 2의 배수이기 때문에 비트연산으로 계산
					sum += 1 << idx;
				}
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}// main end
}
