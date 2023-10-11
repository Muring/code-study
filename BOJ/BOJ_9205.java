package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 
 * @author sehyeon.eom
 * 
 * 맥주 마시면서 걸어가기
 * 
 * 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 
 * 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 
 * 맥주 한 박스에는 맥주가 20개 들어있다. 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 
 * 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
 * 상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 따라서, 맥주를 더 구매해야 할 수도 있다. 
 * 미리 인터넷으로 조사를 해보니 다행히도 맥주를 파는 편의점이 있다. 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 
 * 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 * 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다. 
 * 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
 * 각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
 * 다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 
 * 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
 * 송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)
 * 
 * [ 출력 ]
 * 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다. 
 *
 */
public class BOJ_9205 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int storeCount;
	static List<Point> locationList;
	static boolean[][] isLinked;
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int distance(Point current, Point destination) {
		return Math.abs(current.row - destination.row) + Math.abs(current.col - destination.col);
	}
	
	private static void floyd() {
		// 연결성이 저장된 배열을 통해 시작 지점부터 스타디움까지 연결되어있는지 확인한다.
		for(int currentIdx = 0; currentIdx < storeCount + 2; currentIdx++) {
			for(int row = 0; row < storeCount + 2; row++) {
				for(int col = 0; col < storeCount + 2; col++) {
					if(isLinked[row][currentIdx] && isLinked[currentIdx][col])
						isLinked[row][col] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 0; tc < totalCase; tc++) {
			// 편의점의 개수를 입력받는다.
			storeCount = Integer.parseInt(br.readLine().trim());
			
			// 상근이의 집 좌표, 가게 좌표, 스타디움 좌표가 순서대로 주어진다.
			locationList = new ArrayList<>();
			for(int idx = 0; idx < storeCount + 2; idx++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				locationList.add(new Point(row, col));
			}
			
			// 모든 입력이 끝났으니 이제 출발하자.
			// 맥주 한 병당 50미터를 갈 수 있다.
			// 우선 20병을 들고 시작한다.
			// 현재 거리에서 경기장에 도착할 수 있는지 확인한다.
			// 도착할 수 없다면 가장 가까운 편의점을 찾고 해당 편의점이 거리 1000이내인지 확인한다.
			// 이조차 경우가 없다면 sad 출력
			// 도착하면 happy 출력
			
			// 각 지점들의 연결 여부를 저장하는 배열
			isLinked = new boolean[storeCount + 2][storeCount + 2];
			// 각 지점들의 연결 여부를 저장한다.
			for(int row = 0; row < storeCount + 2; row++) {
				for(int col = 0; col < storeCount + 2; col++) {
					if(distance(locationList.get(row), locationList.get(col)) <= 1000) {
						isLinked[row][col] = isLinked[col][row] = true;
					}
				}
			}
			
			// 플로이드 와샬 알고리즘
			floyd();
			
			sb.append((isLinked[0][storeCount + 1] ? "happy" : "sad")).append("\n");
			
		}	// tc end

		System.out.println(sb);
	}
}
