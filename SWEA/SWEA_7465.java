package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 창용 마을 무리의 개수
 * 
 * 창용 마을에는 N명의 사람이 살고 있다.
 * 사람은 편의상 1번부터 N번 사람까지 번호가 붙어져 있다고 가정한다.
 * 두 사람은 서로를 알고 있는 관계일 수 있고, 아닐 수 있다.
 * 두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면,
 * 이러한 사람들을 모두 다 묶어서 하나의 무리라고 한다.
 * 창용 마을에 몇 개의 무리가 존재하는지 계산하는 프로그램을 작성하라.
 * 
 * [입력]
 * 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
 * 각 테스트 케이스의 첫 번째 줄에는 각각 창용 마을에 사는 사람의 수와 서로를 알고 있는 사람의 관계 수를 나타내는
 * 두 정수 N, M(1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2) 이 공백 하나로 구분되어 주어진다.
 * 이후 M개의 줄에 걸쳐서 서로를 알고 있는 두 사람의 번호가 주어진다.
 * 같은 관계는 반복해서 주어지지 않는다.
 * 
 * [출력]
 * 각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
 * 무리의 개수를 출력한다.
 * 
 * [풀이]
 * 1. 전체 테스트 케이스 수 입력
 * 2. 마을에 사는 사람 수와 서로 알고있는 사람의 관계 수를 입력받는다.
 * 3. 사람의 관계 정보가 주어진다.
 * 4. 관계의 수를 구하여 무리의 개수를 구한다.
 * 	4-1. union find를 사용한다.
 * 	4-2. 간선을 조회하여 from과 to 중에 더 낮은 값으로 그룹핑한다.
 * 	4-3. 최종적으로 자기 자신이 부모인 개수가 그룹의 개수이다.
 * 5. 결과 출력
 *
 */

public class SWEA_7465 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int villagerCnt;
	static int relationCnt;
	static int[] parent;
	static int answer;

	private static void unionParent(int from, int to) {
		// 각각의 부모를 구한다.
		from = getParent(from);
		to = getParent(to);

		// from과 to 중 더 낮은 값으로 그룹핑
		if (from < to) {
			parent[to] = from;
		} else
			parent[from] = to;
	}

	private static int getParent(int num) {
		// 기저 조건
		// 만약 자기 자신이면 최상단 부모라는 의미이므로 종료
		if (parent[num] == num)
			return num;

		return parent[num] = getParent(parent[num]);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int totalCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= totalCase; tc++) {
			// 매 테스트 케이스마다 초기화
			answer = 0;

			st = new StringTokenizer(br.readLine());
			villagerCnt = Integer.parseInt(st.nextToken());
			relationCnt = Integer.parseInt(st.nextToken());

			// parent 배열 초기화
			parent = new int[villagerCnt + 1];
			for (int idx = 1; idx <= villagerCnt; idx++) {
				parent[idx] = idx;
			}

			// 관계 정보 입력
			for (int idx = 0; idx < relationCnt; idx++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 관계 정리
				unionParent(from, to);
			}

			// 관계 수 구하기
			for (int idx = 1; idx <= villagerCnt; idx++) {
				if (parent[idx] == idx)
					answer++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");

		} // tc end
		System.out.println(sb);
	} // main end
}
