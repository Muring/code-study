package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사칙연산 유효성 검사
 * 
 * 노드의 번호, 연산방식, 왼쪽 노드, 오른쪽 노드 순으로 매 줄 입력받는다.
 * 계산이 가능하다면 1 불가능하다면 0
 * 각 줄을 입력받아서 연산 방식에 숫자가 들어오거나 뒤의 숫자가 두개가 아니거나 단일 입력이 숫자가 아닌 경우 거르면 된다.
 * 
 * @author sehyeon.eom
 *
 */
public class SWEA_1233 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int totalCase = 10; 	// 전체 케이스의 수
	static int answer;					// 정답
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 케이스 수 만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			answer = 1;												// 정답 초기화
			int nodeCount = Integer.parseInt(br.readLine().trim());	// 입력받을 노드의 개수
			
			for(int idx = 0; idx < nodeCount; idx++) {
				st = new StringTokenizer(br.readLine());	// 한 줄 입력받기
				st.nextToken();	// 노드 번호 버리기
				
				// 노드에 노드 번호 다음 입력을 저장
				char node = st.nextToken().charAt(0);
				
				// 뒤에 다른 토큰이 더 있다면 현재 node는 부호를 입력 받은 것이고 뒤에 숫자가 두 개 더 있다는 뜻
				if(st.hasMoreTokens()) {
					// 그러니 현재 노드는 연산자여야 한다. 따라서 해당이 숫자면 answer는 0이다.
					if(node >= '0' && node <= '9')
						answer = 0;
				}
				// 뒤에 다른 토큰이 더 없다면 해당 토큰은 숫자여야 한다.
				else {
					// 해당 토큰이 숫자가 아니라면 answer = 0;
					if(node < 0 && node > 9)
						answer = 0;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}	// tc end
		System.out.println(sb);
	}
}
