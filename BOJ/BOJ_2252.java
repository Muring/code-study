package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * 줄 세우기
 * N명의 학생들을 키 순서대로 줄을 세우려고 한다.
 * 두 학생의 키를 비교하는 방법을 사용하기로 했다.
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오
 * 
 * 1. 첫째 줄에 학생 수와 키를 비교한 횟수가 주어진다.
 * 2. 둘째 줄 부터는 키를 비교한 두 학생의 번호 A B가 주어진다. 이는 학생 A가 B의 앞에 서야한다는 의미
 * 3. 학생들의 번호는 1번부터 N번이다.
 * 4. 학생들을 앞에서부텆 줄을 세운 결과를 출력한다. 답이 여러가지인 경우에는 아무거나 출력한다.
 * 
 * 시간 초과가 터지네?
 * 위상 정렬을 사용해야하는 문제였다.
 * 위상 정렬의 순서는 다음과 같다.
 * -> 진입 차수 저장 배열 및 그래프 생성
 * -> 입력 받는 조건들에 따라 그래프에 저장
 * ----------------------------
 * -> 이후 진입 차수가 0인 숫자들을 큐에 저장
 * -> 큐에 저장된 값을 peek해 해당 값과 연결된 값들을 큐에 저장하고 큐에서 빼낸 값은 sb에 저장
 * ----------------------------
 * -> 위 과정을 큐가 빌 때까지 반복
 *
 */

public class BOJ_2252 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int totalStudent;	// 전체 학생 수
	static int compareCount;	// 키 비교 횟수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 학생 수와 비교 횟수 입력
		st = new StringTokenizer(br.readLine());
		totalStudent = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());

		// 비교 시작
		// 위상 정렬에 사용할 2차원 리스트 구현
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		
		// 전체 학생수 만큼 만들어야하는데 학생 번호를 인덱스로 사용할 것이기 때문에 +1 해준다.
		for(int idx = 0; idx <= totalStudent + 1; idx++)	
			graph.add(new ArrayList<>());
		
		int degreeCount[] = new int[totalStudent + 1];	// 해당 수의 진입 차수 저장 배열
		Queue<Integer> que = new LinkedList<>();		// 위상 정렬에 사용할 큐
		
		
		// 값을 입력받고 그래프와 차수 저장
		int A, B;	// 입력 받는 두 숫자
		for(int idx = 0; idx < compareCount; idx++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			graph.get(A).add(B);
			degreeCount[B]++;
			
		}	// for end
		
		// 진입 차수가 0인 숫자들을 큐에 저장
		for(int idx = 1; idx < degreeCount.length; idx++) {
			if(degreeCount[idx] == 0)
				que.offer(idx);
		}
		
		// 큐가 빌때가지 과정 반복
		while(!que.isEmpty()) {
			// 큐에서 학생 번호 꺼내기
			int studentNo = que.poll();
			
			// 꺼낸 학생 번호 출력값에 저장
			sb.append(studentNo).append(" ");
			
			// 해당 번호와 연결된 노드들의 정보 가져오기
			List<Integer> list = graph.get(studentNo);
			
			// 해당 번호와 연결된 노드 개수만큼 반복문 실행
			for(int idx = 0; idx < list.size(); idx++) {
				int temp = list.get(idx);
				degreeCount[temp]--;
				// 진입 차수가 0이되면 큐에 저장
				if(degreeCount[temp] == 0)
					que.offer(temp);
			}
		}

		System.out.println(sb);
	}
}

