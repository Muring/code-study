package algorithms;

class ListNode{
	private int data;	// 데이터 저장 변수
	public ListNode next;	// 다른 노드 참조 변수
	
	// 기본 생성자
	public ListNode() {
		this.data = 0;
		this.next = null;
	}
	
	// 파라미터 생성자
	public ListNode(int data) {
		this.data = data;
		this.next = null;
	}
	
	// 또 다른 리스트 노드 파라미터 생성자
	public ListNode(int data, ListNode link) {
		this.data = data;
		this.next = link;
	}
	
	// private인 데이터를 받아오기 위한 getter
	public int getData() {
		return this.data;
	}
}

public class SingleLinkedList {
	
	private ListNode head;		// ListNode 타입의 head

	static StringBuilder sb;	// 스트링 빌더 선언
	
	// SingleLinkedList 생성자
	public SingleLinkedList() {
		head = null;			// head의 시작은 null
	}
	
	// 중간에 추가
	// 단순히 연결만 하면 된다.
	public void insertNode(ListNode preNode, int data) {
		ListNode newNode = new ListNode(data);	// 새로운 노드 생성
		
		// newNode의 링크를 preNode가 가리키고 있던 노드로 지정
		newNode.next = preNode.next;
		
		// 이후 preNode는 newNode를 지정하여 중간 삽입 과정이 완성된다.
		preNode.next = newNode;
	}
	
	// 맨뒤에 추가
	public void insertNode(int data) {
		ListNode newNode = new ListNode(data);
		// 만약 노드에 아무것도 연결되어 있지 않으면
		if(head == null) {
			this.head = newNode;	// 헤드가 가리키는 것은 newNode가 된다.
		}
		// 노드에 이미 다른 노드들이 연결되어 있다면
		else {
			// 맨 끝의 노드를 찾기위한 노드로써 선언
			// 시작점인 head를 받고 시작한다.
			// head에서부터 시작해 모든 노드들의 정보를 받아와 마지막 노드를 찾는 역할
			ListNode tempNode = head;
			
			// 이후 tempNode가 끝 노드에 도착할 때까지 다음 노드를 참조
			// 다음 참조가 null이 아닐 때 그 다음 참조를 받음
			while(tempNode.next != null)
				tempNode = tempNode.next;
			
			// 반복문에서 나오면 이제 tempNode는 마지막 노드에 도착한 상태
			// 해당 노드의 link를 새로운 노드의 주소에 연결해주면 새로운 노드는 모든 노드들의 맨 뒤에 위치하게 된다.
			tempNode.next = newNode;
		}
	}
	
	// 특정 값을 가진 노드 삭제 (중간 삭제)
	public void deleteNode(int data) {
		ListNode currentNode = head;		// head가 가리키는 노드 -> 맨 앞의 노드
		ListNode nextNode = head.next;	// 맨 앞의 한 칸 뒤의 노드
		
		// 첫 번째 노드가 삭제되야하는 노드일 때
		if(data == currentNode.getData()) {
			// head는 한 칸 뒤의 노드를 가리켜야 한다.
			head = nextNode;
			// currentNode는 사라져야하기 때문에 null을 가리켜야 한다.
			currentNode.next = null;
		}
		else {
			// nextNode가 null일 때까지 반복 탐색
			// 즉, currentNode가 마지막 노드일 때까지
			while(nextNode != null) {
				// 주어진 데이터와 다음 노드의 데이터가 같을 때
				if(data == nextNode.getData()) {
					// 다음 노드의 데이터가 마지막일 경우
					// 현재 노드의 다음 주소를 null로 바꾸면 참조할 수 없게되고 사라진다.
					if(nextNode.next == null)
						currentNode.next = null;
					// 다음 노드가 마지막이 아닌 경우
					else {
						// 현재 노드의 다음 주소 참조 값을 다음 노드의 다음 노드를 가리키는 주소 값으로 바꾼다.
						// 이후 다음 노드의 참조값은 null로 하여 버린다.
						currentNode.next = nextNode.next;
						nextNode.next = null;
					}
					// 삭제가 된 경우이기 때문에 반복문에서 탈출
					break;
				}
				// 주어진 데이터와 다음 노드의 데이터가 같지 않을 경우 한칸씩 뒤로 이동한다.
				else {
					currentNode = nextNode;
					nextNode = nextNode.next;
				}
			}
		}
		
	}
	
	// 리스트를 전부 출력하는 메서드
	public void printList() {
		ListNode node = this.head;	// head에서부터 즉, 처음부터 출력을 해야하기 때문에 head를 받는다.
		
		// node가 null이 아닐 때까지 출력
		while(node != null) {
			sb.append(node.getData()).append(" ");	// 스트링 빌더에 값 저장
			node = node.next;						// 다음 노드를 참조하도록 함
		}
		System.out.printf("%s\n", sb);				// 출력
		sb.setLength(0);							// 스트링 빌더 초기화
	}
	
	public static void main(String[] args) {
		sb = new StringBuilder();						// 스트링 빌더 생성
		SingleLinkedList list = new SingleLinkedList();	// 연결 리스트 생성
		int num = 4;									// 삭제할 값을 넣는 변수
		// 리스트 데이터 삽입
		list.insertNode(2);
		list.insertNode(6);
		list.insertNode(4);
		list.insertNode(9);
		list.insertNode(7);
		
		// 리스트 출력, 삭제 후 다시 출력
		list.printList();
		list.deleteNode(num);
		list.printList();
	}

}
