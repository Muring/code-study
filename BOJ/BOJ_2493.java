package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 각 탑에서 왼쪽으로 신호를 전송했을 때, 해당 탑보다 높은 탑에서 신호를 수신한다.
 * 처음에 입력되는 타워의 정보를 타워 객체에 각각 타워의 높이와 인덱스로 받아 저장한다.
 * 다음에 입력되는 타워들의 높이와 앞에 있는 타워의 높이를 서로 비교하여 각각에 맞는 처리를 한다.
 * 
 * 첫번째 줄에 탑의 개수가 주어진다.
 * 둘째 줄에 각 탑의 높이가 주어진다.
 * 
 * 시간 초과를 막기 위하여 배열에 따로 저장한 값을 불러오는 것이 아닌 값을 받자마자 스택과 비교하고 결과를 도출한다.
 */
public class BOJ_2493 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int towerCount;		// 전체 타워 수
	static Stack<Tower> towers;	// 타워 정보를 저장할 스택
	
	// 타워 객체
	static class Tower {
		int height;		// 높이
		int index;		// 타워의 인덱스
		
		Tower(int height, int index){	// 파라미터 생성자
			this.height = height;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		towers = new Stack<>();
		
		// 타워 개수 입력
		towerCount = Integer.parseInt(br.readLine().trim());
		
		// 타워들의 높이 입력
		// 띄어쓰기 입력이기 때문에 스트링 토큰 사용
		st = new StringTokenizer(br.readLine());
		
		for(int towerIdx = 1; towerIdx <= towerCount; towerIdx++) {
			// 현 타워 인덱스의 타워 높이
			int currentTowerHeight = Integer.parseInt(st.nextToken());
			while(true) {
				// 스택이 비어있을 경우 0을 결과에 저장한다.
				if(towers.isEmpty()) {
					sb.append(0).append(" ");	// 0 저장
				}
				// 가장 뒤쪽의 타워 높이와 현재 타워 높이를 비교한다.
				// 현재 타워의 높이가 앞의 타워보다 클 경우 pop을 통해 앞의 타워를 제거
				// continue를 통해 인덱스를 늘리지 않고 해당 타워를 그 다음 앞에 있는 타워와 비교하게 한다.
				else if(towers.peek().height < currentTowerHeight) {
					towers.pop();
					continue;
				}
				// 현재 타워의 높이가 앞의 타워보다 낮으면 앞의 타워의 위치를 저장하고
				// 현재 타워를 push해 저장한다.
				else if(towers.peek().height >= currentTowerHeight) {
					sb.append(towers.peek().index).append(" ");	// 앞의 타워 위치 저장
				}
				// 타워 객체를 스택에 저장한다.
				towers.push(new Tower(currentTowerHeight, towerIdx));
				break;
			}	// while end
			
		}
		// 결과 출력
		System.out.println(sb);
		towers.clear();
	}	// main end
}
