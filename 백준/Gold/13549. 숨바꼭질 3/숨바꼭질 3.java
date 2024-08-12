import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int minTime;
    static final int MAX = 100000;
    static boolean[] isVisited;

    public static class Node {
        int position;
        int time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    private static void move(int start, int target) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, 0));

        // 범위 초과가 아닐 때
        if (start * 2 <= MAX && !isVisited[start * 2]) {
            que.offer(new Node(start * 2, 0));
            isVisited[start * 2] = true;
        }

        while (!que.isEmpty()) {
            Node currentNode = que.poll();
            isVisited[currentNode.position] = true;

            // 도착
            if (currentNode.position == target) {
                minTime = Math.min(minTime, currentNode.time);
            }

            if (currentNode.position * 2 <= MAX && !isVisited[currentNode.position * 2])
                que.offer(new Node(currentNode.position * 2, currentNode.time));
            if (currentNode.position + 1 <= MAX && !isVisited[currentNode.position + 1])
                que.offer(new Node(currentNode.position + 1, currentNode.time + 1));
            if (currentNode.position - 1 >= 0 && !isVisited[currentNode.position - 1])
                que.offer(new Node(currentNode.position - 1, currentNode.time + 1));
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        minTime = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        isVisited = new boolean[MAX + 1];

        move(start, target);
        System.out.println(minTime);
    }
}