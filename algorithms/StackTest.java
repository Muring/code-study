package algorithms;

/**
 * 
 * @author sehyeon.eom
 *
 * 스택을 구현한다.
 * 스택의 메서드를 구현 - push, pop, isEmpty, size - peek
 */

// 배열을 기반으로한 스택 구현 메서드
class personalArrayStack{
	// 초기화
	int top = -1;							// 현재 스택의 높이
	Object[] stack = new Object[100];		// 스택의 갯수 100개로 제한
	
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
}

public class StackTest {
	
	public static void arrayStackTest(personalArrayStack stack) {
		// 스택 상태 출력
		System.out.println(stack.isEmpty());
		
		// 값 대입
		stack.push(1);
		stack.push(2);
		stack.push("TEST");
		
		// 스택의 맨 위의 값 출력
		System.out.println(stack.peek());
		
		// 스택의 맨 위의 값을 삭제시키고 난 후의 맨 위의 값 출력
		stack.pop();
		System.out.println(stack.peek());
		
		// 스택 상태 출력
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
	}

	
	public static void main(String[] args) {
		personalArrayStack arrayStack = new personalArrayStack();
		
		arrayStackTest(arrayStack);
		
	}
}
