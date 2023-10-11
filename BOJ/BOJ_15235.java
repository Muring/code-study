package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15235 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Person {
		int number;				// 처음 먹는 순서
		int requiredPizzaCnt;	// 배부르게 먹기위해 필요한 피자 수
		
		public Person(int number, int requiredPizzaCnt) {
			super();
			this.number = number;
			this.requiredPizzaCnt = requiredPizzaCnt;
		}
	}
	
	static int totalCount;		// 전체 인원 수
	static Queue<Person> que;	// 각 사람의 정보를 담기 위한 큐
	static int[] answer;
	
	private static void eatPizza() {
		int time = 0;	// 피자를 다 먹기위해 요구되는 시간
		
		while(!que.isEmpty()) {
			Person currentPerson = que.poll();
			time++;
			
			currentPerson.requiredPizzaCnt--;

			// 아직 배부르지 않을 때
			if(currentPerson.requiredPizzaCnt != 0) {
				que.offer(currentPerson);
				continue;
			}
			
			// 요구되는 피자를 다 먹어서 배부른 상태가 됐을 때는 결과 배열에 순서대로 넣어준다.
			answer[currentPerson.number] = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		totalCount = Integer.parseInt(br.readLine().trim());
		
		que = new LinkedList<>();
		answer = new int[totalCount];
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < totalCount; idx++	) {
			int temp = Integer.parseInt(st.nextToken());
			que.offer(new Person(idx, temp));
		}
		
		eatPizza();
		
		for(int idx = 0; idx < totalCount; idx++) {
			sb.append(answer[idx]).append(" ");
		}
		System.out.println(sb);
	}
}
