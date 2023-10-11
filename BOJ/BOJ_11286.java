package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 절댓값 힙
 * 
 * 첫째 줄에는 연산의 개수 total이 주어진다.
 * 이후 연산에 대한 정보를 나타내는 정수 num이 주어진다.
 * 
 * num이 0이 아니라면 배열에 해당 숫자를 저장하고
 * num이 0이라면 현재 배열에서 절댓값이 가장 작은 값을 출력한다.
 * 이때, 절댓값이 같다면 음수부터 출력한다.
 * 
 * @author SSAFY
 *
 */
public class BOJ_11286 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int total;	
	static List<Integer> nums;		// 숫자 입력되는 배열
	static final int MAX_VALUE = Integer.MAX_VALUE;
	static PriorityQueue<Integer> NegMinHeap;
	static PriorityQueue<Integer> PosMinHeap;
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 연산 개수 입력
		total = Integer.parseInt(br.readLine().trim());
		
		// 연산을 위한 우선순위 큐를 생성한다.
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// 절대값 기준으로 앞 값이 더 크다면 자리를 바꿔준다.
				if(Math.abs(o1) > Math.abs(o2))
					return Math.abs(o1) - Math.abs(o2);
				// 절대값 기준으로 값이 같다면 음수를 앞으로 보내준다.
				else if(Math.abs(o1) == Math.abs(o2))
					return o1 - o2;
				else
					return -1;
			}
		});
		
		// 연산 횟수만큼 반복
		for(int calIdx = 0; calIdx < total; calIdx++) {
			int num = Integer.parseInt(br.readLine().trim());
			
			// 입력 받은 숫자가 0이면
			if(num == 0) {
				// 큐가 비어있을 경우
				if(pq.isEmpty())
					sb.append("0").append("\n");	// 그냥 0을 출력
				// 큐가 비어있지 않을 경우
				else
					sb.append(pq.poll()).append("\n");	// 큐의 가장 윗 값을 출력
			}
			// 입력 받은 숫자가 0이 아니라면
			else
				// 숫자 큐에 저장
				pq.offer(num);
			
		}	// calculation end
		System.out.println(sb);
		
	}	// main end
}
