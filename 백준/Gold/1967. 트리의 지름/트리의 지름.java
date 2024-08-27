import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static List<Node>[] tree;
    static boolean[] isVisted;
    static int answer;

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    private static void dfs(int idx, int sum) {
        for (Node node : tree[idx]) {
            if (!isVisted[node.end]) {
                isVisted[idx] = true;
                dfs(node.end, sum + node.weight);
            }
        }
        answer = Math.max(answer, sum);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int nodeCnt = Integer.parseInt(br.readLine());
        tree = new ArrayList[nodeCnt + 1];
        for (int idx = 1; idx <= nodeCnt; idx++)
            tree[idx] = new ArrayList<>();

        // 노드 정보 입력
        for (int idx = 0; idx < nodeCnt - 1; idx++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[from].add(new Node(to, weight));
            tree[to].add(new Node(from, weight));
        }

        answer = 0;
        for (int idx = 1; idx <= nodeCnt; idx++) {
            isVisted = new boolean[nodeCnt + 1];
            isVisted[idx] = true;
            dfs(idx, 0);
        }
        System.out.println(answer);
    }
}