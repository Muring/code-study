package algorithms;

public class CompleteBinaryTreeTest {
	public static void main(String[] args) {
		int size = 9;
		CompleteBinaryTree<Character> tree = new CompleteBinaryTree<>(size);

		for (int i = 0; i < size; i++) {
			tree.add((char) (65 + i));

		}
		tree.dfsByPreOrder(1);
		System.out.println();
		tree.dfsByInOrder(1);
		System.out.println();
		tree.dfsByPostOrder(1);
		System.out.println();
		tree.dfs();
		System.out.println();
	}
}
