package BOJ;
/**
 * 요세푸스 순열
 * 
 * 사람 수와 몇번 째 사람을 순차적으로 제거할건지 입력 받는다. total, range
 * 예를 들어, 5 2 를 입력 받는다면 2, 4, 1, 3, 5 순으로 삭제된다.
 * 
 * 링크드 리스트를 사용해보자
 * 입력 받은 range에 도달할 떄까지 만나는 수는 전부 뽑아 맨 뒤로 보낸다.
 * range에 도달하면 단순 삭제
 * 모두 삭제될 때까지 반복한다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1158 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		LinkedList<Integer> list = new LinkedList<>();
		
		// 전체 명 수와 범위 입력
		st = new StringTokenizer(br.readLine());
		int total = Integer.parseInt(st.nextToken());
		int range = Integer.parseInt(st.nextToken());
		
		// 리스트에 저장
		for(int idx = 1; idx <= total; idx++)
			list.add(idx);
		
		sb.append("<");
		// 리스트 검사하면서 해당 번째의 리스트 삭제
		while(!list.isEmpty()) {
			// 제거되야 하는 순번의 사람만 제거
			// 그 전의 사람들은 전부 뽑아서 뒤로 넘긴다.
			for(int idx = 0; idx < range; idx++	) {
				// 죽어야 하는 사람의 순번일 경우
				if(idx == range - 1) {
					if(list.size() == 1)
						sb.append(list.poll());
					else
						sb.append(list.poll()).append(", ");
				}
				else {
					list.add(list.poll());
				}
			}
		}	// while end
		sb.append(">");
		System.out.println(sb);
	}	// main end
}
