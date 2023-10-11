package BOJ;

import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * 카드!
 * 1. 카드의 개수 total을 입력 받는다. 카드 덱은 위에서부터 1번으로 맨 마지막이 total이다.
 * 2. 제일 위의 카드를 버리고 그 밑의 카드를 덱 맨 밑에 넣는다.
 * 3. 카드가 한 장 남을때까지 반복한다.
 * 
 * 큐를 사용하면 쉽게 풀 수 있다.굳이 디큐를 쓸 문제는 아니다.
 *
 */

public class BOJ_2164 {
	
	public static void main(String[] args) {
		Queue<Integer> que = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
		// 전체 카드 수 입력
		int total = sc.nextInt();
		
		// 큐에 카드 삽입
		for(int idx = 1; idx <= total; idx++) {
			que.add(idx);
		}
		
		// 카드가 한 장 남을 때까지 카드 버리기와 옮기기 반복
		while(que.size() != 1) {
			// 맨 위의 카드 버리기
			que.remove();
			
			// 두번째 카드 맨 뒤로 옮기기
			que.add(que.peek());	// 먼저 값을 복사해 맨 뒤에 추가
			que.remove();			// 이후 두번째 카드 삭제
		}
		
		// 결과 출력
		System.out.println(que.peek());
		
		sc.close();
	}	// main end
}	
