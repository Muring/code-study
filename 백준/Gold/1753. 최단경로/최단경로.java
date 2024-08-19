import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int nodeCnt; // 노드 수
    static int edgeCnt; // 간선 수
    static int startNode;   // 시작 노드
    static List<Node>[] nodeList; // 노드 리스트
    static int[] dist;  // 최소 가중치 저장 배열

    static final int INF = 100_000_000;

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] isVisited = new boolean[nodeCnt + 1];
        que.add(new Node(startNode, 0));
        dist[startNode] = 0;

        while (!que.isEmpty()) {
            Node currentNode = que.poll();
            int currentIdx = currentNode.end;

            // 현재 노드가 방문된 적 있다면 넘어간다.
            if (isVisited[currentIdx]) continue;
            isVisited[currentIdx] = true;

            for (Node node : nodeList[currentIdx]) {

                if (dist[node.end] > dist[currentIdx] + node.weight) {
                    dist[node.end] = dist[currentIdx] + node.weight;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력
        st = new StringTokenizer(br.readLine());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(br.readLine());

        // 초기화
        dist = new int[nodeCnt + 1];
        Arrays.fill(dist, INF);

        nodeList = new ArrayList[nodeCnt + 1];
        for (int idx = 1; idx <= nodeCnt; idx++) {
            nodeList[idx] = new ArrayList<>();
        }

        // 노드 정보 입력
        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList[start].add(new Node(end, weight));
        }

        dijkstra();
        for (int idx = 1; idx <= nodeCnt; idx++) {
            if (dist[idx] == INF) sb.append("INF");
            else sb.append(dist[idx]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}