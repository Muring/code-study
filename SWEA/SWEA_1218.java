package SWEA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author sehyeon.eom
 * 
 * 괄호 짝짓기
 * 문자열에 사용된 괄호들이 짝이 맞는지 확인한다.
 * 
 * 1. 총 10개의 테스트 케이스가 주어진다.
 * 2. 첫 번째 줄에는 테스트케이스의 길이가 주어진다.
 * 3. 다음 줄에는 테스트 케이스가 주어진다.
 * 4. 유효성 여부를 1와 0으로 나타낸다. 1 - 유효, 0 - 비유효
 * 
 * 스택으로 구현하면 된다.
 * 가로의 종류는 총 4개
 * 닫힌 괄호가 나오지 않았는데 열린 괄호가 나오면 틀린 것이다.
 * 무조건 닫힌 괄호가 먼저 나와야 함
 * 닫힌 괄호를 저장할 스택을 생성한다.
 * 문자열을 끝에서부터 확인하며 닫힌 괄호가 나오면 스택에 저장
 * 이후 문자열의 다음 괄호가 닫힌 괄호 저장 스택의 괄호와 짝이 맞으면 해당 스택을 삭제한다.
 * 짝이 맞지 않으면 다음 괄호도 닫힌 괄호 저장 스택에 저장한다.
 * 이 과정을 반복하여 삭제를 진행하고 양쪽 스택이 모두 empty라면 1 아니면 0
 */

//배열을 기반으로한 스택 구현 메서드
class personalStack{
	// 초기화
	int top = -1;							// 현재 스택의 높이
	Object[] stack;							// 스택
	
	public void setSize(int size) {			// 스택의 사이즈를 저장
		stack = new Object[size];
	}
	
	// 저장소에 자료 저장
	public void push(Object element) {
		this.top++;							// 저장하면 스택 높이 증가
		this.stack[this.top] = element;		// 요소 저장
	}
	
	// 저장소에 자료 삭제
	public Object pop() {
		return this.stack[this.top--];		// 스택의 높이를 낮춘다.
	}
	
	// 0이 거짓 1이 참
	// 비어있는지 확인
	public boolean isEmpty() {	
		if(this.top == -1)
			return false;
		else 
			return true;
	}
	
	public int size() {
		return this.top + 1;	// 사이즈 반환
	}
	
	public Object peek() {
		return this.stack[this.top];	// 가장 위의 값 출력
	}
	
	public void clear() {
		for(int i = 0; i < top + 1; i++)
			stack[i] = null;
		top = -1;
	}
}
	
public class SWEA_1218 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int length;							// 문자열의 길이
	static char[] str;							// 문자열
	static final int totalCase = 10;			// 전체 테스트 케이스 수
	static int answer;							// 답
	
	public static void match(int idx, personalStack stack) {
		if(str[idx] == ')' && (char)stack.peek() == '(' || 
				str[idx] == '}' && (char)stack.peek() == '{' ||
				str[idx] == ']' && (char)stack.peek() == '[' ||
				str[idx] == '>' && (char)stack.peek() == '<') {
			stack.pop();
		}
		else
			stack.push(str[idx]);
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		personalStack stack = new personalStack();
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			answer = 0;
			// 문자열의 길이를 입력 받는다.
			length = Integer.parseInt(br.readLine().trim());	// 문자열 길이 저장
			stack.setSize(length); 		// 스택의 사이즈 지정
			
			// 문자열을 입력받는다 (괄호들)
			str = br.readLine().trim().toCharArray();
			
			// 문자열의 맨 뒤부터 괄호의 짝이 맞는지 체크한다. 괄호가 닫힌 괄호라면 스택에 저장
			for(int idx = 0; idx < length; idx++) {
				if(str[idx] == '(' || str[idx] == '[' || str[idx] == '{' || str[idx] == '<') {
					stack.push(str[idx]);
				}
				else {
					match(idx, stack);
				}
			}
			
			// 스택이 비어있는지 확인
			if(!stack.isEmpty())
				answer = 1;
			else
				answer = 0;
			
			//결과 저장
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			stack.clear();
		}	// tc end
		System.out.println(sb);
	}	// main end
}