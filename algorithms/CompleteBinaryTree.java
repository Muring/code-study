package algorithms;

import java.util.*;

public class CompleteBinaryTree<T> {
	private Object[] nodes;
	private int lastIndex; // Last node index
	private final int SIZE; // max node count

	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new Object[size + 1];
	}

	public boolean isEmpty() {
		return lastIndex == 0;
	}

	public boolean isFull() {
		return lastIndex == SIZE;
	}

	public boolean add(T data) {
		if (isFull())
			return false;
		nodes[++lastIndex] = data;
		return true;
	}

	public void bfs() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<>();

		// 탐색 시작의 대상부터 큐에 넣기
		queue.offer(1); // 루트 노드의 인덱스

		while (!queue.isEmpty()) { // 탐색 대상이 있다면
			int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
			// 탐색 대상 방문
			System.out.println(nodes[current]);
			// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
			if (current * 2 <= lastIndex)
				queue.offer(current * 2);
			if (current * 2 + 1 <= lastIndex)
				queue.offer(current * 2 + 1);
		}
	}

	public void bfs2() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Queue<Integer> queue = new ArrayDeque<>();

		// 탐색 시작의 대상부터 큐에 넣기
		queue.offer(1); // 루트 노드의 인덱스

		int breadth = 0;
		while (!queue.isEmpty()) { // 탐색 대상이 있다면
			int size = queue.size();
			while (--size >= 0) {
				int current = queue.poll(); // 탐색 대상 큐에서 꺼내기
				// 탐색 대상 방문
				System.out.println(nodes[current] + "\t");
				// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
				if (current * 2 <= lastIndex)
					queue.offer(current * 2);
				if (current * 2 + 1 <= lastIndex)
					queue.offer(current * 2 + 1);
			}
			System.out.println();
			System.out.println("============" + breadth + "너비 탐색 완료");
			breadth++;
		}

	}

	public void bfs3() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Queue<int[]> queue = new ArrayDeque<>(); // int[] : { 탐색 노드의 인덱스, 너비 }

		// 탐색 시작의 대상부터 큐에 넣기
		queue.offer(new int[] { 1, 0 }); // 루트 노드의 인덱스
		;
		while (!queue.isEmpty()) { // 탐색 대상이 있다면
			int[] info = queue.poll();
			int current = info[0];

			System.out.println(nodes[current] + "//" + info[1]);

			if (current * 2 <= lastIndex)
				queue.offer(new int[] { current * 2, info[1] + 1 });
			if (current * 2 + 1 <= lastIndex)
				queue.offer(new int[] { current * 2 + 1, info[1] + 1 });
		}

	}

	public void dfsByPreOrder (int current) {
		// 탐색 대상 방문
		System.out.print(nodes[current]);
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
		if (current * 2 <= lastIndex)
			dfsByPreOrder(current * 2);
		if (current * 2 + 1 <= lastIndex)
			dfsByPreOrder(current * 2 + 1);
	}
	
	public void dfsByInOrder (int current) {
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
		if (current * 2 <= lastIndex)
			dfsByInOrder(current * 2);
		// 탐색 대상 방문
		System.out.print(nodes[current]);
		if (current * 2 + 1 <= lastIndex)
			dfsByInOrder(current * 2 + 1);
	}
	
	public void dfsByPostOrder (int current) {
		// 현재 탐색 대상을 통해 탐색해야할 새로운 대상을 큐에 넣기
		if (current * 2 <= lastIndex)
			dfsByPostOrder(current * 2);	
		if (current * 2 + 1 <= lastIndex)
			dfsByPostOrder(current * 2 + 1);
		// 탐색 대상 방문
		System.out.print(nodes[current]);
	}
	
	public void dfs() {
		if (isEmpty())
			return;
		// 탐색 순서를 관리할 대기열 자료구조 생성
		Stack<Integer> stack = new Stack<>(); // int[] : { 탐색 노드의 인덱스, 너비 }

		// 탐색 시작의 대상부터 큐에 넣기s
		 // 루트 노드의 인덱스
		stack.push(1);
		
		while (!stack.isEmpty()) { // 탐색 대상이 있다면
			int current = stack.pop();

			System.out.print(nodes[current]);

			if (current * 2 <= lastIndex)
				stack.push(current * 2);
			if (current * 2 + 1 <= lastIndex)
				stack.push(current * 2);
		}

	}
}
