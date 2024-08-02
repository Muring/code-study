import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static Node[] tree;

    // 전위 순회
    private static void preorder(Node node) {
        if (node == null) return;
        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회
    private static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    // 후위 순회
    private static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 트리 정보 입력
        int nodeCnt = Integer.parseInt(br.readLine());
        tree = new Node[nodeCnt + 1];

        for (int idx = 0; idx < nodeCnt; idx++) {
            // 노드 하나 받기
            st = new StringTokenizer(br.readLine());
            char parentValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);

            // 트리에 저장
            // 해당 부모 노드가 존재하지 않는다면
            if (tree[parentValue - 'A'] == null) {
                tree[parentValue - 'A'] = new Node(parentValue);
            }
            // 왼쪽 자식이 존재할 경우
            if (leftValue != '.') {
                tree[leftValue - 'A'] = new Node(leftValue);  // 트리에 해당 노드 정보를 저장하고
                tree[parentValue - 'A'].left = tree[leftValue - 'A']; // 해당 부모 노드의 left에 노드 정보 저장
            }
            // 오른쪽 자식이 존재할 경우
            if (rightValue != '.') {
                tree[rightValue - 'A'] = new Node(rightValue);
                tree[parentValue - 'A'].right = tree[rightValue - 'A'];
            }
        }
        preorder(tree[0]);
        sb.append("\n");
        inorder(tree[0]);
        sb.append("\n");
        postorder(tree[0]);

        System.out.println(sb);
    }
}