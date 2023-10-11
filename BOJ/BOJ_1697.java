package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * 숨바꼭질
 * 
 * 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동한다.
 * 순간이동하는 경우에는 1초 후에 2*X의 위치로 이동한다.
 * 수빈이와 동생의 위치가 구해졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하시오.
 * 
 * 1. 첫째 줄에 수빈이가 있는 위치X와 동생의 위치K가 주어진다.
 * 2. 동생을 찾는 가장 빠른 시간을 출력한다.
 * 
 * 순간이동은 현재 수빈이의 위치인 X*2만큼씩 이동한다.
 * -> 이동한 위치에 따라 순간이동 값이 달라진다.ㅇㄱ
 * 처음 주어진 값에서 최대한 순간이동해서 이동하고 나머지는 걷는다.
 * 
 * BFS를 사용하여 풀면 된다.
 * BFS를 활용한 문제라고는 생각하지도 못했다.
 * 좀 더 여러 문제를 풀면서 경험을 습득해야하겠다.
 *
 */

public class BOJ_1697 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int start;	// 수빈이
	static int end;		// 동생
	static int[] check = new int[100001];	// 수빈이가 동생 찾아가는 동안 걸리는 횟수 저장 배열
	
	private static void bfs(int current) {
		Queue<Integer> que = new LinkedList<>();	// bfs를 위한 큐 생성
		que.add(current);	// 현재 위치를 큐에 넣는다.
		check[current] = 1;	// 그리고 수빈이의 현재 위치 카운트
		
		// 큐가 전부 비거나 탈출이 되어야 과정이 끝난 것이다.
		while(!que.isEmpty()) {
			int temp = que.poll();	// 우선 임시로 temp에 큐의 값을 받는다.
			
			for(int idx = 0; idx < 3; idx++) {
				int next = 0;	// 다음 값 임시 저장 변수
				
				// 각 인덱스를 돌면서 모든 경우의 수를 구한다.
				// 해당 경우마다의 횟수를 check 배열에 저장
				if(idx == 0) {
					next = temp + 1;
				}
				else if(idx == 1) {
					next = temp - 1;
				}
				else if(idx == 2) {
					next = temp * 2;
				}
				
				// 기저 조건
				// 만약에 수빈이가 동생에게 도착했다면 종료한다.
				if(next == end) {
					sb.append(check[temp]);
					return;
				}
				
				// 도착하지 못했고, 아직 길의 범위 안에 있으며 해당 check 배열에 도달한적이 없다면
				if(next >= 0 && next < check.length && check[next] == 0) {
					que.add(next);	// 큐에 다음 값을 저장한다.
					check[next] = check[temp] + 1;	// 그리고 해당 숫자에 해당하는 check 배열에 여태까지의 걸린 횟수 + 1을 저장한다. -> 숫자 누적
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		start = Integer.parseInt(st.nextToken());	// 수빈이의 위치
		end = Integer.parseInt(st.nextToken());		// 동생의 위치
		
		// 만약에 입력된 값이 같다면 즉시 종료
		if(start == end)
			sb.append(0);
		else
			bfs(start);

		System.out.println(sb);
	}	// main end
}
